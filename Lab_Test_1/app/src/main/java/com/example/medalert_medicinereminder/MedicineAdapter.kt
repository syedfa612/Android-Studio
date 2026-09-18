package com.example.medalert_medicinereminder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.medalert_medicinereminder.databinding.ItemMedicineBinding

class MedicineAdapter(
    private val medicines: List<Medicine>,
    private val onItemClick: (Medicine) -> Unit
) : RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>() {

    class MedicineViewHolder(val binding: ItemMedicineBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val binding = ItemMedicineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MedicineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        val medicine = medicines[position]
        holder.binding.tvMedicineName.text = medicine.name
        holder.binding.tvMedicineDosage.text = "Dosage: ${medicine.dosage}"
        holder.binding.tvMedicineDetails.text = "${medicine.type} - ${medicine.frequency}"
        
        holder.itemView.setOnClickListener {
            onItemClick(medicine)
        }
    }

    override fun getItemCount(): Int = medicines.size
}
