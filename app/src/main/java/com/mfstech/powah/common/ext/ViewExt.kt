package com.mfstech.powah.common.ext

import android.view.View

fun View.asVisible() {
    this.visibility = View.VISIBLE
}

fun View.asGone() {
    this.visibility = View.GONE
}

fun View.isVisibleWhen(predicate: () -> Boolean) {
    this.visibility = if (predicate()) View.VISIBLE else View.GONE
}

