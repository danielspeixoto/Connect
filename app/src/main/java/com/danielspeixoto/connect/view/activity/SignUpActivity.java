package com.danielspeixoto.connect.view.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.danielspeixoto.connect.R;
import com.danielspeixoto.connect.model.pojo.User;
import com.danielspeixoto.connect.module.SignUp;
import com.danielspeixoto.connect.presenter.SignUpPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements SignUp.View {

    @BindView(R.id.nameEdit)
    EditText nameEdit;
    @BindView(R.id.usernameEdit)
    EditText emailEdit;
    @BindView(R.id.passEdit)
    EditText passEdit;
    @BindView(R.id.confirmPassEdit)
    EditText confirmPassEdit;
    private SignUp.Presenter mPresenter;
    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_sign_up);
        mPresenter = new SignUpPresenter(this);
    }

    @OnClick(R.id.saveFab)
    public void saveUser() {
        if (checkTextEmpty(nameEdit)) {
            nameEdit.requestFocus();
            showMessage("Name must be filled");
        } else if (checkTextEmpty(emailEdit)) {
            emailEdit.requestFocus();
            showMessage("Email must be filled");
        } else if (checkTextEmpty(passEdit)) {
            passEdit.requestFocus();
            showMessage("Must have a password");
        } else if (!getText(passEdit).equals(getText(confirmPassEdit))) {
            confirmPassEdit.requestFocus();
            showMessage("Passwords must match");
        } else {
            user.setName(getText(nameEdit));
            user.setUsername(getText(emailEdit));
            user.setPassword(getText(passEdit));
            mPresenter.signUp(user);
        }
    }
}