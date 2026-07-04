package com.studyai.app.ui.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.studyai.app.database.StudyAiDatabase
import com.studyai.app.databinding.ActivityTestPapersBinding
import com.studyai.app.repositories.TestPaperRepository
import com.studyai.app.ui.adapters.TestPaperAdapter
import com.studyai.app.ui.viewmodels.TestPaperViewModel
import kotlinx.coroutines.launch

class TestPapersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTestPapersBinding
    private lateinit var viewModel: TestPaperViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestPapersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupSpinners()
        setupClickListeners()
        viewModel.loadTestPapers()
    }

    private fun setupViewModel() {
        val database = StudyAiDatabase.getInstance(this)
        val repository = TestPaperRepository(database)
        viewModel = TestPaperViewModel(repository)
    }

    private fun setupSpinners() {
        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
            arrayOf("MCQ", "Short Answer", "Long Answer", "True/False", "Fill in Blanks"))
        binding.testType.adapter = typeAdapter

        val difficultyAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
            arrayOf("Easy", "Medium", "Hard"))
        binding.testDifficulty.adapter = difficultyAdapter
    }

    private fun setupClickListeners() {
        binding.testGenerate.setOnClickListener {
            val type = binding.testType.selectedItem.toString()
            val difficulty = binding.testDifficulty.selectedItem.toString()
            viewModel.generateTest("General", type, difficulty)
            Toast.makeText(this, "Test generated", Toast.LENGTH_SHORT).show()
        }

        binding.testBack.setOnClickListener { finish() }

        lifecycleScope.launch {
            viewModel.testPapers.collect { papers ->
                val adapter = TestPaperAdapter(papers)
                binding.testRecycler.adapter = adapter
            }
        }
    }
}
