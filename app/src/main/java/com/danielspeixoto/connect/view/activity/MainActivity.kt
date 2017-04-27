package com.danielspeixoto.connect.view.activity


import android.graphics.Color
import android.os.Bundle
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.Main
import com.danielspeixoto.connect.presenter.MainPresenter
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.view.custom.iconView
import org.jetbrains.anko.*

class MainActivity : BaseActivity(), Main.View {

    private val HAS_NO_ACCOUNT: Int = 1
    private val  HAS_ACCOUNT: Int = 2

    lateinit private var mPresenter : Main.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter = MainPresenter(this)
        mPresenter.checkIfUserIsSaved()
        super.onCreate(savedInstanceState)
        relativeLayout {
            lparams(width = matchParent, height = matchParent)
            padding = dip(PARAM_LAYOUT)
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
            iconView().lparams(width = matchParent) {
                //TODO Adjust aspect ratio
                alignParentTop()
                above(HAS_ACCOUNT)
                centerHorizontally()
                topMargin = dip(16)
            }
        }
    }
}
