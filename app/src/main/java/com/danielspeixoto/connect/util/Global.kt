package com.danielspeixoto.connect.util

import android.widget.TextView

/**
 * Created by danielspeixoto on 4/21/17.
 */
val EMPTY_STRING = ""

fun TextView.checkTextEmpty() = this.text.toString() == EMPTY_STRING

fun TextView.getStringText() = this.text.toString()

fun TextView.clear() {
    this.text = EMPTY_STRING
}