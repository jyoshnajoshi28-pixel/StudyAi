package com.studyai.app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studyai.app.database.entities.TestPaper
import com.studyai.app.databinding.ItemNoteBinding

class TestPaperAdapter(private val testPapers: List<TestPaper>) : RecyclerView.Adapter<TestPaperAdapter.TestViewHolder>() {

    inner class TestViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(testPaper: TestPaper) {
            binding.noteItemTitle.text = testPaper.title
            binding.noteItemContent.text = "Questions: ${testPaper.totalQuestions}\nScore: ${testPaper.userScore}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bind(testPapers[position])
    }

    override fun getItemCount(): Int = testPapers.size
}
