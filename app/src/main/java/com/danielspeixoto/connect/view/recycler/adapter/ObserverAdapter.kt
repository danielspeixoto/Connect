package com.danielspeixoto.connect.view.recycler.adapter

import com.danielspeixoto.connect.view.activity.BaseActivity

class ObserverAdapter(activity : BaseActivity) : UserAdapter(activity) {

    override fun getItemCount() : Int {
        if(status == LOADING) {
            return data.size + 1
        }
        if(data.size == 0) return 1 else return data.size
    }

    override fun getItemViewType(position: Int): Int {
        if(status == LOADING && position == data.size) {
            return LOADING_VIEW
        }
        if(data.size == 0) {
            return EMPTY_VIEW
        }
        return ITEM_VIEW
    }
}
