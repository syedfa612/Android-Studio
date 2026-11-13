package com.qrvault.app.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object ImageStorage {
    private const val TAG = "ImageStorage"
    private const val FOLDER = "QRVault"

    private fun fileName(contactName: String): String {
        val safe = contactName.replace(Regex("[^A-Za-z0-9]"), "_").take(20).ifEmpty { "contact" }
        return "QRVault_${safe}_${System.currentTimeMillis()}.png"
    }

    /** Saves the bitmap to Pictures/QRVault. Returns true on success. */
    @Suppress("DEPRECATION")
    fun saveToGallery(context: Context, bitmap: Bitmap, contactName: String): Boolean {
        val name = fileName(contactName)
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val values = ContentValues().apply {
                    put(MediaStore.Images.Media.DISPLAY_NAME, name)
                    put(MediaStore.Images.Media.MIME_TYPE, "image/png")
                    put(MediaStore.Images.Media.RELATIVE_PATH, "${Environment.DIRECTORY_PICTURES}/$FOLDER")
                    put(MediaStore.Images.Media.IS_PENDING, 1)
                }
                val resolver = context.contentResolver
                val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                    ?: return false
                try {
                    val stream = resolver.openOutputStream(uri) ?: throw IOException("No output stream")
                    stream.use {
                        if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)) {
                            throw IOException("Bitmap compress failed")
                        }
                    }
                    values.clear()
                    values.put(MediaStore.Images.Media.IS_PENDING, 0)
                    resolver.update(uri, values, null, null)
                    true
                } catch (e: Exception) {
                    resolver.delete(uri, null, null)
                    throw e
                }
            } else {
                val dir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), FOLDER)
                if (!dir.exists() && !dir.mkdirs()) return false
                val file = File(dir, name)
                FileOutputStream(file).use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
                MediaScannerConnection.scanFile(context, arrayOf(file.absolutePath), arrayOf("image/png"), null)
                true
            }
        } catch (e: Exception) {
            Log.e(TAG, "saveToGallery failed", e)
            false
        }
    }

    /** Writes the bitmap to the app cache and returns a secure FileProvider content URI. */
    fun cacheForSharing(context: Context, bitmap: Bitmap, contactName: String): Uri? {
        return try {
            val dir = File(context.cacheDir, "shared")
            if (dir.exists()) dir.listFiles()?.forEach { it.delete() } else dir.mkdirs()
            val file = File(dir, fileName(contactName))
            FileOutputStream(file).use {
                if (!bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)) throw IOException("compress failed")
            }
            FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        } catch (e: Exception) {
            Log.e(TAG, "cacheForSharing failed", e)
            null
        }
    }
}
