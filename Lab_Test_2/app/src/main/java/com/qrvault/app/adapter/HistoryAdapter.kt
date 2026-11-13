package com.qrvault.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.qrvault.app.R
import com.qrvault.app.databinding.ItemHistoryBinding
import com.qrvault.app.model.QrRecord

private object RecordDiff : DiffUtil.ItemCallback<QrRecord>() {
    override fun areItemsTheSame(oldItem: QrRecord, newItem: QrRecord) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: QrRecord, newItem: QrRecord) = oldItem == newItem
}

class HistoryAdapter(
    private val onView: (QrRecord) -> Unit,
    private val onDelete: (QrRecord) -> Unit
) : ListAdapter<QrRecord, HistoryAdapter.ViewHolder>(RecordDiff) {

    class ViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = getItem(position)
        val b = holder.binding
        b.tvName.text = record.contactName
        b.tvDate.text = record.formattedDate
        b.tvPhone.text = record.phone.ifEmpty { b.root.context.getString(R.string.no_phone) }
        b.root.setOnClickListener { onView(record) }
        b.btnView.setOnClickListener { onView(record) }
        b.btnDelete.setOnClickListener { onDelete(record) }
    }
}
