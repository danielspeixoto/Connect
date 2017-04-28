package com.danielspeixoto.connect.view.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.InfoVisitor
import com.danielspeixoto.connect.model.pojo.Visitor
import com.danielspeixoto.connect.presenter.InfoVisitorPresenter
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.DatabaseContract
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import com.danielspeixoto.connect.util.string
import com.danielspeixoto.connect.view.recycler.adapter.VisitorAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


/**
 * Created by danielspeixoto on 4/27/17.
 */
class InfoVisitorActivity : BaseActivity(), InfoVisitor.View {

    lateinit private var mPresenter: InfoVisitor.Presenter
    private var mAdapter = VisitorAdapter(this)

    val REQUEST_CALL = 1
    var callEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val visitor = intent.getParcelableExtra<Visitor>(DatabaseContract.VISITOR)
        title = visitor.name
        scrollView {
            lparams(width = matchParent, height = wrapContent)
            cardView {
                verticalLayout {
                    if (visitor.age != null) {
                        textView(visitor.age!!.string + " " + App.getStringResource(R.string.years)) {
                            textSize = 26f
                            padding = dip(PARAM_LAYOUT * 2)
                        }
                    }
                    if (visitor.phone != null) {
                        relativeLayout {
                            onClick {
                                val permissionCheck = ContextCompat.checkSelfPermission(this@InfoVisitorActivity,
                                                                                        Manifest.permission.CALL_PHONE)
                                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                                    callEnabled = false
                                    ActivityCompat.requestPermissions(this@InfoVisitorActivity,
                                                                      arrayOf(Manifest.permission.CALL_PHONE),
                                                                      REQUEST_CALL)
                                }
                                if(callEnabled) {
                                    makeCall(visitor.phone!!.string)
                                }
                            }
                            val phoneText = textView(visitor.phone!!.string) {
                                textSize = 26f
                                padding = dip(PARAM_LAYOUT * 2)
                            }
                            imageView {
                                imageResource = R.drawable.ic_call
                                scaleType = ImageView.ScaleType.FIT_CENTER
                                adjustViewBounds = true
                            }.lparams(height = PARAM_LAYOUT * 10) {
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
                    //TODO Add observers and activities list also
                }.lparams(width = matchParent, height = wrapContent) {
                    padding = dip(PARAM_LAYOUT)
                }
            }.lparams(width = matchParent) {
                margin = dip(PARAM_LAYOUT)
            }
        }
        mPresenter = InfoVisitorPresenter(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CALL -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callEnabled = true
                }
            }
        }
    }
}
