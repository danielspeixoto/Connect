package com.danielspeixoto.connect.presenter;

import com.danielspeixoto.connect.R;
import com.danielspeixoto.connect.model.Connection;
import com.danielspeixoto.connect.model.CRUDUsers;
import com.danielspeixoto.connect.model.pojo.User;
import com.danielspeixoto.connect.module.SignUp;
import com.danielspeixoto.connect.util.App;
import com.danielspeixoto.connect.util.Validate;
import com.danielspeixoto.connect.view.activity.BaseActivity;
import com.danielspeixoto.connect.view.activity.HomeActivity;

import rx.SingleSubscriber;

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
        String result = Validate.User(user);
        if (result.equals(Validate.OK)) {
            CRUDUsers.createAdm(user).subscribe(new SingleSubscriber<User>() {
                @Override
                public void onSuccess(User user) {
                    App.showMessage(App.getStringResource(R.string.user_added));
                    Connection.logIn(user);
                    mView.goToActivity(HomeActivity.class);
                    mView.getActivity().finish();
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
