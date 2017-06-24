package com.danielspeixoto.connect.util

import android.content.Context
import android.support.multidex.MultiDexApplication
import android.util.Log
import android.widget.Toast
import org.jetbrains.anko.runOnUiThread

/**
 * Created by danielspeixoto on 2/15/17.
 */

class App : MultiDexApplication() {


    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {

        lateinit var context: Context

        fun getStringResource(resId: Int) = context.getString(resId)

        fun getDimenResource(resId: Int) = context.resources.getDimension(resId)

        fun showMessage(message: String) {
            context.runOnUiThread {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }

        fun log(s: String) {
            Log.d("Testing", s)
        }

    }
}
