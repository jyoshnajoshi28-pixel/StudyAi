package com.studyai.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studyai.app.database.entities.Flashcard
import com.studyai.app.databinding.ItemNoteBinding

class FlashcardListAdapter(
    private val flashcards: List<Flashcard>,
    private val onDeleteClick: (Flashcard) -> Unit
) : RecyclerView.Adapter<FlashcardListAdapter.FlashcardViewHolder>() {

    inner class FlashcardViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(flashcard: Flashcard) {
            binding.noteItemTitle.text = flashcard.question
            binding.noteItemContent.text = flashcard.answer.take(100) + "..."
            binding.noteItemDelete.setOnClickListener {
                onDeleteClick(flashcard)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashcardViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlashcardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlashcardViewHolder, position: Int) {
        holder.bind(flashcards[position])
    }

    override fun getItemCount(): Int = flashcards.size
}
