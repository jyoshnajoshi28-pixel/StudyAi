package com.studyai.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studyai.app.database.entities.RevisionNote
import com.studyai.app.databinding.ItemNoteBinding

class RevisionNoteAdapter(
    private val revisionNotes: List<RevisionNote>,
    private val onDeleteClick: (RevisionNote) -> Unit
) : RecyclerView.Adapter<RevisionNoteAdapter.RevisionViewHolder>() {

    inner class RevisionViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(revisionNote: RevisionNote) {
            binding.noteItemTitle.text = revisionNote.subject
            binding.noteItemContent.text = revisionNote.content.take(100) + "..."
            binding.noteItemDelete.setOnClickListener {
                onDeleteClick(revisionNote)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RevisionViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RevisionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RevisionViewHolder, position: Int) {
        holder.bind(revisionNotes[position])
    }

    override fun getItemCount(): Int = revisionNotes.size
}
