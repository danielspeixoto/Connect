package com.danielspeixoto.connect.view.recycler.adapter

import android.support.v7.widget.RecyclerView
import com.danielspeixoto.connect.view.activity.BaseActivity

/**
 * Created by danielspeixoto on 4/21/17.
 */
abstract class BaseAdapter<O>
    (var activity : BaseActivity) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data : ArrayList<O> = ArrayList()

    open fun addItem(t: O) {
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
        return data.size
    }

}