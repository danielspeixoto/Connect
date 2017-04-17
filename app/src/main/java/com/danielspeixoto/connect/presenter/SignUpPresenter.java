package com.danielspeixoto.connect.presenter;

import com.danielspeixoto.connect.R;
import com.danielspeixoto.connect.model.Connection;
import com.danielspeixoto.connect.model.WebService.UsersService;
import com.danielspeixoto.connect.model.pojo.User;
import com.danielspeixoto.connect.module.SignUp;
import com.danielspeixoto.connect.util.App;
import com.danielspeixoto.connect.util.Database;
import com.danielspeixoto.connect.util.Permissions;
import com.danielspeixoto.connect.util.Validate;
import com.danielspeixoto.connect.view.activity.BaseActivity;
import com.danielspeixoto.connect.view.activity.HomeActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by danielspeixoto on 2/14/17.
 */

public class SignUpPresenter implements SignUp.Presenter {

    private SignUp.View mView;
    private BaseActivity mActivity;


    public SignUpPresenter(SignUp.View mView) {
        this.mView = mView;
        mActivity = mView.getActivity();
    }

    @Override
    public void signUp(User user) {
        App.showMessage(mActivity.getResources().getString(R.string.loading));
	    user.setGroup(user.getUsername());
	    user.setPermissions(Permissions.getADMPermissions());
	    String result = Validate.User(user);
        if (result.equals(Validate.OK)) {
	        // TODO Check if username is unique
	        Database.getMRetrofit()
			        .create(UsersService.class)
			        .createUser(user)
			        .subscribeOn(Schedulers.io())
			        .observeOn(AndroidSchedulers.mainThread())
			        .subscribe(user1 -> {
				        App.showMessage(App.getStringResource(R.string.user_added));
				        Connection.saveAccountOnDevice(user1);
				        mView.goToActivity(HomeActivity.class);
				        mView.getActivity().finish();
			        }, throwable -> {
				        App.showMessage(App.getStringResource(R.string.error_occurred));
				        throwable.printStackTrace();
			        });
        } else {
            App.showMessage(result);
        }
    }
}
