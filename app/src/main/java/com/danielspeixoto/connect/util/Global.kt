package com.danielspeixoto.connect.util

import android.widget.TextView

/**
 * Created by danielspeixoto on 4/21/17.
 */
val EMPTY_STRING = ""

fun TextView.checkTextEmpty() = this.text.toString() == EMPTY_STRING

val TextView.content
 get() = this.text.toString()

fun TextView.clear() {
    this.text = EMPTY_STRING
}

val String.integer
    get() = Integer.valueOf(this)

val ACTIVITY_BORDER = 8