package com.danielspeixoto.connect.view.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.danielspeixoto.connect.contract.ActivityBase

/**
 * Created by danielspeixoto on 13/11/16.
 */
abstract class BaseActivity : AppCompatActivity(), ActivityBase.View {
    val TAG = javaClass.simpleName

    override val activity: BaseActivity
        get() = this

    fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun goToActivity(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}
