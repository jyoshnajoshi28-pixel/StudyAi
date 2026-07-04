package com.studyai.app.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.studyai.app.database.StudyAiDatabase
import com.studyai.app.databinding.ActivitySummaryBinding
import com.studyai.app.repositories.SummaryRepository
import com.studyai.app.ui.viewmodels.SummaryViewModel
import kotlinx.coroutines.launch

class SummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySummaryBinding
    private lateinit var viewModel: SummaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupClickListeners()
        viewModel.loadSummaries()
    }

    private fun setupViewModel() {
        val database = StudyAiDatabase.getInstance(this)
        val repository = SummaryRepository(database)
        viewModel = SummaryViewModel(repository)
    }

    private fun setupClickListeners() {
        binding.summaryGenerate.setOnClickListener {
            val text = binding.summaryInput.text.toString().trim()

            if (text.isNotEmpty()) {
                viewModel.generateSummary(text)
                binding.summaryInput.text.clear()
                Toast.makeText(this, "Summary generated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show()
            }
        }

        binding.summaryBack.setOnClickListener { finish() }
    }
}
