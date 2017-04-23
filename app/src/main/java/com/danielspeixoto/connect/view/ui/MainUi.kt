package com.danielspeixoto.connect.view.ui

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.view.activity.LoginActivity
import com.danielspeixoto.connect.view.activity.MainActivity
import com.danielspeixoto.connect.view.activity.SignUpActivity
import org.jetbrains.anko.*

/**
 * Created by danielspeixoto on 4/22/17.
 */
class MainUi : AnkoComponent<MainActivity> {

    private val HAS_NO_ACCOUNT: Int = 1
    private val  HAS_ACCOUNT: Int = 2

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {
            lparams(width = matchParent, height = matchParent)
            padding = dip(8)
            button(App.getStringResource(R.string.has_no_account)) {
                id = HAS_NO_ACCOUNT
                onClick {
                    startActivity<SignUpActivity>()
                }
                backgroundColor = Color.WHITE
            }.lparams(width = matchParent) {
                    bottomMargin =  dip(8)
                    alignParentBottom()
                    alignParentStart()
                }
            button(App.getStringResource(R.string.has_account)) {
                id = HAS_ACCOUNT
                onClick {
                    startActivity<LoginActivity>()
                }
                backgroundColor = Color.WHITE
            }.lparams(width = matchParent) {
                above(HAS_NO_ACCOUNT)
                bottomMargin =  dip(8)
                alignParentStart()
            }
            imageView(ContextCompat.getDrawable(ctx, R.mipmap.ic_launcher)) {
                maxHeight = dip(200)
                maxWidth = dip(200)
                minimumHeight = dip(150)
                minimumWidth = dip(150)
                scaleType = ImageView.ScaleType.FIT_CENTER
            }.lparams {
                above(HAS_ACCOUNT)
                bottomMargin = dip(50)
                topMargin = dip(50)
                alignParentTop()
                centerHorizontally()
            }
        }
    }
}