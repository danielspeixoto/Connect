package com.danielspeixoto.connect.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.InfoVisitor
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.presenter.InfoVisitorPresenter
import com.danielspeixoto.connect.util.*
import com.danielspeixoto.connect.view.custom.EditField
import com.danielspeixoto.connect.view.custom.editField
import com.danielspeixoto.connect.view.recycler.adapter.ActivityAdapter
import com.danielspeixoto.connect.view.recycler.adapter.ObserverAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.nestedScrollView


/**
 * Created by danielspeixoto on 4/27/17.
 */
class InfoVisitorActivity : LoggedActivity(), InfoVisitor.View {

    lateinit private var presenter: InfoVisitor.Presenter
    private var activityAdapter = ActivityAdapter(this)
    private var observerAdapter = ObserverAdapter(this)

    lateinit var connectedButton: Button
    lateinit var observeButton: Button
    lateinit var activityField: EditField
    lateinit var activitiesList: RecyclerView
    lateinit var observersList: RecyclerView

    val REQUEST_CALL = 1
    var callEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val visitor = intent.getParcelableExtra<Visitor>(DatabaseContract.VISITOR)
        presenter = InfoVisitorPresenter(this)
        presenter.visitor = visitor
        title = visitor.name
        verticalLayout {
            nestedScrollView {
                verticalLayout {
                    if (visitor.age != null) {
                        textView(visitor.age!!.string + " " + App.getStringResource(R.string.years)) {
                            textSize = 26f
                            padding = dip(PARAM_LAYOUT * 2)
                        }
                    }
                    if (visitor.phone != null) {
                        relativeLayout {
                            textView(visitor.phone!!.string) {
                                textSize = (PARAM_LAYOUT * 3).toFloat()
                                padding = dip(PARAM_LAYOUT * 2)
                                isSelectable = true
                            }
                            linearLayout {
                                imageButton {
                                    maxWidth = dip(PARAM_LAYOUT * 7)
                                    imageResource = R.drawable.ic_whatsapp
                                    scaleType = ImageView.ScaleType.CENTER_CROP
                                    adjustViewBounds = true
                                    backgroundColor = Color.TRANSPARENT
                                    padding = dip(PARAM_LAYOUT)
                                    onClick {
                                        val uri = Uri.parse("smsto:" + visitor.phone!!.string)
                                        val i = Intent(Intent.ACTION_SENDTO,
                                                uri)
                                        i.`package` = "com.whatsapp"
                                        startActivity(i)
                                    }
                                }
                                imageButton {
                                    maxWidth = dip(PARAM_LAYOUT * 7)
                                    imageResource = R.drawable.ic_call
                                    scaleType = ImageView.ScaleType.FIT_CENTER
                                    adjustViewBounds = true
                                    backgroundColor = Color.TRANSPARENT
                                    padding = dip(PARAM_LAYOUT)
                                    onClick {
                                        val permissionCheck = ContextCompat.checkSelfPermission(this@InfoVisitorActivity,
                                                Manifest.permission.CALL_PHONE)
                                        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                                            callEnabled = false
                                            ActivityCompat.requestPermissions(this@InfoVisitorActivity,
                                                    arrayOf(Manifest.permission.CALL_PHONE),
                                                    REQUEST_CALL)
                                        }
                                        if (callEnabled) {
                                            makeCall(visitor.phone!!.string)
                                        }
                                    }
                                }
                            }.lparams(height = PARAM_LAYOUT * 20) {
                                rightMargin = dip(PARAM_LAYOUT)
                                alignParentRight()
                                centerVertically()
                            }

                        }
                    }
                    if(!visitor.observations.equals(EMPTY_STRING)) {
                        textView(visitor.observations) {
                            textSize = 26f
                            padding = dip(PARAM_LAYOUT * 2)
                        }
                    }
                    linearLayout {
                        activityField = editField {
                            hint = getString(R.string.add_activity)
                        }.lparams {
                            weight = 1f
                            gravity = Gravity.CENTER
                        }
                        imageButton {
                            imageResource = R.drawable.ic_add_circle
                            scaleType = ImageView.ScaleType.FIT_CENTER
                            adjustViewBounds = true
                            backgroundColor = Color.TRANSPARENT
                            onClick {
                                if(activityField.content != EMPTY_STRING) {
                                    presenter.addActivity(activityField.content)
                                }
                            }
                            padding = PARAM_LAYOUT * 2
                        }.lparams {
                            gravity = Gravity.CENTER
                        }
                    }.lparams(width = matchParent)
                    activitiesList = recyclerView {
                        layoutManager = LinearLayoutManager(this@InfoVisitorActivity) as RecyclerView.LayoutManager?
                        adapter = activityAdapter
                        padding = dip(PARAM_LAYOUT)
                        visitor.activities.forEach {
                            activityAdapter.addItem(it)
                        }
                    }
                    activitiesList.setNestedScrollingEnabled(false)
                    textView(App.getStringResource(R.string.observers)) {
                        textSize = 26f
                        padding = dip(PARAM_LAYOUT)
                    }
                    observersList = recyclerView {
                        layoutManager = LinearLayoutManager(this@InfoVisitorActivity) as RecyclerView.LayoutManager?
                        adapter = observerAdapter
                        padding = dip(PARAM_LAYOUT)
                    }
                    observersList.setNestedScrollingEnabled(false)
                    if(!visitor!!.observers.contains(UserModel.currentUser!!.username!!)) {
                        observeButton = button(App.getStringResource(R.string.observe)) {
                            textColor = Color.WHITE
                            onClick {
                                presenter.observe()
                            }
                            val typedValue = TypedValue()
                            theme.resolveAttribute(R.attr.colorAccent,
                                    typedValue,
                                    true)
                            backgroundColor = typedValue.data
                        }
                    }
                    // TODO Find better way to add padding
                    linearLayout {
                        bottomPadding = PARAM_LAYOUT * 2
                    }.lparams {
                        bottomMargin = PARAM_LAYOUT * 2
                    }
                }.lparams(width = matchParent) {
                    margin = PARAM_LAYOUT * 2
                }
            }.lparams(width = matchParent) {
                weight = 1f
            }
            connectedButton = button {
                textColor = Color.WHITE
                onClick {
                    presenter.toggleConnected()
                }
                val typedValue = TypedValue()
                theme.resolveAttribute(R.attr.colorAccent,
                        typedValue,
                        true)
                backgroundColor = typedValue.data
            }.lparams(width = matchParent)
            onVisitorConnected(visitor.isConnected)
        }
        // Remove focus from activity field when it starts
        activitiesList.requestFocus()
        presenter.activitiesAdapter = activityAdapter
        presenter.observersAdapter = observerAdapter
        presenter.retrieveObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_visitor, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
            R.id.delete -> {
                alert(getString(R.string.are_you_sure)) {
                    yesButton {
                        presenter.deleteVisitor()
                        finish()
                    }
                    noButton {

                    }
                }.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onVisitorConnected(isConnected: Boolean) {
        if (!isConnected) {
            connectedButton.text = App.getStringResource(R.string.is_connected)
        } else {
            connectedButton.text = App.getStringResource(R.string.is_not_connected)
        }
    }

    override fun onActivityAdded(activity: String) {
        activityField.clear()
    }

    override fun onObserved() {
        observeButton.visibility = View.GONE
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CALL -> {
                if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callEnabled = true
                }
            }
        }
    }
}