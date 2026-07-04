package com.studyai.app.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.studyai.app.database.StudyAiDatabase
import com.studyai.app.databinding.ActivityPlannerBinding
import com.studyai.app.repositories.PlannerRepository
import com.studyai.app.ui.adapters.PlannerSubjectAdapter
import com.studyai.app.ui.viewmodels.PlannerViewModel
import kotlinx.coroutines.launch

class PlannerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlannerBinding
    private lateinit var viewModel: PlannerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupClickListeners()
        viewModel.loadSubjects()
    }

    private fun setupViewModel() {
        val database = StudyAiDatabase.getInstance(this)
        val repository = PlannerRepository(database)
        viewModel = PlannerViewModel(repository)
    }

    private fun setupClickListeners() {
        binding.plannerAdd.setOnClickListener {
            val subject = binding.plannerSubjectInput.text.toString().trim()

            if (subject.isNotEmpty()) {
                val examDate = System.currentTimeMillis() + (30 * 24 * 60 * 60 * 1000)
                viewModel.addSubject(subject, examDate)
                binding.plannerSubjectInput.text.clear()
                Toast.makeText(this, "Subject added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter subject name", Toast.LENGTH_SHORT).show()
            }
        }

        binding.plannerBack.setOnClickListener { finish() }

        lifecycleScope.launch {
            viewModel.subjects.collect { subjects ->
                val adapter = PlannerSubjectAdapter(subjects) { subject ->
                    viewModel.deleteSubject(subject)
                    Toast.makeText(this@PlannerActivity, "Subject deleted", Toast.LENGTH_SHORT).show()
                }
                binding.plannerRecycler.adapter = adapter
            }
        }
    }
}
