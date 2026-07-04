package com.studyai.app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studyai.app.database.entities.RevisionNote
import com.studyai.app.database.entities.Summary
import com.studyai.app.repositories.RevisionRepository
import com.studyai.app.repositories.SummaryRepository
import com.studyai.app.services.RevisionService
import com.studyai.app.services.SummaryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RevisionViewModel(private val revisionRepository: RevisionRepository) : ViewModel() {
    private val revisionService = RevisionService()
    private val _revisionNotes = MutableStateFlow<List<RevisionNote>>(emptyList())
    val revisionNotes: StateFlow<List<RevisionNote>> = _revisionNotes

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadRevisionNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val notes = revisionRepository.getAllRevisionNotes()
                _revisionNotes.value = notes
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error loading revision notes"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun generateRevisionSheet(subject: String, content: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val sheet = revisionService.generateRevisionSheet(subject, content)
                val revisionNote = RevisionNote(
                    subject = subject,
                    content = sheet.keyPoints.joinToString("\n") + "\n\nFormulas:\n" + sheet.formulas.joinToString("\n")
                )
                revisionRepository.addRevisionNote(revisionNote)
                loadRevisionNotes()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error generating revision sheet"
            } finally {
                _isLoading.value = false
            }
        }
    }
}

class SummaryViewModel(private val summaryRepository: SummaryRepository) : ViewModel() {
    private val summaryService = SummaryService()
    private val _summaries = MutableStateFlow<List<Summary>>(emptyList())
    val summaries: StateFlow<List<Summary>> = _summaries

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadSummaries() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val summaries = summaryRepository.getAllSummaries()
                _summaries.value = summaries
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error loading summaries"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun generateSummary(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val result = summaryService.generateSummary(text)
                val summary = Summary(
                    originalText = text,
                    shortSummary = result.shortSummary,
                    mediumSummary = result.mediumSummary,
                    detailedSummary = result.detailedSummary
                )
                summaryRepository.addSummary(summary)
                loadSummaries()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error generating summary"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
