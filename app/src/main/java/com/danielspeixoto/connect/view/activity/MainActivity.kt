package com.danielspeixoto.connect.view.activity


import android.os.Bundle
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.UserModel
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (UserModel.hasAccountSavedOnDevice()) {
            goToActivity(HomeActivity::class.java)
            finish()
        }
        super.onCreate(savedInstanceState, R.layout.activity_main)
        activity.signUpButton.setOnClickListener { goToActivity(SignUpActivity::class.java) }
        activity.haveAccountButton.setOnClickListener { goToActivity(LoginActivity::class.java) }
    }
}
