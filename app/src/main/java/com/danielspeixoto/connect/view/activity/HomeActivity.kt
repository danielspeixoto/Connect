package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.util.ACTIVITY_BORDER
import com.danielspeixoto.connect.view.custom.iconView
import org.jetbrains.anko.centerInParent
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding
import org.jetbrains.anko.relativeLayout

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        relativeLayout {
            padding = dip(ACTIVITY_BORDER)
            iconView().lparams {
                centerInParent()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                UserModel.logOut()
                goToActivity(MainActivity::class.java)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
