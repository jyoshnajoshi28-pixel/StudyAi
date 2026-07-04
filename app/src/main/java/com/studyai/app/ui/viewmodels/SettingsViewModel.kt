package com.studyai.app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studyai.app.database.entities.AppSettings
import com.studyai.app.repositories.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {
    private val _settings = MutableStateFlow<AppSettings?>(null)
    val settings: StateFlow<AppSettings?> = _settings

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val appSettings = settingsRepository.getSettings() ?: AppSettings()
                _settings.value = appSettings
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error loading settings"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateApiKey(apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currentSettings = _settings.value ?: AppSettings()
                val updated = currentSettings.copy(apiKey = apiKey)
                settingsRepository.updateSettings(updated)
                _settings.value = updated
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error updating API key"
            }
        }
    }

    fun updateTheme(theme: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currentSettings = _settings.value ?: AppSettings()
                val updated = currentSettings.copy(theme = theme)
                settingsRepository.updateSettings(updated)
                _settings.value = updated
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error updating theme"
            }
        }
    }

    fun updateFontSize(fontSize: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currentSettings = _settings.value ?: AppSettings()
                val updated = currentSettings.copy(fontSize = fontSize)
                settingsRepository.updateSettings(updated)
                _settings.value = updated
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error updating font size"
            }
        }
    }

    fun clearAllData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                settingsRepository.clearSettings()
                _settings.value = AppSettings()
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Error clearing data"
            }
        }
    }
}
