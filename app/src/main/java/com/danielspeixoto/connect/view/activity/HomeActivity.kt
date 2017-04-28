package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.Home
import com.danielspeixoto.connect.presenter.HomePresenter
import com.danielspeixoto.connect.view.custom.floatingButton
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class HomeActivity : BaseActivity(), Home.View {

    lateinit var list : RecyclerView
    lateinit private var mPresenter : Home.Presenter
    private var mAdapter = VisitorAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coordinatorLayout {
            list = recyclerView().lparams(width = matchParent, height = matchParent)
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
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = mAdapter
        mPresenter = HomePresenter(this)
        mPresenter.mAdapter = mAdapter
    }

    override fun onResume() {
        super.onResume()
        mPresenter.syncItems()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mPresenter.onMenuItemSelected(item.itemId)
        return super.onOptionsItemSelected(item)
    }
}
