package com.tanay.flickrdemo.ui.launch.search

import android.app.Activity
import android.content.Context

interface SearchNavigator {

    fun activity(): Activity

    fun context(): Context

    fun hideKeyboard()
}