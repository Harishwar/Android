package com.harishwar.storageapp;

import java.util.ArrayList;
import java.util.List;

import com.harishwar.storageapp.R;
import com.harishwar.storageapp.MainScreenActivity;
import com.harishwar.storageapp.PrefActivity;
import com.harishwar.storageapp.SQLiteActivity;
import com.harishwar.storageapp.SavedList;
import com.harishwar.storageapp.DB;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainScreenActivity extends Activity {

	private SavedList adapter;
	private List<String> itemList = new ArrayList<String>();
	private DB listdb;
	public static final int pref_code = 100;
	public static final int sqlite_code = 100;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		
		listdb = new DB(getApplicationContext());
		listdb.openInternalDB();
		itemList = listdb.getPastDataList();
		listdb.closeDB();

		Button pref = (Button) findViewById(R.id.button_pref);
		Button sqlite = (Button) findViewById(R.id.button_sqlite);
		Button close = (Button) findViewById(R.id.button_exit);

		ListView savedList = (ListView) findViewById(R.id.storage_list_view);
		adapter = new SavedList(itemList, getApplicationContext());
		savedList.setAdapter(adapter);
		
		sqlite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View sqlite) {
				Intent sqlitepage = new Intent(MainScreenActivity.this, SQLiteActivity.class);
				sqlitepage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(sqlitepage, sqlite_code);
			}
		});
		close.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View close) {
				finish();
			}
		});
		pref.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View pref){
				Intent prefpage = new Intent(MainScreenActivity.this, PrefActivity.class);
				prefpage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivityForResult(prefpage, pref_code);
			}
		});
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (requestCode == pref_code || requestCode == sqlite_code) {
			if(resultCode == RESULT_OK){
				String result = data.getStringExtra("result");
				listdb.openInternalDB();
				listdb.addPastData(result);
				itemList = listdb.getPastDataList();
				listdb.closeDB();
				adapter.setStringList(itemList);
			}
		}

	}
}
