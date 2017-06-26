package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import com.danielspeixoto.connect.model.UserModel
import org.jetbrains.anko.startActivity

/**
 * Created by daniel on 23/06/17.
 */
abstract class LoggedActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!UserModel.isLogged) {
            startActivity<MainActivity>()
            finish()
        }
    }
}
