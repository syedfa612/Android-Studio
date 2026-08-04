package com.example.a3rdexperiment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CourseAdapter(
    private val onItemClick: (CourseItem) -> Unit
) : ListAdapter<CourseItem, CourseAdapter.CourseViewHolder>(CourseDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)
    }

    inner class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textCategory: TextView = itemView.findViewById(R.id.text_category)
        private val textDifficulty: TextView = itemView.findViewById(R.id.text_difficulty)
        private val textTitle: TextView = itemView.findViewById(R.id.text_title)
        private val textDuration: TextView = itemView.findViewById(R.id.text_duration)

        fun bind(course: CourseItem) {
            textTitle.text = course.title
            textCategory.text = course.category.uppercase()
            textDuration.text = "Duration: ${course.duration}"
            textDifficulty.text = course.difficulty

            // Style difficulty badges dynamically
            val context = itemView.context
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

            itemView.setOnClickListener {
                onItemClick(course)
            }
        }
    }

    companion object CourseDiffCallback : DiffUtil.ItemCallback<CourseItem>() {
        override fun areItemsTheSame(oldItem: CourseItem, newItem: CourseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CourseItem, newItem: CourseItem): Boolean {
            return oldItem == newItem
        }
    }
}
