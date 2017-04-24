package com.danielspeixoto.connect.view.recycler.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.danielspeixoto.connect.view.recycler.adapter.BaseAdapter

/**
 * Created by danielspeixoto on 20/11/16.
 */
abstract class BaseHolder<O>(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

    var item: O? = null
    var itemPosition: Int = 0
    var adapter : BaseAdapter<*,O>? = null

    abstract fun onPostCreated()
}
