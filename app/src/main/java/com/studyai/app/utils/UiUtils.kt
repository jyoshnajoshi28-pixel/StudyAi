package com.studyai.app.utils

import android.content.Context
import android.widget.Toast

object UiUtils {
    fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }

    fun showLongToast(context: Context, message: String) {
        showToast(context, message, Toast.LENGTH_LONG)
    }

    fun truncateText(text: String, maxLength: Int): String {
        return if (text.length > maxLength) {
            text.take(maxLength) + "..."
        } else {
            text
        }
    }

    fun highlightKeywords(text: String, keywords: List<String>): String {
        var result = text
        keywords.forEach { keyword ->
            result = result.replace(
                keyword,
                "**$keyword**",
                ignoreCase = true
            )
        }
        return result
    }
}
