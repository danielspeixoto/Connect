package com.danielspeixoto.connect.util

import android.view.View
import android.widget.TextView

/**
 * Created by daniel on 23/06/17.
 */
interface MessageView {

    var messageView : TextView?

    fun setMessageViewContent(message : String) {
        messageView!!.text = message
        messageView!!.visibility = View.VISIBLE
    }
}