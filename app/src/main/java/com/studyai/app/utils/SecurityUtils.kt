package com.studyai.app.utils

import java.security.MessageDigest

object SecurityUtils {
    fun hashString(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(input.toByteArray())
        return hashBytes.joinToString("") { "%02x".format(it) }
    }

    fun validateApiKey(apiKey: String): Boolean {
        return apiKey.length >= 10 && !apiKey.contains(" ")
    }

    fun maskApiKey(apiKey: String): String {
        if (apiKey.length <= 4) return "****"
        return "****" + apiKey.takeLast(4)
    }
}
