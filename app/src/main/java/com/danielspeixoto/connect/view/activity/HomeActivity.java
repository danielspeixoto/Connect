package com.danielspeixoto.connect.view.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.danielspeixoto.connect.R;
import com.danielspeixoto.connect.model.UserModel;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_home);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.logout:
				UserModel.logOut();
				goToActivity(MainActivity.class);
				finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
