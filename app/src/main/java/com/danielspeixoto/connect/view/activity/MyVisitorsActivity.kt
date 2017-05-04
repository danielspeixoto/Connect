package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.danielspeixoto.connect.view.recycler.adapter.LinksAdapter
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by danielspeixoto on 5/3/17.
 */
class MyVisitorsActivity : BaseActivity() {

    lateinit var list: RecyclerView
    private var adapter = LinksAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coordinatorLayout {
            list = recyclerView {
                layoutManager = LinearLayoutManager(this@MyVisitorsActivity)
                adapter = adapter
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}