package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.module.SignUp
import com.danielspeixoto.connect.presenter.SignUpPresenter
import com.danielspeixoto.connect.util.ACTIVITY_BORDER
import com.danielspeixoto.connect.util.checkTextEmpty
import com.danielspeixoto.connect.util.content
import com.danielspeixoto.connect.view.custom.editField
import com.danielspeixoto.connect.view.custom.floatingButton
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout

class SignUpActivity : BaseActivity(), SignUp.View {

    lateinit var nameEdit: EditText
    lateinit var usernameEdit: EditText
    lateinit var passEdit: EditText
    lateinit var confirmPassEdit: EditText
    lateinit private var mPresenter: SignUp.Presenter
    private val user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = SignUpPresenter(this)
        coordinatorLayout {
            lparams(width = matchParent, height = matchParent)
            padding = dip(ACTIVITY_BORDER)
            scrollView {
                verticalLayout {
                    nameEdit = editField {
                        hint = getString(R.string.name)
                        inputType = EditorInfo.TYPE_TEXT_VARIATION_PERSON_NAME
                    }
                    usernameEdit = editField {
                        hint = getString(R.string.username)
                        inputType = EditorInfo.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
                    }
                    passEdit = editField {
                        hint = getString(R.string.password)
                        inputType = EditorInfo.TYPE_CLASS_TEXT or EditorInfo.TYPE_TEXT_VARIATION_WEB_PASSWORD
                    }
                    confirmPassEdit = editField {
                        hint = getString(R.string.confirm_your_password)
                        inputType = EditorInfo.TYPE_CLASS_TEXT or EditorInfo.TYPE_TEXT_VARIATION_WEB_PASSWORD
                    }
                }.lparams(width = matchParent) {
                    gravity = Gravity.CENTER
                }
            }.lparams(width = matchParent, height = matchParent)
            floatingButton {
                imageResource = R.drawable.ic_save_black_24dp
                onClick {
                    if (nameEdit.checkTextEmpty()) {
                        nameEdit.requestFocus()
                        toast(getString(R.string.name_must_fill))
                    } else if (usernameEdit.checkTextEmpty()) {
                        usernameEdit.requestFocus()
                        toast(getString(R.string.username_must_fill))
                    } else if (passEdit.checkTextEmpty()) {
                        passEdit.requestFocus()
                        toast(getString(R.string.password_must_fill))
                    } else if (passEdit.content != confirmPassEdit.content) {
                        confirmPassEdit.requestFocus()
                        toast(getString(R.string.password_must_match))
                    } else {
                        user.name = nameEdit.content
                        user.username = usernameEdit.content
                        user.password = passEdit.content
                        mPresenter.signUp(user)
                    }
                }
            }.lparams {
                margin = resources.getDimensionPixelSize(R.dimen.fab_margin)
                gravity = Gravity.BOTTOM or GravityCompat.END
            }
        }
    }
}