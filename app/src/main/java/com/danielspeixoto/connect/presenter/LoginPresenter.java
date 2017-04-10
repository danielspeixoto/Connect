package com.danielspeixoto.connect.presenter;

import com.danielspeixoto.connect.R;
import com.danielspeixoto.connect.model.Connection;
import com.danielspeixoto.connect.model.pojo.User;
import com.danielspeixoto.connect.module.Login;
import com.danielspeixoto.connect.util.App;
import com.danielspeixoto.connect.util.Validate;
import com.danielspeixoto.connect.view.activity.BaseActivity;
import com.danielspeixoto.connect.view.activity.HomeActivity;

import rx.SingleSubscriber;

/**
 * Created by danielspeixoto on 2/15/17.
 */

public class LoginPresenter implements Login.Presenter {

    private Login.View mView;
    private BaseActivity mActivity;

    public LoginPresenter(Login.View view) {
        mView = view;
        mActivity = mView.getActivity();
    }

    @Override
    public void logIn(String email, String password) {
        mActivity.showMessage(App.getStringResource(R.string.loading));
        User user = new User(email, password);
        String result = Validate.User(user);
        if (result.equals(Validate.OK)) {
            Connection.findUser(email, password).subscribe(new SingleSubscriber<User>() {
                @Override
                public void onSuccess(User user) {
                    Connection.logIn(user);
                    mView.goToActivity(HomeActivity.class);
                    mActivity.finish();
                }

                @Override
                public void onError(Throwable error) {
                    App.showMessage(error.getMessage());
                }
            });
        } else {
            App.showMessage(result);
        }
    }
}
