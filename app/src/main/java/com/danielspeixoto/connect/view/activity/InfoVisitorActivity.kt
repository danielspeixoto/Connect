package com.danielspeixoto.connect.view.activity

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
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
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.DatabaseContract
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.util.string
import com.danielspeixoto.connect.view.recycler.adapter.ActivityAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView


/**
 * Created by danielspeixoto on 4/27/17.
 */
class InfoVisitorActivity : BaseActivity(), InfoVisitor.View {

    lateinit private var mPresenter: InfoVisitor.Presenter
    private var mAdapter = ActivityAdapter(this)

    lateinit var connectedButton : Button

    lateinit var activitiesList: RecyclerView

    val REQUEST_CALL = 1
    var callEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val visitor = intent.getParcelableExtra<Visitor>(DatabaseContract.VISITOR)
        mPresenter = InfoVisitorPresenter(this)
        mPresenter.visitor = visitor
        title = visitor.name
        verticalLayout {
            relativeLayout {
                connectedButton = button {
                    textColor = Color.WHITE
                    onClick {
                        mPresenter.toggleVisitorConnected()
                    }
                    val typedValue = TypedValue()
                    theme.resolveAttribute(R.attr.colorAccent, typedValue, true)
                    backgroundColor = typedValue.data
                }.lparams(width = matchParent) {
                    alignParentBottom()
                }
                scrollView {
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
                                }
                                imageButton {
                                    imageResource = R.drawable.ic_call
                                    scaleType = ImageView.ScaleType.FIT_CENTER
                                    adjustViewBounds = true
                                    backgroundColor = Color.WHITE
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
                                }.lparams(height = PARAM_LAYOUT * 20) {
                                    rightMargin = dip(PARAM_LAYOUT * 2)
                                    alignParentRight()
                                    centerVertically()
                                }
                            }
                        }
                        textView(visitor.observations) {
                            textSize = 26f
                            padding = dip(PARAM_LAYOUT * 2)
                        }
                        relativeLayout {
                            val activityField = imageButton {
                                imageResource = R.drawable.ic_add_black_24dp
                                scaleType = ImageView.ScaleType.FIT_CENTER
                                adjustViewBounds = true
                                backgroundColor = Color.TRANSPARENT

                            }.lparams {
                                alignParentRight()
                                centerVertically()
                                padding = dip(PARAM_LAYOUT * 3)
                            }
                            editText {
                                background = ContextCompat.getDrawable(context, android.R.drawable.editbox_background)
                                padding = dip(PARAM_LAYOUT * 2)
                                hint = getString(R.string.add_activity)
                                weightSum = 1f
                            }.lparams {
                                alignParentLeft()
                                rightOf(activityField)
                            }
                        }
                        activitiesList = recyclerView {
                            layoutManager = LinearLayoutManager(this@InfoVisitorActivity) as RecyclerView.LayoutManager?
                            adapter = mAdapter
                            visitor.activities.forEach {
                                mAdapter.addItem(it)
                            }
                        }
                        //TODO Add observers list also
                    }.lparams(width = matchParent, height = wrapContent) {
                        padding = dip(PARAM_LAYOUT)
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    above(connectedButton)
                }
                onVisitorConnected(visitor.isConnected)
            }
        }
    }

    override fun onVisitorConnected(isConnected: Boolean) {
        if(isConnected) {
            connectedButton.text = App.getStringResource(R.string.is_connected)
        } else {
            connectedButton.text = App.getStringResource(R.string.is_not_connected)
        }
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
