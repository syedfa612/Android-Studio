package com.qrvault.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.qrvault.app.R
import com.qrvault.app.databinding.ItemContactBinding
import com.qrvault.app.model.Contact

private object ContactDiff : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Contact, newItem: Contact) = oldItem == newItem
}

class ContactAdapter(private val onClick: (Contact) -> Unit) :
    ListAdapter<Contact, ContactAdapter.ViewHolder>(ContactDiff) {

    class ViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = getItem(position)
        val b = holder.binding
        b.tvInitial.text = contact.initial
        b.tvName.text = contact.name
        b.tvPhone.text = contact.phones.firstOrNull()
            ?: b.root.context.getString(R.string.no_phone)
        b.root.setOnClickListener { onClick(contact) }
    }
}
