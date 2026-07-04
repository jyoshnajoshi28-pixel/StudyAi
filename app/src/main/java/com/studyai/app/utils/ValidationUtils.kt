package com.studyai.app.utils

object ValidationUtils {
    fun isValidEmail(email: String): Boolean {
        return email.matches(Regex("^[A-Za-z0-9+_.-]+@(.+)$"))
    }

    fun isNotEmpty(vararg strings: String): Boolean {
        return strings.all { it.isNotBlank() }
    }

    fun validateNoteTitle(title: String): Boolean {
        return title.isNotBlank() && title.length <= 200
    }

    fun validateNoteContent(content: String): Boolean {
        return content.isNotBlank() && content.length <= 5000
    }

    fun validateFlashcardQuestion(question: String): Boolean {
        return question.isNotBlank() && question.length <= 500
    }

    fun validateFlashcardAnswer(answer: String): Boolean {
        return answer.isNotBlank() && answer.length <= 1000
    }
}
