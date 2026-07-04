package com.studyai.app.ui.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.studyai.app.database.StudyAiDatabase
import com.studyai.app.databinding.ActivitySettingsBinding
import com.studyai.app.repositories.SettingsRepository
import com.studyai.app.ui.viewmodels.SettingsViewModel
import kotlinx.coroutines.launch

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupSpinner()
        setupClickListeners()
        viewModel.loadSettings()
    }

    private fun setupViewModel() {
        val database = StudyAiDatabase.getInstance(this)
        val repository = SettingsRepository(database)
        viewModel = SettingsViewModel(repository)
    }

    private fun setupSpinner() {
        val themeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
            arrayOf("Light", "Dark"))
        binding.settingsTheme.adapter = themeAdapter
    }

    private fun setupClickListeners() {
        binding.settingsSave.setOnClickListener {
            val apiKey = binding.settingsApiKey.text.toString().trim()
            val theme = binding.settingsTheme.selectedItem.toString()
            val fontSize = binding.settingsFontSize.progress + 12

            if (apiKey.isNotEmpty()) {
                viewModel.updateApiKey(apiKey)
                viewModel.updateTheme(theme)
                viewModel.updateFontSize(fontSize)
                Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please enter API key", Toast.LENGTH_SHORT).show()
            }
        }

        binding.settingsClearData.setOnClickListener {
            viewModel.clearAllData()
            Toast.makeText(this, "All data cleared", Toast.LENGTH_SHORT).show()
        }

        binding.settingsBack.setOnClickListener { finish() }

        lifecycleScope.launch {
            viewModel.settings.collect { settings ->
                if (settings != null) {
                    binding.settingsApiKey.setText(settings.apiKey)
                    binding.settingsFontSize.progress = settings.fontSize - 12
                }
            }
        }
    }
}
