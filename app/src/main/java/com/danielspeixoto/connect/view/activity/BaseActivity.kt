package com.danielspeixoto.connect.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Toast
import butterknife.ButterKnife
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.module.ActivityBase

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

    protected fun onCreate(savedInstanceState: Bundle?, layout: Int) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        ButterKnife.bind(this)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    override fun goToActivity(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }
}
