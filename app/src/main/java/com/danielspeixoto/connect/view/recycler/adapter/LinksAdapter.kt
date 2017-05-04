package com.danielspeixoto.connect.view.recycler.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.danielspeixoto.connect.model.pojo.Link
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.view.activity.BaseActivity
import com.danielspeixoto.connect.view.recycler.holder.BaseHolder
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by danielspeixoto on 5/3/17.
 */
open class LinksAdapter(activity: BaseActivity) :
        BaseAdapter<Link>(activity) {

    override fun onCreateViewHolder(parent: ViewGroup?,
                                    viewType: Int): BaseHolder<*> {
        return LinksAdapter.ItemUI().createHolder(
                AnkoContext.create(parent!!.context,
                                   parent))
    }

    override fun onBindViewHolder(holder: BaseHolder<*>,
                                  position: Int) {
        when (holder.getItemViewType()) {
            ITEM_VIEW -> {
                holder as LinksAdapter.DrawerHolder
                holder.item = data[position]
                holder.adapter = this
            }
        }
        holder.onPostCreated()
    }

    class ItemUI : AnkoComponent<ViewGroup> {

        lateinit var nameText: TextView
        lateinit var containerView: LinearLayout

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

        fun createHolder(ui: AnkoContext<ViewGroup>): DrawerHolder {
            val holder = DrawerHolder(createView(ui))
            holder.nameText = nameText
            holder.containerView = containerView
            return holder
        }

    }

    class DrawerHolder(itemView: View) : BaseHolder<Link>(itemView) {

        lateinit var nameText: TextView
        lateinit var containerView: LinearLayout

        override fun onPostCreated() {
            nameText.text = item!!.name
            containerView.onClick {
                item!!.direction.run()
            }

        }
    }
}
