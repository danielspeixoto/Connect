package com.danielspeixoto.connect.view.activity

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.verticalLayout

/**
 * Created by daniel on 24/06/17.
 */
class SearchResultsActivity : BaseActivity() {

    lateinit var list: RecyclerView
    var visitorAdapter = VisitorAdapter(this)

    lateinit var query : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent!!)
        verticalLayout {
            list = recyclerView {
                layoutManager = LinearLayoutManager(this@SearchResultsActivity)
                adapter = visitorAdapter
            }.lparams(width = matchParent, height = matchParent)
        }
    }

    private fun  handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH.equals(intent.action)) {
            query = intent.getStringExtra(SearchManager.QUERY);
            showSavedDialog(query)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        handleIntent(intent!!)
    }

    lateinit var searchView : SearchView

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_home, menu)
        searchView = menu.findItem(R.id.search_view).actionView as SearchView
        searchView.setQuery(query, false)
        searchView.setIconified(false)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showSavedDialog(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        return true
    }
}