package com.danielspeixoto.connect.view.recycler.adapter

import android.support.v7.widget.RecyclerView
import com.danielspeixoto.connect.view.activity.BaseActivity
import com.danielspeixoto.connect.view.recycler.holder.BaseHolder

/**
 * Created by danielspeixoto on 4/21/17.
 */
abstract class BaseAdapter<O>
    (var activity : BaseActivity) :
        RecyclerView.Adapter<BaseHolder<*>>() {

    val EMPTY_VIEW = 0
    val ITEM_VIEW = 1

    var data : ArrayList<O> = ArrayList()

    fun addItem(t: O) {
        data.add(t)
        notifyDataSetChanged()
    }

    fun getItem(position : Int) = data.get(position)

    fun removeItem(position : Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    fun getIterator(): Iterator<O> {
        return data.iterator()
    }

    protected fun goToActivity(clazz: Class<*>) {
        activity.goToActivity(clazz)
    }

    override fun getItemCount() : Int {
        if(data.size == 0) return 1 else return data.size
    }

    override fun getItemViewType(position: Int): Int {
        if(data.size == 0) {
            return EMPTY_VIEW
        }
        return ITEM_VIEW
    }
}