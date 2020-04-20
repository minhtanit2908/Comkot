package com.zasa.comkot.view

import android.view.View
import android.view.ViewTreeObserver
import androidx.core.widget.NestedScrollView

fun View.onGlobalLayout(callback: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            callback()
        }
    })
}

fun View.beInvisibleIf(beInvisible: Boolean) = if (beInvisible) beInvisible() else beVisible()

fun View.beVisibleIf(beVisible: Boolean) = if (beVisible) beVisible() else beGone()

fun View.beGoneIf(beGone: Boolean) = beVisibleIf(!beGone)

fun View.beGone() = run { visibility = View.GONE }

fun View.beVisible() = run { visibility = View.VISIBLE }

fun View.beInvisible() = run { visibility = View.INVISIBLE }

fun goneViews(vararg views: View) = views.forEach { it.beGone() }

fun disableViews(vararg views: View) = views.forEach { it.isEnabled = false }

fun enableViews(vararg views: View) = views.forEach { it.isEnabled = true }

fun invisibleViews(vararg views: View) = views.forEach { it.beInvisible() }

fun visibleViews(vararg views: View) = views.forEach { it.beVisible() }

fun NestedScrollView.scrollToTop() = run {
    fullScroll(View.FOCUS_UP)
    scrollTo(0, 0)
}
