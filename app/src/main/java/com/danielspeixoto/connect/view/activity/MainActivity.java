package com.danielspeixoto.connect.view.activity;


import android.os.Bundle;

import com.danielspeixoto.connect.R;
import com.danielspeixoto.connect.model.UserModel;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (UserModel.hasAccountSavedOnDevice()) {
            goToActivity(HomeActivity.class);
            finish();
        }
        super.onCreate(savedInstanceState, R.layout.activity_main);
    }

    @OnClick(R.id.signUpButton)
    public void signUp() {
        goToActivity(SignUpActivity.class);
    }

    @OnClick(R.id.haveAccountButton)
    public void logIn() {
        goToActivity(LoginActivity.class);
    }

}
