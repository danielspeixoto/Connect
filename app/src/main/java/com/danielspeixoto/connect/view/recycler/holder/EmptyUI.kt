package com.danielspeixoto.connect.view.recycler.holder

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Database
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.view.recycler.adapter.MutableAdapter
import org.jetbrains.anko.*

/**
 * Created by daniel on 22/06/17.
 */
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

    class EmptyHolder(itemView: View) : BaseHolder<String>(itemView) {

        lateinit var messageText: TextView
        var status = MutableAdapter.LOADING

        override fun onPostCreated() {
            if (!Database.isConnected) {
                messageText.text = App.getStringResource(R.string.no_internet)
            } else if(status == MutableAdapter.ERROR) {
                messageText.text = App.getStringResource(R.string.error_occurred)
            }else {
                messageText.text = App.getStringResource(R.string.empty)
            }
        }
    }

}