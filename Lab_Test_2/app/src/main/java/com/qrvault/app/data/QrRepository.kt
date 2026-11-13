package com.qrvault.app.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.util.Log
import com.qrvault.app.data.QRVaultDbHelper.Companion.COL_CREATED
import com.qrvault.app.data.QRVaultDbHelper.Companion.COL_EMAIL
import com.qrvault.app.data.QRVaultDbHelper.Companion.COL_ID
import com.qrvault.app.data.QRVaultDbHelper.Companion.COL_NAME
import com.qrvault.app.data.QRVaultDbHelper.Companion.COL_PHONE
import com.qrvault.app.data.QRVaultDbHelper.Companion.COL_VCARD
import com.qrvault.app.data.QRVaultDbHelper.Companion.TABLE
import com.qrvault.app.model.QrRecord

/** CRUD access to the QR history table. All methods fail safe (never throw). */
class QrRepository private constructor(context: Context) {

    private val helper = QRVaultDbHelper(context)

    /** Returns the new row id, or -1 if the insert failed. */
    fun insert(name: String, phone: String, email: String, vCard: String): Long {
        return try {
            val values = ContentValues().apply {
                put(COL_NAME, name)
                put(COL_PHONE, phone)
                put(COL_EMAIL, email)
                put(COL_VCARD, vCard)
                put(COL_CREATED, System.currentTimeMillis())
            }
            helper.writableDatabase.insertOrThrow(TABLE, null, values)
        } catch (e: SQLException) {
            Log.e(TAG, "insert failed", e)
            -1L
        }
    }

    fun getAll(): List<QrRecord> {
        return try {
            helper.readableDatabase.query(
                TABLE, null, null, null, null, null, "$COL_CREATED DESC, $COL_ID DESC"
            ).use { c ->
                val list = ArrayList<QrRecord>(c.count)
                while (c.moveToNext()) list.add(c.toRecord())
                list
            }
        } catch (e: SQLException) {
            Log.e(TAG, "getAll failed", e)
            emptyList()
        }
    }

    fun getById(id: Long): QrRecord? {
        return try {
            helper.readableDatabase.query(
                TABLE, null, "$COL_ID = ?", arrayOf(id.toString()), null, null, null
            ).use { c -> if (c.moveToFirst()) c.toRecord() else null }
        } catch (e: SQLException) {
            Log.e(TAG, "getById failed", e)
            null
        }
    }

    fun delete(id: Long): Boolean {
        return try {
            helper.writableDatabase.delete(TABLE, "$COL_ID = ?", arrayOf(id.toString())) > 0
        } catch (e: SQLException) {
            Log.e(TAG, "delete failed", e)
            false
        }
    }

    private fun Cursor.toRecord() = QrRecord(
        id = getLong(getColumnIndexOrThrow(COL_ID)),
        contactName = getString(getColumnIndexOrThrow(COL_NAME)).orEmpty(),
        phone = getString(getColumnIndexOrThrow(COL_PHONE)).orEmpty(),
        email = getString(getColumnIndexOrThrow(COL_EMAIL)).orEmpty(),
        vCard = getString(getColumnIndexOrThrow(COL_VCARD)).orEmpty(),
        createdAt = getLong(getColumnIndexOrThrow(COL_CREATED))
    )

    companion object {
        private const val TAG = "QrRepository"

        @Volatile
        private var instance: QrRepository? = null

        fun get(context: Context): QrRepository =
            instance ?: synchronized(this) {
                instance ?: QrRepository(context.applicationContext).also { instance = it }
            }
    }
}
