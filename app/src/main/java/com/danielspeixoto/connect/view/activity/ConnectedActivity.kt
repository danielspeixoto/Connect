package com.danielspeixoto.connect.view.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.danielspeixoto.connect.contract.Connected
import com.danielspeixoto.connect.presenter.ConnectedPresenter
import com.danielspeixoto.connect.view.recycler.adapter.BaseAdapter
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

/**
 * Created by daniel on 22/06/17.
 */
class ConnectedActivity : LoggedActivity(), Connected.View {

    lateinit var list: RecyclerView
    private var visitorAdapter = VisitorAdapter(this)
    lateinit private var presenter: Connected.Presenter


    lateinit var progressDialog: ProgressDialog
    var isLoading = true
        set(value) {
            field = value
            progressDialog.dismiss()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            verticalLayout {
                list = recyclerView {
                    layoutManager = LinearLayoutManager(this@ConnectedActivity)
                    adapter = visitorAdapter
                }.lparams(width = matchParent, height = matchParent)
            }
        presenter = ConnectedPresenter(this)
        presenter.adapter = visitorAdapter
    }

    override fun onResume() {
        super.onResume()
        visitorAdapter.status = BaseAdapter.IDLE
        presenter.syncItems()
    }

}
