package com.radityarin.gamelogue.utils.exts

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.content.ContextCompat


const val FADE_OUT = 0f
const val FADE_IN = 1f
const val CLICK_DELAY_IN_MS = 1000L
const val ANIM_DURATION_50 = 50L

fun View?.fadeInFadeOut(value: Float, duration: Long = ANIM_DURATION_50) {
    if (value > FADE_IN || value < FADE_OUT) return
    this?.let {
        it.context ?: return
        it.animate().alpha(value).setDuration(duration).start()
    }
}

fun View.isViewHide(): Boolean {
    return this.visibility == View.GONE
}

fun View.isViewFadeOut(): Boolean {
    return this.alpha != FADE_IN
}

fun View?.hideView() {
    this?.visibility = View.GONE
}

fun View?.invisibleView() {
    this?.visibility = View.INVISIBLE
}

fun View?.showViewWithCondition(isShow: Boolean) {
    if (isShow) {
        this?.showView()
    } else {
        this?.hideView()
    }
}

fun View?.showInvisibleViewWithCondition(isShow: Boolean) {
    if (isShow) {
        this?.showView()
    } else {
        this?.invisibleView()
    }
}

fun View?.showViewWithCondition(visibility: Int) {
    this?.visibility = visibility
}

fun View?.showView() {
    this?.visibility = View.VISIBLE
}

fun View.hideKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard() {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun View?.avoidDoubleClicks() {
    this ?: return
    if (!this.isClickable) {
        return
    }
    this.isClickable = false
    this.postDelayed({ this.isClickable = true }, CLICK_DELAY_IN_MS)
}

fun View.clickWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}
