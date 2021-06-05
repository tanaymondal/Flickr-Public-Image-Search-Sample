package com.tanay.flickrdemo.utils

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.tanay.flickrdemo.App


fun String.toast() {
    Toast.makeText(App.getContext(), this, Toast.LENGTH_SHORT).show()
}

fun View.hideKeyboard() {
    val inputMethodManager =
        this.context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
}