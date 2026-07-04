package com.studyai.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.studyai.app.database.StudyAiDatabase
import com.studyai.app.repositories.*
import com.studyai.app.ui.viewmodels.*

class ViewModelFactory(private val database: StudyAiDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            NoteViewModel::class.java -> NoteViewModel(NoteRepository(database)) as T
            ChatViewModel::class.java -> ChatViewModel(ChatRepository(database)) as T
            FlashcardViewModel::class.java -> FlashcardViewModel(FlashcardRepository(database)) as T
            TestPaperViewModel::class.java -> TestPaperViewModel(TestPaperRepository(database)) as T
            PlannerViewModel::class.java -> PlannerViewModel(PlannerRepository(database)) as T
            RevisionViewModel::class.java -> RevisionViewModel(RevisionRepository(database)) as T
            SummaryViewModel::class.java -> SummaryViewModel(SummaryRepository(database)) as T
            SettingsViewModel::class.java -> SettingsViewModel(SettingsRepository(database)) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
