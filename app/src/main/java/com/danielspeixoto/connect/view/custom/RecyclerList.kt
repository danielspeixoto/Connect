package com.danielspeixoto.connect.view.custom

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.util.App

/**
 * Created by danielspeixoto on 4/8/17.
 */

class RecyclerList(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val recycler: CustomRecycler
    private val emptyView: LinearLayout

    init {
        emptyView = LinearLayout(context)
        recycler = CustomRecycler(context)
        recycler.layoutManager = LinearLayoutManager(context)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        recycler.layoutParams = params
        val textView = TextView(context)
        textView.textSize = 24f
        textView.text = App.getStringResource(R.string.no_items)
        emptyView.layoutParams = params
        emptyView.addView(textView)
        emptyView.gravity = Gravity.CENTER
        emptyView.setPadding(16, 16, 16, 16)
        recycler.setEmptyView(emptyView)
        addView(recycler)
        addView(emptyView)
    }

    fun setNestedScrollEnabled(enabled: Boolean) {
        recycler.isNestedScrollingEnabled = enabled
    }

    private inner class CustomRecycler : RecyclerView {

        private var emptyView: View? = null
        private val observer = object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                checkIfEmpty()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                checkIfEmpty()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                checkIfEmpty()
            }
        }

        constructor(context: Context) : super(context)

        constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

        internal fun checkIfEmpty() {
            if (emptyView != null && adapter != null) {
                val emptyViewVisible = adapter.itemCount == 0
                emptyView!!.visibility = if (emptyViewVisible) View.VISIBLE else View.GONE
                visibility = if (emptyViewVisible) View.GONE else View.VISIBLE
            }
        }

        override fun setAdapter(adapter: RecyclerView.Adapter<*>) {
            super.setAdapter(adapter)
            adapter.registerAdapterDataObserver(observer)
            checkIfEmpty()
        }

        fun setEmptyView(emptyView: View) {
            this.emptyView = emptyView
            checkIfEmpty()
        }


    }

}
