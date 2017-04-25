package com.danielspeixoto.connect.view.recycler.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.view.activity.BaseActivity
import com.danielspeixoto.connect.view.recycler.holder.BaseHolder
import org.jetbrains.anko.*


/**
 * Created by danielspeixoto on 4/25/17.
 */

class VisitorAdapter(activity : BaseActivity) : BaseAdapter<ItemUI.VisitorHolder, Visitor>(activity) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemUI.VisitorHolder {
        return ItemUI.VisitorHolder(ItemUI().createView(AnkoContext.create(parent!!.context,
                                                                           parent)))
    }
}

class ItemUI : AnkoComponent<ViewGroup> {

    companion object {
        lateinit var nameText : TextView
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = dip(48))
                orientation = LinearLayout.HORIZONTAL
                nameText = textView {
                    id = 1
                    textSize = 16f
                }
            }
        }
    }

    class VisitorHolder(itemView: View) : BaseHolder<Visitor>(itemView) {

        override fun onPostCreated() {
            nameText.text = item!!.name
        }
    }
}