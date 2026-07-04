package com.studyai.app.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun formatDate(timestamp: Long): String {
        val formatter = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        return formatter.format(Date(timestamp))
    }

    fun formatDateSimple(timestamp: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatter.format(Date(timestamp))
    }

    fun formatTime(timestamp: Long): String {
        val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return formatter.format(Date(timestamp))
    }

    fun daysUntil(targetDate: Long): Int {
        val now = System.currentTimeMillis()
        val difference = targetDate - now
        return (difference / (1000 * 60 * 60 * 24)).toInt()
    }

    fun isToday(timestamp: Long): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = Date(timestamp)
        val today = Calendar.getInstance()
        return calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
               calendar.get(Calendar.MONTH) == today.get(Calendar.MONTH) &&
               calendar.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH)
    }
}
