package com.danielspeixoto.connect.view.recycler.adapter

import android.support.v7.widget.RecyclerView
import com.danielspeixoto.connect.view.activity.BaseActivity
import com.danielspeixoto.connect.view.recycler.holder.BaseHolder

/**
 * Created by danielspeixoto on 4/21/17.
 */
abstract class BaseAdapter<H : BaseHolder<O>, O>
    (var activity : BaseActivity) :
        RecyclerView.Adapter<H>() {

    private var data : ArrayList<O> = ArrayList()

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

    override fun onBindViewHolder(holder: H, position: Int) {
        holder.item = data[position]
        holder.adapter = this
        holder.onPostCreated()
    }

    override fun getItemCount() = data.size
}