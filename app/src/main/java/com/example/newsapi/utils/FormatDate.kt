package com.example.newsapi.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun convertDateFormat(apiDate: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val outputFormatter = DateTimeFormatter.ofPattern("dd MMM, yyyy", Locale("id", "ID"))

    val dateTime = LocalDateTime.parse(apiDate, inputFormatter)
    return outputFormatter.format(dateTime)
}