package com.danielspeixoto.connect.view.custom

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.ViewManager
import android.widget.ImageView
import com.danielspeixoto.connect.R
import org.jetbrains.anko.custom.ankoView

/**
 * Created by danielspeixoto on 4/23/17.
 */
class IconView(ctx: Context) : ImageView(ctx) {

    init {
        background = ContextCompat.getDrawable(context, R.drawable.web_hi_res_512)
        scaleType = ImageView.ScaleType.CENTER_CROP
        adjustViewBounds = true
    }
}

inline fun ViewManager.iconView(theme: Int = 0) = iconView(theme) {}
inline fun ViewManager.iconView(theme: Int = 0, init: IconView.() -> Unit) =
        ankoView(::IconView, theme, init)