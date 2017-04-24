package com.danielspeixoto.connect.view.custom

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.view.ViewManager
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.util.App
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.custom.ankoView

/**
 * Created by danielspeixoto on 4/24/17.
 */
class FloatingButton(ctx: Context) : FloatingActionButton(ctx) {

    init {
        backgroundColor = ContextCompat.getColor(App.context!!, R.color.colorAccent)
    }
}

inline fun ViewManager.floatingButton(theme: Int = 0) = floatingButton(theme) {}
inline fun ViewManager.floatingButton(theme: Int = 0, init : FloatingButton.()-> Unit) =
        ankoView(::FloatingButton, theme, init)