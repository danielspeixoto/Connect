package com.danielspeixoto.connect.view.custom

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.ViewManager
import android.widget.ImageView
import com.danielspeixoto.connect.R
import org.jetbrains.anko._LinearLayout
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.dip
import org.jetbrains.anko.imageView
import org.jetbrains.anko.margin

/**
 * Created by danielspeixoto on 4/23/17.
 */
class IconView(ctx: Context) : _LinearLayout(ctx) {

    init {
        imageView {
            background = ContextCompat.getDrawable(context, R.mipmap.ic_launcher)
            maxHeight = dip(200)
            maxWidth = dip(200)
            minimumHeight = dip(150)
            minimumWidth = dip(150)
            scaleType = ImageView.ScaleType.FIT_CENTER
        }.lparams {
            margin = dip(100)
        }
    }
}

inline fun ViewManager.iconView(theme: Int = 0) = iconView(theme) {}
inline fun ViewManager.iconView(theme: Int = 0, init: IconView.() -> Unit) =
        ankoView(::IconView, theme, init)