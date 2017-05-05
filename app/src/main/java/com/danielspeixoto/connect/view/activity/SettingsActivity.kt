package com.danielspeixoto.connect.view.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.pojo.Link
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.view.recycler.adapter.LinksAdapter
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by danielspeixoto on 5/3/17.
 */
class SettingsActivity : BaseActivity() {

    lateinit var list: RecyclerView
    private var linksAdapter = LinksAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coordinatorLayout {
            list = recyclerView {
                layoutManager = LinearLayoutManager(this@SettingsActivity)
                adapter = linksAdapter
            }.lparams(width = matchParent,
                      height = matchParent)
        }
        linksAdapter.addItem(Link(App.getStringResource(R.string.log_out),
                                  Runnable
                                  {
                                      UserModel.logOut()
                                      goToActivity(MainActivity::class.java)
                                      activity.finish()
                                  }))
    }
}
