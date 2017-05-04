package com.danielspeixoto.connect.view.recycler.adapter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.pojo.Link
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Permissions
import com.danielspeixoto.connect.view.activity.BaseActivity
import com.danielspeixoto.connect.view.activity.MyVisitorsActivity
import com.danielspeixoto.connect.view.activity.SettingsActivity
import com.danielspeixoto.connect.view.activity.UsersActivity

/**
 * Created by danielspeixoto on 5/3/17.
 */
class DrawerAdapter(activity: BaseActivity) :
        LinksAdapter(activity) {

    init {
        addItem(Link(App.getStringResource(R.string.my_visitors),
                     Runnable { activity.goToActivity(MyVisitorsActivity::class.java) }))
        val permissions = UserModel.currentUser!!.permissions
        //TODO Link to already connected visitors
        if(permissions.containsKey(Permissions.MANAGE_USERS)) {
            addItem(Link(App.getStringResource(R.string.manage_users),
                         Runnable { activity.goToActivity(UsersActivity::class.java) }))
        }
        addItem(Link(App.getStringResource(R.string.settings),
                     Runnable { activity.goToActivity(SettingsActivity::class.java) }))
    }
}
