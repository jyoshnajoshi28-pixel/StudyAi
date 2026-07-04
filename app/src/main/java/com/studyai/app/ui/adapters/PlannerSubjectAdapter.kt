package com.studyai.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studyai.app.database.entities.PlannerSubject
import com.studyai.app.databinding.ItemNoteBinding
import com.studyai.app.utils.DateUtils

class PlannerSubjectAdapter(
    private val subjects: List<PlannerSubject>,
    private val onDeleteClick: (PlannerSubject) -> Unit
) : RecyclerView.Adapter<PlannerSubjectAdapter.SubjectViewHolder>() {

    inner class SubjectViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subject: PlannerSubject) {
            binding.noteItemTitle.text = subject.name
            val daysLeft = DateUtils.daysUntil(subject.examDate)
            binding.noteItemContent.text = "Exam Date: ${DateUtils.formatDateSimple(subject.examDate)}\nDays Left: $daysLeft"
            binding.noteItemDelete.setOnClickListener {
                onDeleteClick(subject)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(subjects[position])
    }

    override fun getItemCount(): Int = subjects.size
}
