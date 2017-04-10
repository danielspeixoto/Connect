package com.danielspeixoto.connect.view.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.danielspeixoto.connect.R;
import com.danielspeixoto.connect.module.Login;
import com.danielspeixoto.connect.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements Login.View {

    @BindView(R.id.usernameEdit)
    EditText emailEdit;
    @BindView(R.id.passEdit)
    EditText passEdit;
    private Login.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_login);
        mPresenter = new LoginPresenter(this);
    }

    @OnClick(R.id.fab)
    public void logIn() {
        mPresenter.logIn(getText(emailEdit), getText(passEdit));
    }

}