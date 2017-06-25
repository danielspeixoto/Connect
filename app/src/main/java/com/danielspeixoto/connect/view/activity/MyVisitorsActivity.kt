package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.danielspeixoto.connect.contract.MyVisitors
import com.danielspeixoto.connect.presenter.MyVisitorsPresenter
import com.danielspeixoto.connect.view.recycler.adapter.MutableAdapter
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

/**
 * Created by danielspeixoto on 5/3/17.
 */
class MyVisitorsActivity : LoggedActivity(), MyVisitors.View {

    lateinit var list: RecyclerView
    lateinit var refreshLayout : SwipeRefreshLayout
    private var visitorAdapter = VisitorAdapter(this)
    lateinit private var presenter: MyVisitors.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coordinatorLayout {
            refreshLayout = swipeRefreshLayout {
                list = recyclerView {
                    layoutManager = LinearLayoutManager(this@MyVisitorsActivity)
                    adapter = visitorAdapter
                }.lparams(width = matchParent, height = matchParent)
            }
        }
        presenter = MyVisitorsPresenter(this)
        presenter.adapter = visitorAdapter
        presenter.refreshLayout = refreshLayout
        refreshLayout.setOnRefreshListener {
            presenter.getVisitors()
            visitorAdapter.status = MutableAdapter.RELOADING
        }
        presenter.getVisitors()
    }
}