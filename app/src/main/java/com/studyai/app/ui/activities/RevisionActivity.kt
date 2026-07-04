package com.studyai.app.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.studyai.app.database.StudyAiDatabase
import com.studyai.app.databinding.ActivityRevisionBinding
import com.studyai.app.repositories.RevisionRepository
import com.studyai.app.ui.viewmodels.RevisionViewModel
import kotlinx.coroutines.launch

class RevisionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRevisionBinding
    private lateinit var viewModel: RevisionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRevisionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupClickListeners()
        viewModel.loadRevisionNotes()
    }

    private fun setupViewModel() {
        val database = StudyAiDatabase.getInstance(this)
        val repository = RevisionRepository(database)
        viewModel = RevisionViewModel(repository)
    }

    private fun setupClickListeners() {
        binding.revisionGenerate.setOnClickListener {
            val subject = binding.revisionSubject.text.toString().trim()
            val content = binding.revisionContent.text.toString().trim()

            if (subject.isNotEmpty() && content.isNotEmpty()) {
                viewModel.generateRevisionSheet(subject, content)
                binding.revisionSubject.text.clear()
                binding.revisionContent.text.clear()
                Toast.makeText(this, "Revision sheet generated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.revisionBack.setOnClickListener { finish() }
    }
}
