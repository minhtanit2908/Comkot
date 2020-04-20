package com.zasa.comkot.view

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View?.hideSoftKeyboard() {
    this ?: return
    val inputMethod = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethod.hideSoftInputFromWindow(windowToken, 0)
}

fun View?.showSoftKeyboard() {
    this ?: return
    val inputMethod = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethod.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}
