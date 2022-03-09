package com.marchenaya.marvelcomics.extensions

import android.content.Context
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun MenuItem.show() {
    isVisible = true
}

fun MenuItem.hide() {
    isVisible = false
}

@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}