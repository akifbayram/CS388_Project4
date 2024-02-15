package com.codepath.cs388_project4.util

class LanguageConverter {
    private val languageMap = mapOf(
        "en" to "English",
        "es" to "Spanish",
        "fr" to "French",
        "de" to "German",
        "it" to "Italian",
        "ja" to "Japanese",
        "ko" to "Korean",
        "zh" to "Chinese",
        "hi" to "Hindi",
        "ru" to "Russian"
    )

    fun getLanguageName(id: String): String? {
        return languageMap[id]
    }
}