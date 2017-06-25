package com.danielspeixoto.connect.view.recycler.adapter

import android.support.v7.widget.RecyclerView
import com.danielspeixoto.connect.view.activity.BaseActivity

/**
 * Created by danielspeixoto on 4/21/17.
 */
abstract class BaseAdapter<O>
    (var activity : BaseActivity) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        val LOADED = "loaded"
        val IDLE = "idle"
        val LOADING = "loading"
        val RELOADING = "reloading"
    }
    var status = LOADING
        set(update) {
            field = update
            notifyDataSetChanged()
        }

    val EMPTY_VIEW = 0
    val ITEM_VIEW = 1
    val LOADING_VIEW = 2

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
        if(status == LOADING) return data.size + 1
        if(data.size == 0 && status == LOADED) return 1
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        if(position == data.size && status == LOADING)
            return LOADING_VIEW
        if(status == LOADED && data.size == 0) return EMPTY_VIEW
        return ITEM_VIEW
    }

}