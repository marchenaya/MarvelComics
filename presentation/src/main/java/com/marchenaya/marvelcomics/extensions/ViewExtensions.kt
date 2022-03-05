package com.marchenaya.marvelcomics.extensions

import android.view.MenuItem
import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun MenuItem.hide() {
    isVisible = false
}