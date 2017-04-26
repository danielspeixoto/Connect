package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.util.ACTIVITY_BORDER
import com.danielspeixoto.connect.view.custom.floatingButton
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class HomeActivity : BaseActivity() {

    lateinit var list : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coordinatorLayout {
            padding = dip(ACTIVITY_BORDER)
            list = recyclerView().lparams(width = matchParent, height = matchParent)
            floatingButton {
                //TODO Change resource
                imageResource = R.drawable.ic_save_black_24dp
                onClick {
                   startActivity<CreateVisitorActivity>()
                }
            }.lparams {
                margin = resources.getDimensionPixelSize(R.dimen.fab_margin)
                gravity = Gravity.BOTTOM or GravityCompat.END
            }
        }
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = VisitorAdapter(this)
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
