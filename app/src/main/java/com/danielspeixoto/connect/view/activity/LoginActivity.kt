package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import android.widget.EditText
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.module.Login
import com.danielspeixoto.connect.presenter.LoginPresenter
import com.danielspeixoto.connect.util.getStringText
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*

class LoginActivity : BaseActivity(), Login.View {

    var usernameEdit: EditText? = null
    var passEdit: EditText? = null
    private var mPresenter: Login.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState, R.layout.activity_login)
        usernameEdit = activity.usernameEdit
        passEdit = activity.passEdit
        mPresenter = LoginPresenter(this)
        activity.fab.setOnClickListener {
            mPresenter!!.logIn(usernameEdit!!.getStringText(),
                    passEdit!!.getStringText())
        }
    }

}