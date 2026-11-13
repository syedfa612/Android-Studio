package com.qrvault.app.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.qrvault.app.R
import com.qrvault.app.data.QrRepository
import com.qrvault.app.databinding.ActivityContactDetailsBinding
import com.qrvault.app.model.Contact
import com.qrvault.app.model.QrRecord
import com.qrvault.app.util.QrGenerator
import com.qrvault.app.util.setupHeader
import com.qrvault.app.util.toast

class ContactDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupHeader(binding.header, getString(R.string.contact_details))

        val contact = IntentCompat.getParcelableExtra(intent, EXTRA_CONTACT, Contact::class.java)
        if (contact == null) {
            toast(R.string.contact_missing)
            finish()
            return
        }

        binding.tvInitial.text = contact.initial
        binding.tvName.text = contact.name
        binding.tvPhone.text =
            if (contact.phones.isEmpty()) getString(R.string.no_phone) else contact.phones.joinToString("\n")
        binding.tvEmail.text =
            if (contact.emails.isEmpty()) getString(R.string.no_email) else contact.emails.joinToString("\n")

        binding.btnGenerate.setOnClickListener { generate(contact) }
        binding.btnChooseAnother.setOnClickListener { finish() }
    }

    private fun generate(contact: Contact) {
        val vCard = QrGenerator.buildVCard(contact.name, contact.phones, contact.emails)
        try {
            QrGenerator.generate(vCard) // validate that encoding works before saving history
        } catch (e: Exception) {
            toast(R.string.qr_failed)
            return
        }
        val phone = contact.phones.joinToString(", ")
        val email = contact.emails.joinToString(", ")
        val id = QrRepository.get(this).insert(contact.name, phone, email, vCard)
        if (id < 0) toast(R.string.history_not_saved)

        val record = QrRecord(id, contact.name, phone, email, vCard, System.currentTimeMillis())
        startActivity(QrCodeActivity.newIntent(this, record))
    }

    companion object {
        const val EXTRA_CONTACT = "extra_contact"
    }
}
