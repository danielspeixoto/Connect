package com.danielspeixoto.connect.view.activity

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.contract.ActivityBase
import com.danielspeixoto.connect.util.PARAM_LAYOUT
import org.jetbrains.anko.*

/**
 * Created by danielspeixoto on 13/11/16.
 */
abstract class BaseActivity : AppCompatActivity(), ActivityBase.View {
    val TAG = javaClass.simpleName

    lateinit var loadingDialog : ProgressDialog

    override val activity: BaseActivity
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun goToActivity(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    override fun showLoadingDialog() {
        loadingDialog = indeterminateProgressDialog(getString(R.string.loading))
        loadingDialog.setCancelable(false)
        loadingDialog.show()
    }

    override fun closeLoadingDialog() {
        if(loadingDialog != null) {
            loadingDialog.dismiss()
        }
    }

    override fun showSavedDialog(message : String) {
        showImageDialog(R.drawable.ic_assignment_ind, message)
    }

    override fun showErrorDialog() {
        showImageDialog(R.drawable.ic_report_problem, getString(R.string.error_occurred))
    }

    fun showImageDialog(drawable : Int, message: String) {
        alert {
            customView {
                verticalLayout {
                    lparams(width = wrapContent, height = wrapContent)
                    padding = PARAM_LAYOUT * 4
                    imageView {
                        imageResource = drawable

                    }
                    textView(message) {
                        textSize = (PARAM_LAYOUT * 3).toFloat()
                        gravity = Gravity.CENTER
                    }
                }
            }

        }.show()
    }
}
