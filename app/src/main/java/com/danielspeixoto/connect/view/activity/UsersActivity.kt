package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.Users
import com.danielspeixoto.connect.presenter.UsersPresenter
import com.danielspeixoto.connect.view.custom.floatingButton
import com.danielspeixoto.connect.view.recycler.adapter.UserAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by danielspeixoto on 5/3/17.
 */
class UsersActivity : LoggedActivity(), Users.View {

    lateinit var list: RecyclerView
    private var usersAdapter = UserAdapter(this)
    lateinit private var presenter: Users.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = UsersPresenter(this)
        coordinatorLayout {
            list = recyclerView {
                layoutManager = LinearLayoutManager(this@UsersActivity) as RecyclerView.LayoutManager?
                adapter = usersAdapter
            }.lparams(width = matchParent, height = matchParent)
            floatingButton {
                imageResource = R.drawable.ic_person_add_white_24dp
                onClick {
                   startActivity<CreateUserActivity>()
                }
            }.lparams {
                margin = resources.getDimensionPixelSize(R.dimen.fab_margin)
                gravity = Gravity.BOTTOM or GravityCompat.END
            }
        }
        presenter = UsersPresenter(this)
        presenter.adapter = usersAdapter
    }

    override fun onResume() {
        super.onResume()
        presenter.syncItems()
    }
}