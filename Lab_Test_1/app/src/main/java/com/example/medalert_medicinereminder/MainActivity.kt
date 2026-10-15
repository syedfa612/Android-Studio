package com.example.medalert_medicinereminder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medalert_medicinereminder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val medicineList = mutableListOf<Medicine>()
    private lateinit var adapter: MedicineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.btnAddMedicine.setOnClickListener {
            Log.d("MainActivity", "Add button clicked")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddMedicineFragment())
                .addToBackStack(null)
                .commit()
        }

        supportFragmentManager.setFragmentResultListener("requestKey", this) { _, bundle ->
            val medicine = bundle.getSerializable("bundleKey") as? Medicine
            medicine?.let {
                medicineList.add(it)
                adapter.notifyItemInserted(medicineList.size - 1)
                Log.d("MainActivity", "Medicine added: ${it.name}")
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = MedicineAdapter(medicineList) { medicine ->
            Log.d("MainActivity", "Medicine clicked: ${medicine.name}")
            val intent = Intent(this, MedicineDetailsActivity::class.java).apply {
                putExtra("MEDICINE_EXTRA", medicine)
            }
            startActivity(intent)
        }
        binding.rvMedicines.layoutManager = LinearLayoutManager(this)
        binding.rvMedicines.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart")
    }
}
