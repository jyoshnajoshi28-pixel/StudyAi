package com.studyai.app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studyai.app.database.entities.PlannerSubject
import com.studyai.app.database.entities.PlannerGoal
import com.studyai.app.repositories.PlannerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlannerViewModel(private val plannerRepository: PlannerRepository) : ViewModel() {
    private val _subjects = MutableStateFlow<List<PlannerSubject>>(emptyList())
    val subjects: StateFlow<List<PlannerSubject>> = _subjects

    private val _goals = MutableStateFlow<List<PlannerGoal>>(emptyList())
    val goals: StateFlow<List<PlannerGoal>> = _goals

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadSubjects() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val subjectList = plannerRepository.getAllSubjects()
                _subjects.value = subjectList
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error loading subjects"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addSubject(name: String, examDate: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val subject = PlannerSubject(name = name, examDate = examDate)
                plannerRepository.addSubject(subject)
                loadSubjects()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error adding subject"
            }
        }
    }

    fun deleteSubject(subject: PlannerSubject) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                plannerRepository.deleteSubject(subject)
                loadSubjects()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error deleting subject"
            }
        }
    }

    fun addGoal(subjectId: Int, goal: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val plannerGoal = PlannerGoal(subjectId = subjectId, goal = goal)
                plannerRepository.addGoal(plannerGoal)
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error adding goal"
            }
        }
    }

    fun markGoalComplete(goal: PlannerGoal) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                plannerRepository.updateGoal(goal.copy(isCompleted = true))
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error updating goal"
            }
        }
    }
}
