package com.danielspeixoto.connect.presenter;

import com.danielspeixoto.connect.R;
import com.danielspeixoto.connect.model.UserModel;
import com.danielspeixoto.connect.model.pojo.User;
import com.danielspeixoto.connect.module.Login;
import com.danielspeixoto.connect.util.App;
import com.danielspeixoto.connect.util.Validate;
import com.danielspeixoto.connect.view.activity.HomeActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by danielspeixoto on 2/15/17.
 */

public class LoginPresenter implements Login.Presenter {

    private Login.View mView;

    public LoginPresenter(Login.View view) {
        mView = view;
    }

    @Override
    public void logIn(String username, String password) {
	    App.showMessage(App.getStringResource(R.string.loading));
	    User user = new User(username, password);
	    String result = Validate.User(user);
	    if (result.equals(Validate.OK)) {
		    UserModel.logIn(username, password)
				    .subscribeOn(Schedulers.io())
				    .observeOn(AndroidSchedulers.mainThread())
				    .subscribe(user1 -> {
					    mView.goToActivity(HomeActivity.class);
					    mView.getActivity().finish();
				    });
	    } else {
		    App.showMessage(result);
	    }
    }
}
