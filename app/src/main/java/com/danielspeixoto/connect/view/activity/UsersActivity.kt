package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.Users
import com.danielspeixoto.connect.presenter.UsersPresenter
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.view.custom.floatingButton
import com.danielspeixoto.connect.view.recycler.adapter.UserAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by danielspeixoto on 5/3/17.
 */
class UsersActivity : BaseActivity(), Users.View {

    lateinit var list: RecyclerView
    private var adapter = UserAdapter(this)
    lateinit private var presenter: Users.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = UsersPresenter(this)
        coordinatorLayout {
            padding = dip(PARAM_LAYOUT)
            list = recyclerView {
                layoutManager = LinearLayoutManager(this@UsersActivity)
                adapter = adapter
            }.lparams(width = matchParent, height = matchParent)
            floatingButton {
                imageResource = R.drawable.ic_save_black_24dp
                onClick {
                   // TODO Sent to create users
                }
            }.lparams {
                margin = resources.getDimensionPixelSize(R.dimen.fab_margin)
                gravity = Gravity.BOTTOM or GravityCompat.END
            }
        }
        presenter = UsersPresenter(this)
        presenter.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.syncItems()
    }
}