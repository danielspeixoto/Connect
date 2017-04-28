package com.danielspeixoto.connect.view.recycler.adapter

import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.CardView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.util.DatabaseContract
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.view.activity.BaseActivity
import com.danielspeixoto.connect.view.activity.InfoVisitorActivity
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter.ItemUI.Companion.nameText
import com.danielspeixoto.connect.view.recycler.holder.BaseHolder
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


/**
 * Created by danielspeixoto on 4/25/17.
 */

class VisitorAdapter(activity: BaseActivity) :
        BaseAdapter<VisitorAdapter.VisitorHolder, Visitor>(activity) {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VisitorHolder {
        return VisitorHolder(ItemUI().createView(AnkoContext.create(parent!!.context,
                                                                    parent)))
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
}

