package com.example.utils

import android.content.SharedPreferences

class FashionPref(preferences: SharedPreferences) {

    var lastWord: String by SharedPreferencesDelegate(preferences, LAST_WORD, "")
    var countWord: Int by SharedPreferencesDelegate(preferences, COUNT_WORD, 0)

    companion object {
        private const val LAST_WORD = "last_word"
        private const val COUNT_WORD = "count_word"
    }
}