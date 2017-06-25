package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.danielspeixoto.connect.contract.Connected
import com.danielspeixoto.connect.presenter.ConnectedPresenter
import com.danielspeixoto.connect.view.recycler.adapter.MutableAdapter
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.verticalLayout

/**
 * Created by daniel on 22/06/17.
 */
class ConnectedActivity : LoggedActivity(), Connected.View {

    lateinit var list: RecyclerView
    lateinit var refreshLayout : SwipeRefreshLayout
    private var visitorAdapter = VisitorAdapter(this)
    lateinit private var presenter: Connected.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            verticalLayout {
                refreshLayout = swipeRefreshLayout {
                    list = recyclerView {
                        layoutManager = LinearLayoutManager(this@ConnectedActivity)
                        adapter = visitorAdapter
                    }.lparams(width = matchParent, height = matchParent)
                }
            }
        presenter = ConnectedPresenter(this)
        presenter.adapter = visitorAdapter
        presenter.refreshLayout = refreshLayout
        refreshLayout.setOnRefreshListener {
            presenter.syncItems()
            visitorAdapter.status = MutableAdapter.RELOADING
        }
        presenter.syncItems()
    }
}
