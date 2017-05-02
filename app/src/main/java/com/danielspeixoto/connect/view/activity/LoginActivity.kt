package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.text.InputType
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.Login
import com.danielspeixoto.connect.presenter.LoginPresenter
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.util.content
import com.danielspeixoto.connect.view.custom.EditField
import com.danielspeixoto.connect.view.custom.editField
import com.danielspeixoto.connect.view.custom.floatingButton
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout

class LoginActivity : BaseActivity(), Login.View {

    lateinit var usernameEdit: EditField
    lateinit var passEdit: EditField
    private var mPresenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coordinatorLayout {
            lparams(width = matchParent, height = matchParent)
            padding = dip(PARAM_LAYOUT)
            relativeLayout {
                verticalLayout {
                    usernameEdit = editField {
                        hint = getString(R.string.username)
                        inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
                    }
                    passEdit = editField {
                        hint = getString(R.string.password)
                        inputType = EditorInfo.TYPE_CLASS_TEXT or EditorInfo.TYPE_TEXT_VARIATION_WEB_PASSWORD
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    centerInParent()
                }
            }.lparams(width = matchParent, height = matchParent)
            floatingButton {
                imageResource = android.R.drawable.ic_dialog_email
                onClick {
                    toast(App.getStringResource(R.string.loading))
                    mPresenter.logIn(usernameEdit.content, passEdit.content)
                }
            }.lparams {
                margin = resources.getDimensionPixelSize(R.dimen.fab_margin)
                gravity = Gravity.BOTTOM or GravityCompat.END
            }
        }
    }

}