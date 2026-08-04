package com.example.a3rdexperiment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {

    private val viewModel: CourseViewModel by activityViewModels()
    private lateinit var adapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        
        adapter = CourseAdapter { course ->
            // --- CONDITIONAL BREAKPOINT TARGET ---
            // Place a conditional breakpoint on the line below.
            // Condition: course.title == "Android Development" (or course.id == 1)
            viewModel.selectCourse(course)

            val isDualPane = activity?.findViewById<View>(R.id.detail_container) != null
            if (!isDualPane) {
                // If single-pane mode, navigate to DetailFragment
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, DetailFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }

        recyclerView.adapter = adapter

        viewModel.courses.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }
}
