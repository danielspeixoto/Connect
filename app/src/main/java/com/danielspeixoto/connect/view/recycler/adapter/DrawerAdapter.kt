package com.danielspeixoto.connect.view.recycler.adapter

import com.danielspeixoto.connect.R
import com.danielspeixoto.connect.model.UserModel
import com.danielspeixoto.connect.model.pojo.Link
import com.danielspeixoto.connect.util.App
import com.danielspeixoto.connect.util.Permissions
import com.danielspeixoto.connect.view.activity.*

/**
 * Created by danielspeixoto on 5/3/17.
 */
class DrawerAdapter(activity: BaseActivity) :
        LinksAdapter(activity) {

    init {
        addItem(Link(App.getStringResource(R.string.my_visitors),
                     Runnable { activity.goToActivity(MyVisitorsActivity::class.java) }))
        addItem(Link(App.getStringResource(R.string.connected_visitors),
                Runnable { activity.goToActivity(ConnectedActivity::class.java) }))
        val permissions = UserModel.currentUser!!.permissions
        if(permissions.containsKey(Permissions.MANAGE_USERS)) {
            addItem(Link(App.getStringResource(R.string.manage_users),
                         Runnable { activity.goToActivity(UsersActivity::class.java) }))
        }
        addItem(Link(App.getStringResource(R.string.settings),
                     Runnable { activity.goToActivity(SettingsActivity::class.java) }))
    }
}
