package com.danielspeixoto.connect.view.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.text.InputType
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.Login
import com.danielspeixoto.connect.presenter.LoginPresenter
import com.danielspeixoto.connect.util.MessageView
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.util.content
import com.danielspeixoto.connect.view.custom.EditField
import com.danielspeixoto.connect.view.custom.editField
import com.danielspeixoto.connect.view.custom.floatingButton
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout

class LoginActivity : BaseActivity(), Login.View, MessageView {

    lateinit var usernameEdit: EditField
    lateinit var passEdit: EditField

    override var messageView: TextView? = null
    private var mPresenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coordinatorLayout {
            lparams(width = matchParent, height = matchParent)
            scrollView {
                relativeLayout {
                    padding = dip(PARAM_LAYOUT)
                    verticalLayout {
                        imageView {
                            imageResource = R.drawable.ic_account_circle
                            Gravity.CENTER
                            maxWidth = PARAM_LAYOUT
                            padding = dip(PARAM_LAYOUT * 4)
                        }
                        messageView = textView {
                            padding = PARAM_LAYOUT * 4
                            textColor = Color.RED
                            textSize = (PARAM_LAYOUT * 2).toFloat()
                            visibility = View.GONE
                            gravity = Gravity.CENTER_HORIZONTAL
                        }.lparams(width = matchParent)
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
            }
            floatingButton {
                imageResource = R.drawable.ic_input
                onClick {
                    login()
                }
            }.lparams {
                margin = resources.getDimensionPixelSize(R.dimen.fab_margin)
                gravity = Gravity.BOTTOM or GravityCompat.END
            }
        }

        passEdit.setOnKeyListener { view, keyCode, event ->
            if((event.getAction() == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                login()
                true
            }
            false
        }
    }

    fun login() {
        mPresenter.logIn(usernameEdit.content.trim(), passEdit.content)
    }

    override fun setMessageViewText(message: String) {
        setMessageViewContent(message)
    }

}