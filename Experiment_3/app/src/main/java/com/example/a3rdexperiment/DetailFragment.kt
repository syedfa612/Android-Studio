package com.example.a3rdexperiment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.button.MaterialButton

class DetailFragment : Fragment() {

    private val viewModel: CourseViewModel by activityViewModels()

    companion object {
        private const val TAG = "DetailFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Lifecycle Callback: onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "Lifecycle Callback: onCreateView")
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Lifecycle Callback: onViewCreated")

        val emptyState = view.findViewById<View>(R.id.layout_empty_state)
        val detailContent = view.findViewById<View>(R.id.layout_detail_content)
        val textTitle = view.findViewById<TextView>(R.id.text_detail_title)
        val textCategory = view.findViewById<TextView>(R.id.text_detail_category)
        val textDifficulty = view.findViewById<TextView>(R.id.text_detail_difficulty)
        val textDuration = view.findViewById<TextView>(R.id.text_detail_duration)
        val textDescription = view.findViewById<TextView>(R.id.text_detail_description)
        val enrollButton = view.findViewById<MaterialButton>(R.id.button_enroll)

        // Observe the selected course from the shared ViewModel
        viewModel.selectedCourse.observe(viewLifecycleOwner) { course ->
            if (course != null) {
                // --- NORMAL BREAKPOINT TARGET ---
                // Place a normal breakpoint on this line to inspect local variables,
                // the call stack, and the fragment lifecycle state.
                emptyState.visibility = View.GONE
                detailContent.visibility = View.VISIBLE

                textTitle.text = course.title
                textCategory.text = course.category.uppercase()
                textDuration.text = course.duration
                textDifficulty.text = course.difficulty
                textDescription.text = course.description

                // Dynamic coloring for difficulty badge
                val context = requireContext()
                when (course.difficulty.lowercase()) {
                    "beginner" -> {
                        textDifficulty.setBackgroundColor(ContextCompat.getColor(context, R.color.bg_easy))
                        textDifficulty.setTextColor(ContextCompat.getColor(context, R.color.text_easy))
                    }
                    "intermediate" -> {
                        textDifficulty.setBackgroundColor(ContextCompat.getColor(context, R.color.bg_medium))
                        textDifficulty.setTextColor(ContextCompat.getColor(context, R.color.text_medium))
                    }
                    "advanced" -> {
                        textDifficulty.setBackgroundColor(ContextCompat.getColor(context, R.color.bg_hard))
                        textDifficulty.setTextColor(ContextCompat.getColor(context, R.color.text_hard))
                    }
                }
                
                enrollButton.text = "Register for ${course.title}"
            } else {
                emptyState.visibility = View.VISIBLE
                detailContent.visibility = View.GONE
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Lifecycle Callback: onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Lifecycle Callback: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Lifecycle Callback: onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Lifecycle Callback: onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "Lifecycle Callback: onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Lifecycle Callback: onDestroy")
    }
}
