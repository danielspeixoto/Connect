package com.danielspeixoto.connect.view.recycler.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.view.activity.BaseActivity
import com.danielspeixoto.connect.view.recycler.holder.BaseHolder
import com.danielspeixoto.connect.view.recycler.holder.EmptyUI
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by danielspeixoto on 4/28/17.
 */
class ActivityAdapter(activity: BaseActivity) :
        BaseAdapter<String>(activity) {

    init {
        status = BaseAdapter.LOADED
    }

    override fun onCreateViewHolder(parent: ViewGroup?,
                                    viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_VIEW -> return ActivityAdapter.ItemUI().createHolder(
                    AnkoContext.create(parent!!.context,
                                       parent))
            else      -> return EmptyUI().createHolder(AnkoContext.create(
                    parent!!.context,
                    parent))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) {
        when (holder.getItemViewType()) {
            ITEM_VIEW -> {
                holder as ActivityAdapter.ActivityHolder
                holder.item = data[position]
                holder.adapter = this
                holder.onPostCreated()
            }
            EMPTY_VIEW -> {
                holder as EmptyUI.EmptyHolder
                holder.status = status
                holder.onPostCreated()
            }
        }

    }

    class ItemUI : AnkoComponent<ViewGroup> {

        lateinit var nameText: TextView

        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent)
                    cardView {
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

        fun createHolder(ui: AnkoContext<ViewGroup>): ActivityHolder {
            val holder = ActivityHolder(createView(ui))
            holder.nameText = nameText
            return holder
        }

    }

    class ActivityHolder(itemView: View) : BaseHolder<String>(itemView) {

        lateinit var nameText: TextView

        override fun onPostCreated() {
            nameText.text = item
        }
    }
}
