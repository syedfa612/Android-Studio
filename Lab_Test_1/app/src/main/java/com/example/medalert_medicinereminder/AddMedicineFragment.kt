package com.example.medalert_medicinereminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.medalert_medicinereminder.databinding.FragmentAddMedicineBinding

class AddMedicineFragment : Fragment() {

    private var _binding: FragmentAddMedicineBinding? = null
    private val binding get() = _binding!!

    private val CHANNEL_ID = "MedAlertChannel"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("AddMedicineFragment", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("AddMedicineFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("AddMedicineFragment", "onCreateView")
        _binding = FragmentAddMedicineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("AddMedicineFragment", "onViewCreated")

        binding.btnSave.setOnClickListener {
            saveMedicine()
        }
    }

    private fun saveMedicine() {
        val name = binding.etName.text.toString().trim()
        val dosage = binding.etDosage.text.toString().trim()

        if (name.isEmpty() || dosage.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val typeId = binding.rgType.checkedRadioButtonId
        val type = view?.findViewById<RadioButton>(typeId)?.text.toString()

        val frequencyId = binding.rgFrequency.checkedRadioButtonId
        val frequency = view?.findViewById<RadioButton>(frequencyId)?.text.toString()

        val hasReminder = binding.cbReminder.isChecked

        val medicine = Medicine(name, dosage, type, frequency, hasReminder)

        if (hasReminder) {
            sendNotification(medicine)
        }

        setFragmentResult("requestKey", bundleOf("bundleKey" to medicine))
        parentFragmentManager.popBackStack()
    }

    private fun sendNotification(medicine: Medicine) {
        val notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Medicine Reminders",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Medicine Reminder")
            .setContentText("Time to take ${medicine.name} (${medicine.dosage})")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }

    override fun onStart() {
        super.onStart()
        Log.d("AddMedicineFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("AddMedicineFragment", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("AddMedicineFragment", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("AddMedicineFragment", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("AddMedicineFragment", "onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AddMedicineFragment", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("AddMedicineFragment", "onDetach")
    }
}
