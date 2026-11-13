package com.qrvault.app.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.qrvault.app.R
import com.qrvault.app.adapter.ContactAdapter
import com.qrvault.app.databinding.ActivityContactsBinding
import com.qrvault.app.model.Contact
import com.qrvault.app.util.ContactProvider
import com.qrvault.app.util.PermissionHelper
import com.qrvault.app.util.setupHeader
import java.util.concurrent.Executors

class ContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactsBinding
    private val adapter = ContactAdapter { openDetails(it) }
    private val executor = Executors.newSingleThreadExecutor()

    private var allContacts: List<Contact> = emptyList()
    private var loaded = false
    private var loading = false
    private var askedOnce = false
    private var requestedOnce = false

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) loadContacts() else showPermissionDenied()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupHeader(binding.header, getString(R.string.contacts_title))

        binding.rvContacts.layoutManager = LinearLayoutManager(this)
        binding.rvContacts.adapter = adapter
        binding.etSearch.doAfterTextChanged { applyFilter(it?.toString().orEmpty()) }
    }

    override fun onResume() {
        super.onResume()
        if (PermissionHelper.hasContactsPermission(this)) {
            if (!loaded && !loading) loadContacts()
        } else if (!askedOnce) {
            askedOnce = true
            showRationaleDialog()
        }
    }

    override fun onDestroy() {
        executor.shutdownNow()
        super.onDestroy()
    }

    // ---------------- permission flow ----------------

    private fun showRationaleDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.perm_title)
            .setMessage(R.string.perm_rationale)
            .setCancelable(false)
            .setPositiveButton(R.string.perm_continue) { _, _ -> requestPermission() }
            .setNegativeButton(R.string.perm_not_now) { _, _ -> showPermissionDenied() }
            .show()
    }

    private fun requestPermission() {
        requestedOnce = true
        permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
    }

    private fun showPermissionDenied() {
        val permanentlyDenied = requestedOnce &&
            !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)
        showState(
            getString(R.string.perm_denied_title),
            getString(R.string.perm_denied_message),
            getString(if (permanentlyDenied) R.string.open_settings else R.string.grant_permission)
        ) {
            if (permanentlyDenied) PermissionHelper.openAppSettings(this) else requestPermission()
        }
    }

    // ---------------- loading & filtering ----------------

    private fun loadContacts() {
        if (loading) return
        loading = true
        binding.progress.visibility = View.VISIBLE
        binding.emptyState.root.visibility = View.GONE
        binding.rvContacts.visibility = View.GONE

        val unnamed = getString(R.string.unnamed_contact)
        val appContext = applicationContext
        try {
            executor.execute {
                val result = try {
                    ContactProvider.loadContacts(appContext, unnamed)
                } catch (e: Exception) {
                    null
                }
                runOnUiThread {
                    if (isFinishing || isDestroyed) return@runOnUiThread
                    loading = false
                    binding.progress.visibility = View.GONE
                    if (result == null) {
                        showState(
                            getString(R.string.contacts_error_title),
                            getString(R.string.contacts_error),
                            getString(R.string.retry)
                        ) { loadContacts() }
                    } else {
                        loaded = true
                        allContacts = result
                        applyFilter(binding.etSearch.text?.toString().orEmpty())
                    }
                }
            }
        } catch (e: Exception) {
            loading = false
            binding.progress.visibility = View.GONE
        }
    }

    private fun applyFilter(query: String) {
        if (!loaded) return
        val q = query.trim()
        val digits = q.filter { it.isDigit() || it == '+' }
        val filtered = if (q.isEmpty()) allContacts else allContacts.filter { c ->
            c.name.contains(q, ignoreCase = true) ||
                c.emails.any { it.contains(q, ignoreCase = true) } ||
                (digits.isNotEmpty() && c.phones.any { p ->
                    p.filter { it.isDigit() || it == '+' }.contains(digits)
                })
        }
        adapter.submitList(filtered)
        binding.tvCount.text = getString(R.string.contacts_count, filtered.size)

        when {
            allContacts.isEmpty() -> showState(
                getString(R.string.contacts_empty_title), getString(R.string.contacts_empty_message), null, null
            )
            filtered.isEmpty() -> showState(
                getString(R.string.contacts_no_match_title), getString(R.string.contacts_no_match_message), null, null
            )
            else -> {
                binding.emptyState.root.visibility = View.GONE
                binding.rvContacts.visibility = View.VISIBLE
            }
        }
    }

    private fun showState(title: String, message: String, actionLabel: String?, action: (() -> Unit)?) {
        binding.progress.visibility = View.GONE
        binding.rvContacts.visibility = View.GONE
        val s = binding.emptyState
        s.root.visibility = View.VISIBLE
        s.tvEmptyTitle.text = title
        s.tvEmptyMessage.text = message
        if (actionLabel != null && action != null) {
            s.btnEmptyAction.visibility = View.VISIBLE
            s.btnEmptyAction.text = actionLabel
            s.btnEmptyAction.setOnClickListener { action() }
        } else {
            s.btnEmptyAction.visibility = View.GONE
        }
    }

    private fun openDetails(contact: Contact) {
        startActivity(Intent(this, ContactDetailsActivity::class.java).putExtra(ContactDetailsActivity.EXTRA_CONTACT, contact))
    }
}
