package com.danielspeixoto.connect.view.activity

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.MenuItem
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.Home
import com.danielspeixoto.connect.presenter.HomePresenter
import com.danielspeixoto.connect.view.custom.floatingButton
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.drawerLayout

class HomeActivity : BaseActivity(), Home.View {

    lateinit var list: RecyclerView
    lateinit var drawer : DrawerLayout
    lateinit var drawerToggle : ActionBarDrawerToggle
    lateinit private var mPresenter: Home.Presenter
    private var mAdapter = VisitorAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        drawer = drawerLayout {
            coordinatorLayout {
                list = recyclerView {
                    layoutManager = LinearLayoutManager(this@HomeActivity)
                    adapter = mAdapter
                }.lparams(width = matchParent, height = matchParent)
                floatingButton {
                    imageResource = R.drawable.ic_person_add_white_24dp
                    onClick {
                        startActivity<CreateVisitorActivity>()
                    }
                }.lparams {
                    margin = resources.getDimensionPixelSize(R.dimen.fab_margin)
                    gravity = Gravity.BOTTOM or GravityCompat.END
                }
            }
        }
        drawerToggle = ActionBarDrawerToggle(this,
                                             drawer,
                                             R.string.accept,
                                             R.string.decline)
        drawer.addDrawerListener(drawerToggle)
        mPresenter = HomePresenter(this)
        mPresenter.mAdapter = mAdapter
    }

    override fun onResume() {
        super.onResume()
        mPresenter.syncItems()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }

}
