package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import android.widget.EditText
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.module.SignUp
import com.danielspeixoto.connect.presenter.SignUpPresenter
import com.danielspeixoto.connect.util.checkTextEmpty
import com.danielspeixoto.connect.util.content
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.content_sign_up.*

class SignUpActivity : BaseActivity(), SignUp.View {

    var nameEdit: EditText? = null
    var usernameEdit: EditText? = null
    var passEdit: EditText? = null
    var confirmPassEdit: EditText? = null
    private var mPresenter: SignUp.Presenter? = null
    private val user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState, R.layout.activity_sign_up)
        nameEdit = activity.nameEdit
        usernameEdit = activity.usernameEdit
        passEdit = activity.passEdit
        confirmPassEdit = activity.confirmPassEdit
        mPresenter = SignUpPresenter(this)
        activity.saveFab.setOnClickListener {
            if (nameEdit!!.checkTextEmpty()) {
                nameEdit!!.requestFocus()
                showMessage("Name must be filled")
            } else if (usernameEdit!!.checkTextEmpty()) {
                usernameEdit!!.requestFocus()
                showMessage("Email must be filled")
            } else if (passEdit!!.checkTextEmpty()) {
                passEdit!!.requestFocus()
                showMessage("Must have a password")
            } else if (passEdit!!.content != confirmPassEdit!!.content) {
                confirmPassEdit!!.requestFocus()
                showMessage("Passwords must match")
            } else {
                user.name = nameEdit!!.content
                user.username = usernameEdit!!.content
                user.password = passEdit!!.content
                mPresenter!!.signUp(user)
            }
        }
    }
}