package com.danielspeixoto.connect.view.recycler.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.view.activity.BaseActivity
import com.danielspeixoto.connect.view.recycler.holder.BaseHolder
import com.danielspeixoto.connect.view.recycler.holder.EmptyUI
import com.danielspeixoto.connect.view.recycler.holder.LoadingUI
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by danielspeixoto on 5/3/17.
 */
open class UserAdapter(activity: BaseActivity) :
        MutableAdapter<User>(activity) {

    override fun onCreateViewHolder(parent: ViewGroup?,
                                    viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_VIEW -> return ItemUI().createHolder(AnkoContext.create(parent!!.context,
                                                                         parent))
            LOADING_VIEW -> return LoadingUI().createHolder(AnkoContext.create(parent!!.context,
                    parent))

            else      -> return EmptyUI().createHolder(AnkoContext.create(parent!!.context,
                                                                          parent))
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                  position: Int) {
        when (holder.getItemViewType()) {
            ITEM_VIEW -> {
                holder as UserHolder
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

        fun createHolder(ui: AnkoContext<ViewGroup>): UserHolder {
            val holder = UserHolder(createView(ui))
            holder.nameText = nameText
            return holder
        }

    }

    class UserHolder(itemView: View) : BaseHolder<User>(itemView) {

        lateinit var nameText: TextView

        override fun onPostCreated() {
            nameText.text = item!!.name
        }
    }

}

