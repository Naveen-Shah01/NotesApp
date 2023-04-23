package com.example.splash.utils

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS

const val DATABASE_NAME = "notes_database"
const val EXTRA_NOTE_DETAILS = "notes_details"

fun View.hideKeyboard() =
    (context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        windowToken, HIDE_NOT_ALWAYS
    )