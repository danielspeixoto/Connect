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
import android.widget.Button
import android.widget.ImageView
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.InfoVisitor
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.presenter.InfoVisitorPresenter
import com.danielspeixoto.connect.util.*
import com.danielspeixoto.connect.view.custom.EditField
import com.danielspeixoto.connect.view.custom.editField
import com.danielspeixoto.connect.view.recycler.adapter.ActivityAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.nestedScrollView


/**
 * Created by danielspeixoto on 4/27/17.
 */
class InfoVisitorActivity : BaseActivity(), InfoVisitor.View {

    lateinit private var mPresenter: InfoVisitor.Presenter
    private var activityAdapter = ActivityAdapter(this)

    lateinit var connectedButton: Button
    lateinit var activityField: EditField
    lateinit var activitiesList: RecyclerView

    val REQUEST_CALL = 1
    var callEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val visitor = intent.getParcelableExtra<Visitor>(DatabaseContract.VISITOR)
        mPresenter = InfoVisitorPresenter(this)
        mPresenter.visitor = visitor
        title = visitor.name
        relativeLayout {
            connectedButton = button {
                textColor = Color.WHITE
                onClick {
                    mPresenter.toggleVisitorConnected()
                }
                val typedValue = TypedValue()
                theme.resolveAttribute(R.attr.colorAccent,
                                       typedValue,
                                       true)
                backgroundColor = typedValue.data
            }.lparams(width = matchParent) {
                alignParentBottom()
            }
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
                                textSize = 26f
                                padding = dip(PARAM_LAYOUT * 2)
                                isSelectable = true
                            }
                            linearLayout {
                                imageButton {
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
                    if(visitor.observations !== EMPTY_STRING) {
                        textView(visitor.observations) {
                            textSize = 26f
                            padding = dip(PARAM_LAYOUT * 2)
                        }
                    }
                    relativeLayout {
                        activityField = editField {
                            hint = getString(R.string.add_activity)
                            rightPadding = dip(PARAM_LAYOUT * 4)
                        }.lparams(width = matchParent) {
                            alignParentLeft()
                        }
                        imageButton {
                            imageResource = R.drawable.ic_add_black_24dp
                            scaleType = ImageView.ScaleType.FIT_CENTER
                            adjustViewBounds = true
                            backgroundColor = Color.TRANSPARENT
                            onClick {
                                mPresenter.addActivity(activityField.content)
                            }

                        }.lparams {
                            alignParentRight()
                            centerVertically()
                            padding = dip(PARAM_LAYOUT)
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
                    //TODO Add observers list also
                }.lparams(width = matchParent) {
                    margin = dip(PARAM_LAYOUT)
                }
            }.lparams(width = matchParent) {
                // TODO make it work
                above(connectedButton)
                bottomMargin = dip(50)
            }
            onVisitorConnected(visitor.isConnected)
        }
        mPresenter.activitiesAdapter = activityAdapter
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
