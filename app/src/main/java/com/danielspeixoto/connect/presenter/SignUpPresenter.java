package com.danielspeixoto.connect.presenter;

import com.danielspeixoto.connect.R;
import com.danielspeixoto.connect.model.Connection;
import com.danielspeixoto.connect.model.WebService.UsersService;
import com.danielspeixoto.connect.model.pojo.User;
import com.danielspeixoto.connect.module.SignUp;
import com.danielspeixoto.connect.util.App;
import com.danielspeixoto.connect.util.Database;
import com.danielspeixoto.connect.util.DatabaseContract;
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
	    String passwordWithoutHash = user.getPassword();
	    App.showMessage(mActivity.getResources().getString(R.string.loading));
	    String result = Validate.User(user);
        if (result.equals(Validate.OK)) {
	        Database.getRetrofit()
			        .create(UsersService.class)
			        .createUser(user)
			        .subscribeOn(Schedulers.io())
			        .observeOn(AndroidSchedulers.mainThread())
			        .subscribe(response -> {
				        App.showMessage(App.getStringResource(R.string.user_added));
				
				        User newUser = Database.getGson().fromJson(response.get(DatabaseContract.USER), User.class);
				        Connection.saveAccountOnDevice(
						        newUser.getUsername(),
						        passwordWithoutHash);
				        Connection.logIn(newUser, response.get(DatabaseContract.TOKEN).getAsString());
				        
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
