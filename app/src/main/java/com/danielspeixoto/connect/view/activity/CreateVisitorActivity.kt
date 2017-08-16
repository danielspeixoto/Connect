package com.danielspeixoto.connect.view.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.CreateVisitor
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.presenter.CreateVisitorPresenter
import com.danielspeixoto.connect.util.*
import com.danielspeixoto.connect.view.custom.EditField
import com.danielspeixoto.connect.view.custom.editField
import com.danielspeixoto.connect.view.custom.floatingButton
import org.jetbrains.anko.*
import org.jetbrains.anko.design.coordinatorLayout

/**
 * Created by danielspeixoto on 4/25/17.
 */
class CreateVisitorActivity : LoggedActivity(), CreateVisitor.View, MessageView {

    lateinit var nameEdit : EditText
    lateinit var ageEdit: EditField
    lateinit var observationsEdit: EditField
    lateinit var phoneEdit: EditField

    override var messageView: TextView? = null

    lateinit var presenter: CreateVisitor.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = CreateVisitorPresenter(this)
        coordinatorLayout {
            lparams(width = matchParent, height = matchParent)
            padding = dip(PARAM_LAYOUT)
            scrollView {
                verticalLayout {
                    nameEdit = editField {
                        hint = getString(R.string.name)
                    }
                    ageEdit = editField {
                        hint = getString(R.string.age)
                        inputType = EditorInfo.TYPE_CLASS_NUMBER
                    }
                    phoneEdit = editField {
                        hint = getString(R.string.phone)
                        inputType = EditorInfo.TYPE_CLASS_NUMBER
                    }
                    observationsEdit = editField {
                        gravity = Gravity.START
                        hint = getString(R.string.observations)
                        height = dip(100)
                    }
                    messageView = textView {
                        padding = PARAM_LAYOUT * 4
                        textColor = Color.RED
                        textSize = (PARAM_LAYOUT * 2).toFloat()
                        visibility = View.GONE
                        gravity = Gravity.CENTER_HORIZONTAL
                    }.lparams(width = matchParent)
                }.lparams(width = matchParent, height = wrapContent)
            }.lparams(width = matchParent, height = wrapContent)
            floatingButton {
                imageResource = R.drawable.ic_save
                onClick {
                    val visitor = Visitor(nameEdit.content.trim())
                    if(!ageEdit.isEmpty()) visitor.age = ageEdit.content.integer
                    if(!phoneEdit.isEmpty()) visitor.phone = phoneEdit.content.integer
                    visitor.observations = observationsEdit.content.trim()
                    presenter.create(visitor)
                }
            }.lparams {
                margin = resources.getDimensionPixelSize(R.dimen.fab_margin)
                gravity = Gravity.BOTTOM or GravityCompat.END
            }
        }
    }

    override fun refresh() {
        nameEdit.clear()
        ageEdit.clear()
        phoneEdit.clear()
        observationsEdit.clear()
    }

    override fun setMessageViewText(message: String) {
        setMessageViewContent(message)
    }
}
