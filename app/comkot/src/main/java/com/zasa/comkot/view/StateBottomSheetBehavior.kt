package com.zasa.comkot.view

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomsheet.BottomSheetBehavior

class StateBottomSheetBehavior(view: View) {

    val behavior: BottomSheetBehavior<View> = BottomSheetBehavior.from(view)

    // When height of peek is changed, this field will be called
    val peekHeightLiveData = MutableLiveData<Int>()

    var bottomSheetCallback: BottomSheetBehavior.BottomSheetCallback? = null

    fun clearBottomSheetCallback() = bottomSheetCallback?.run(behavior::removeBottomSheetCallback)

    fun onSlide(callback: (Float) -> Unit) = apply {
        clearBottomSheetCallback()
        bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                callback.invoke(slideOffset)
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }
        }
        bottomSheetCallback?.run(behavior::addBottomSheetCallback)
    }

    fun onStateChanged() { }

    var skipCollapsed: Boolean
        get() = behavior.skipCollapsed
        set(value) {
            behavior.skipCollapsed = value
        }

    fun setPeekHeight(height: Int) = apply {
        behavior.peekHeight = height
        peekHeightLiveData.postValue(height)
    }

    fun setCollapsed() = apply { behavior.state = BottomSheetBehavior.STATE_COLLAPSED }
    fun setExpanded() = apply { behavior.state = BottomSheetBehavior.STATE_EXPANDED }

}
