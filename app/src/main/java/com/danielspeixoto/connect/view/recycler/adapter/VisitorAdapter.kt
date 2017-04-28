package com.danielspeixoto.connect.view.recycler.adapter

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.CardView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Database
import com.danielspeixoto.connect.util.DatabaseContract
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.view.activity.BaseActivity
import com.danielspeixoto.connect.view.activity.InfoVisitorActivity
import com.danielspeixoto.connect.view.recycler.EmptyUI
import com.danielspeixoto.connect.view.recycler.EmptyUI.Companion.messageText
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter.ItemUI.Companion.nameText
import com.danielspeixoto.connect.view.recycler.holder.BaseHolder
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


/**
 * Created by danielspeixoto on 4/25/17.
 */

class VisitorAdapter(activity: BaseActivity) :
        BaseAdapter<Visitor>(activity) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseHolder<*> {
        when(viewType) {
            ITEM_VIEW -> return VisitorHolder(ItemUI().createView(AnkoContext.create(parent!!.context,
                                                                                     parent)))
            else -> return EmptyHolder(EmptyUI().createView(AnkoContext.create(parent!!.context,
                                                                               parent)))
        }

    }

    override fun onBindViewHolder(holder: BaseHolder<*>, position: Int) {
        when (holder.getItemViewType()) {
            ITEM_VIEW -> {
                holder as VisitorHolder
                holder.item = data[position]
                holder.adapter = this
            }
        }
        holder.onPostCreated()
    }

    class ItemUI : AnkoComponent<ViewGroup> {

        companion object {
            lateinit var nameText: TextView
            lateinit var itemView : CardView
        }

        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent)
                    itemView = cardView {
                        linearLayout {
                            lparams(width = matchParent) {
                                padding = dip(PARAM_LAYOUT * 2)
                            }
                            orientation = LinearLayout.HORIZONTAL
                            nameText = textView {
                                textSize = 26f
                            }
                        }
                    }.lparams(width = matchParent) {
                        margin = dip(PARAM_LAYOUT / 2)
                    }
                }
            }
        }

    }

    class VisitorHolder(itemView: View) : BaseHolder<Visitor>(itemView) {
        override fun onPostCreated() {
            nameText.text = item!!.name
            if(item!!.observers.size == 0) {
                nameText.setTextColor(Color.RED)
            }
            itemView.onClick {
                val intent = Intent(adapter.activity, InfoVisitorActivity::class.java)
                intent.putExtra(DatabaseContract.VISITOR, item)
                adapter.activity.startActivity(intent)
            }
        }
    }

    class EmptyHolder(itemView: View) : BaseHolder<String>(itemView) {

        override fun onPostCreated() {
            if(!Database.isConnected) {
                messageText.text = App.getStringResource(R.string.no_internet)
            } else {
                messageText.text = App.getStringResource(R.string.no_visitors)
            }
        }
    }
}

