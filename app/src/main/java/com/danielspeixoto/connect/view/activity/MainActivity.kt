package com.danielspeixoto.connect.view.activity


import android.graphics.Color
import android.os.Bundle
import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.util.ACTIVITY_BORDER
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.view.custom.iconView
import org.jetbrains.anko.*

class MainActivity : BaseActivity() {

    private val HAS_NO_ACCOUNT: Int = 1
    private val  HAS_ACCOUNT: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        if (UserModel.hasAccountSavedOnDevice()) {
            startActivity<HomeActivity>()
            goToActivity(HomeActivity::class.java)
            finish()
        }
        super.onCreate(savedInstanceState)
        relativeLayout {
            lparams(width = matchParent, height = matchParent)
            padding = dip(ACTIVITY_BORDER)
            button(App.getStringResource(R.string.has_no_account)) {
                id = HAS_NO_ACCOUNT
                onClick {
                    startActivity<SignUpActivity>()
                }
                backgroundColor = Color.WHITE
            }.lparams(width = matchParent) {
                bottomMargin =  dip(8)
                alignParentBottom()
                alignParentStart()
            }
            button(App.getStringResource(R.string.has_account)) {
                id = HAS_ACCOUNT
                onClick {
                    startActivity<LoginActivity>()
                }
                backgroundColor = Color.WHITE
            }.lparams(width = matchParent) {
                above(HAS_NO_ACCOUNT)
                bottomMargin =  dip(8)
                alignParentStart()
            }
            iconView().lparams {
                above(HAS_ACCOUNT)
                alignParentTop()
                centerHorizontally()
            }
        }
    }
}
