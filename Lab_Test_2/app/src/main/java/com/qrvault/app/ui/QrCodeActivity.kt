package com.qrvault.app.ui

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.qrvault.app.R
import com.qrvault.app.databinding.ActivityQrCodeBinding
import com.qrvault.app.model.QrRecord
import com.qrvault.app.util.ImageStorage
import com.qrvault.app.util.PermissionHelper
import com.qrvault.app.util.QrGenerator
import com.qrvault.app.util.setupHeader
import com.qrvault.app.util.toast

class QrCodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQrCodeBinding
    private var record: QrRecord? = null
    private var bitmap: Bitmap? = null

    private val storageLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) saveImage() else toast(R.string.storage_denied)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupHeader(binding.header, getString(R.string.qr_title))

        val r = IntentCompat.getParcelableExtra(intent, EXTRA_RECORD, QrRecord::class.java)
        if (r == null) {
            toast(R.string.record_missing)
            finish()
            return
        }
        record = r

        binding.tvName.text = r.contactName
        binding.tvPhone.text = r.phone.ifEmpty { getString(R.string.not_available) }
        binding.tvEmail.text = r.email.ifEmpty { getString(R.string.not_available) }

        try {
            val bmp = QrGenerator.generate(r.vCard)
            bitmap = bmp
            binding.ivQr.setImageBitmap(bmp)
        } catch (e: Exception) {
            toast(R.string.qr_failed)
            binding.btnSave.isEnabled = false
            binding.btnShare.isEnabled = false
        }

        binding.btnSave.setOnClickListener {
            if (PermissionHelper.needsLegacyStoragePermission(this)) {
                storageLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            } else {
                saveImage()
            }
        }
        binding.btnShare.setOnClickListener { shareImage() }
        binding.btnGenerateAnother.setOnClickListener {
            startActivity(
                Intent(this, ContactsActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            )
            finish()
        }
        binding.btnBackHome.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            )
            finish()
        }
    }

    private fun saveImage() {
        val bmp = bitmap ?: return
        val name = record?.contactName.orEmpty()
        if (ImageStorage.saveToGallery(this, bmp, name)) toast(R.string.saved_ok) else toast(R.string.saved_fail)
    }

    private fun shareImage() {
        val bmp = bitmap ?: return
        val uri = ImageStorage.cacheForSharing(this, bmp, record?.contactName.orEmpty())
        if (uri == null) {
            toast(R.string.share_fail)
            return
        }
        val send = Intent(Intent.ACTION_SEND).apply {
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, uri)
            clipData = ClipData.newRawUri(null, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        try {
            startActivity(Intent.createChooser(send, getString(R.string.share_chooser)))
        } catch (e: ActivityNotFoundException) {
            toast(R.string.no_share_app)
        } catch (e: Exception) {
            toast(R.string.share_fail)
        }
    }

    companion object {
        const val EXTRA_RECORD = "extra_record"

        fun newIntent(context: Context, record: QrRecord): Intent =
            Intent(context, QrCodeActivity::class.java).putExtra(EXTRA_RECORD, record)
    }
}
