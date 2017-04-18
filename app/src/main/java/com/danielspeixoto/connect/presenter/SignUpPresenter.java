package com.danielspeixoto.connect.presenter;

import com.danielspeixoto.connect.R;
import com.danielspeixoto.connect.model.UserModel;
import com.danielspeixoto.connect.model.pojo.User;
import com.danielspeixoto.connect.module.SignUp;
import com.danielspeixoto.connect.util.App;
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
		String result = Validate.User(user);
		if (result.equals(Validate.OK)) {
			UserModel.createADM(user)
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe(user1 -> {
						App.showMessage(App.getStringResource(R.string.user_added));
						mView.goToActivity(HomeActivity.class);
						mView.getActivity().finish();
					});
		} else {
			App.showMessage(result);
		}
	}
}
