package com.harishwar.storageapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SavedListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_saved_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.saved_list, menu);
		return true;
	}

}
