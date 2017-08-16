package com.danielspeixoto.connect.view.recycler.holder

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import org.jetbrains.anko.*

/**
 * Created by daniel on 25/06/17.
 */
class ErrorUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            verticalLayout {
                lparams(width = wrapContent, height = wrapContent)
                padding = PARAM_LAYOUT * 4
                imageView {
                    imageResource = R.drawable.ic_report_problem

                }
                textView(App.getStringResource(R.string.error_occurred)) {
                    textSize = (PARAM_LAYOUT * 3).toFloat()
                    gravity = Gravity.CENTER
                }
            }
        }
    }

    fun createHolder(ui: AnkoContext<ViewGroup>): RecyclerView.ViewHolder {
        return ErrorUIHolder(createView(ui))
    }

    class ErrorUIHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}