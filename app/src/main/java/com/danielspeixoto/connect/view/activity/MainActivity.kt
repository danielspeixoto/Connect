package com.danielspeixoto.connect.view.activity


import android.os.Bundle
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.view.ui.MainUi
import org.jetbrains.anko.setContentView

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (UserModel.hasAccountSavedOnDevice()) {
            goToActivity(HomeActivity::class.java)
            finish()
        }
        super.onCreate(savedInstanceState)
        MainUi().setContentView(this)
    }
}
