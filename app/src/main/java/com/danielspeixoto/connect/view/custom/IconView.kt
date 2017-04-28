package com.danielspeixoto.connect.view.custom

import android.content.Context
import android.view.ViewManager
import android.widget.ImageView
import com.danielspeixoto.connect.R
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.imageResource

/**
 * Created by danielspeixoto on 4/23/17.
 */
class IconView(ctx: Context) : ImageView(ctx) {

    init {
        imageResource = R.drawable.web_hi_res_512
        scaleType = ImageView.ScaleType.FIT_CENTER
        adjustViewBounds = true
    }
}

inline fun ViewManager.iconView(theme: Int = 0) = iconView(theme) {}
inline fun ViewManager.iconView(theme: Int = 0, init: IconView.() -> Unit) =
        ankoView(::IconView, theme, init)