package com.danielspeixoto.connect.view.recycler.holder

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import org.jetbrains.anko.*

/**
 * Created by daniel on 22/06/17.
 */
class LoadingUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                padding = PARAM_LAYOUT * 2
                lparams(width = matchParent)
                progressBar().lparams(width = matchParent)
            }
        }
    }

    fun createHolder(ui: AnkoContext<ViewGroup>): ViewHolder {
        return LoadingHolder(createView(ui))
    }

    class LoadingHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}