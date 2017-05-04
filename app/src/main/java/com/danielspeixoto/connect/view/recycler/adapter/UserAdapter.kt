package com.danielspeixoto.connect.view.recycler.adapter

import android.support.v7.widget.CardView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.pojo.User
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Database
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.view.activity.BaseActivity
import com.danielspeixoto.connect.view.recycler.holder.BaseHolder
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 * Created by danielspeixoto on 5/3/17.
 */
class UserAdapter(activity: BaseActivity) :
        BaseAdapter<User>(activity) {

    override fun onCreateViewHolder(parent: ViewGroup?,
                                    viewType: Int): BaseHolder<*> {
        when (viewType) {
            ITEM_VIEW -> return ItemUI().createHolder(AnkoContext.create(parent!!.context,
                                                                         parent))

            else      -> return EmptyUI().createHolder(AnkoContext.create(parent!!.context,
                                                                          parent))
        }

    }

    override fun onBindViewHolder(holder: BaseHolder<*>,
                                  position: Int) {
        when (holder.getItemViewType()) {
            ITEM_VIEW -> {
                holder as UserHolder
                holder.item = data[position]
                holder.adapter = this
            }
        }
        holder.onPostCreated()
    }

    class ItemUI : AnkoComponent<ViewGroup> {

        lateinit var nameText: TextView
        lateinit var cardView: CardView

        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent)
                    cardView = cardView {
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
            holder.cardView = cardView
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

    class UserHolder(itemView: View) : BaseHolder<User>(itemView) {

        lateinit var nameText: TextView
        lateinit var cardView: CardView

        override fun onPostCreated() {
            nameText.text = item!!.name
        }
    }


    class EmptyHolder(itemView: View) : BaseHolder<String>(itemView) {

        lateinit var messageText: TextView

        override fun onPostCreated() {
            if (!Database.isConnected) {
                messageText.text = App.getStringResource(R.string.no_internet)
            }
        }
    }
}

