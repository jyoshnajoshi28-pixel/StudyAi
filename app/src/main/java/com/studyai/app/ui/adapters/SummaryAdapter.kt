package com.studyai.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studyai.app.database.entities.Summary
import com.studyai.app.databinding.ItemNoteBinding

class SummaryAdapter(
    private val summaries: List<Summary>,
    private val onDeleteClick: (Summary) -> Unit
) : RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder>() {

    inner class SummaryViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(summary: Summary) {
            binding.noteItemTitle.text = "Summary"
            binding.noteItemContent.text = summary.shortSummary.take(100) + "..."
            binding.noteItemDelete.setOnClickListener {
                onDeleteClick(summary)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummaryViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SummaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SummaryViewHolder, position: Int) {
        holder.bind(summaries[position])
    }

    override fun getItemCount(): Int = summaries.size
}
