package com.qrvault.app.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.qrvault.app.R
import com.qrvault.app.adapter.HistoryAdapter
import com.qrvault.app.data.QrRepository
import com.qrvault.app.databinding.ActivityHistoryBinding
import com.qrvault.app.model.QrRecord
import com.qrvault.app.util.setupHeader
import com.qrvault.app.util.toast

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private val repository by lazy { QrRepository.get(this) }
    private val adapter = HistoryAdapter(
        onView = { startActivity(QrCodeActivity.newIntent(this, it)) },
        onDelete = { confirmDelete(it) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupHeader(binding.header, getString(R.string.history_title))

        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = adapter

        binding.emptyState.ivEmpty.setImageResource(R.drawable.ic_history)
        binding.emptyState.tvEmptyTitle.setText(R.string.history_empty_title)
        binding.emptyState.tvEmptyMessage.setText(R.string.history_empty_message)
        binding.emptyState.btnEmptyAction.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

    private fun refresh() {
        val records = repository.getAll()
        adapter.submitList(records)
        val empty = records.isEmpty()
        binding.emptyState.root.visibility = if (empty) View.VISIBLE else View.GONE
        binding.rvHistory.visibility = if (empty) View.GONE else View.VISIBLE
    }

    private fun confirmDelete(record: QrRecord) {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.delete_title)
            .setMessage(getString(R.string.delete_message, record.contactName))
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.delete) { _, _ ->
                if (repository.delete(record.id)) toast(R.string.deleted) else toast(R.string.delete_failed)
                refresh()
            }
            .show()
    }
}
