package com.danielspeixoto.connect.view.recycler.adapter

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Database
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.view.activity.BaseActivity
import com.danielspeixoto.connect.view.recycler.holder.BaseHolder
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by danielspeixoto on 4/28/17.
 */
class ActivityAdapter(activity: BaseActivity) :
        BaseAdapter<String>(activity) {

    override fun onCreateViewHolder(parent: ViewGroup?,
                                    viewType: Int): BaseHolder<*> {
        when (viewType) {
            ITEM_VIEW -> return ActivityAdapter.ItemUI().createHolder(
                    AnkoContext.create(parent!!.context,
                                       parent))
            else      -> return EmptyUI().createHolder(AnkoContext.create(
                    parent!!.context,
                    parent))
        }
    }

    override fun onBindViewHolder(holder: BaseHolder<*>,
                                  position: Int) {
        when (holder.getItemViewType()) {
            ITEM_VIEW -> {
                holder as ActivityAdapter.ActivityHolder
                holder.item = data[position]
                holder.adapter = this
            }
        }
        holder.onPostCreated()
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

    class EmptyUI : AnkoComponent<ViewGroup> {

        lateinit var messageText: TextView

        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                verticalLayout {
                    lparams(width = matchParent)
                    imageView {
                        imageResource = R.drawable.ic_sentiment_very_dissatisfied
                        padding = dip(PARAM_LAYOUT / 2)
                        scaleType = ImageView.ScaleType.FIT_CENTER
                    }
                    messageText = textView {
                        textSize = 26f
                        horizontalPadding = dip(PARAM_LAYOUT * 2)
                        bottomPadding = dip(PARAM_LAYOUT * 2)
                        gravity = Gravity.CENTER
                    }
                }
            }

        }

        fun createHolder(ui: AnkoContext<ViewGroup>): EmptyHolder {
            val holder = EmptyHolder(createView(ui))
            holder.messageText = messageText
            return holder
        }

    }

    class ActivityHolder(itemView: View) : BaseHolder<String>(itemView) {

        lateinit var nameText: TextView

        override fun onPostCreated() {
            nameText.text = item
        }
    }

    class EmptyHolder(itemView: View) : BaseHolder<String>(itemView) {

        lateinit var messageText: TextView

        override fun onPostCreated() {
            if (!Database.isConnected) {
                messageText.text = App.getStringResource(R.string.no_internet)
            } else {
                messageText.text = App.getStringResource(R.string.no_activities)
            }
        }
    }
}
