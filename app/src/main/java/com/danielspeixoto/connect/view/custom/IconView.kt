package com.danielspeixoto.connect.view.custom

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.ViewManager
import android.widget.ImageView
import com.danielspeixoto.connect.R
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.dip

/**
 * Created by danielspeixoto on 4/23/17.
 */
class IconView(ctx: Context) : ImageView(ctx) {

    init {
        background = ContextCompat.getDrawable(context, R.mipmap.ic_launcher)
        maxHeight = dip(200)
        maxWidth = dip(200)
        minimumHeight = dip(150)
        minimumWidth = dip(150)
        scaleType = ImageView.ScaleType.FIT_XY
    }
}

inline fun ViewManager.iconView(theme: Int = 0) = iconView(theme) {}
inline fun ViewManager.iconView(theme: Int = 0, init: IconView.() -> Unit) =
        ankoView(::IconView, theme, init)