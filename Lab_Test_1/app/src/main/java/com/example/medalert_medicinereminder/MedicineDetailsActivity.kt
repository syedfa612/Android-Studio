package com.example.medalert_medicinereminder

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.medalert_medicinereminder.databinding.ActivityMedicineDetailsBinding

class MedicineDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMedicineDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MedicineDetailsActivity", "onCreate")
        binding = ActivityMedicineDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val medicine = intent.getSerializableExtra("MEDICINE_EXTRA") as? Medicine
        medicine?.let {
            binding.tvDetailName.text = "Name: ${it.name}"
            binding.tvDetailDosage.text = "Dosage: ${it.dosage}"
            binding.tvDetailType.text = "Type: ${it.type}"
            binding.tvDetailFrequency.text = "Frequency: ${it.frequency}"
            binding.tvDetailReminder.text = "Reminder: ${if (it.hasReminder) "Enabled" else "Disabled"}"
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MedicineDetailsActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MedicineDetailsActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MedicineDetailsActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MedicineDetailsActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MedicineDetailsActivity", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MedicineDetailsActivity", "onRestart")
    }
}
