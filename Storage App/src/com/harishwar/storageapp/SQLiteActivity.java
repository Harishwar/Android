package com.harishwar.storageapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.harishwar.storageapp.R;
import com.harishwar.storageapp.DB;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class SQLiteActivity extends Activity {
	private DB sqlitedb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);

		final EditText messageEditText = (EditText) findViewById(R.id.message);
		Button Save = (Button) findViewById(R.id.button_sqlite_save);
		Button Close = (Button) findViewById(R.id.button_sqlite_close);

		Save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View sqlite) {
				String message = messageEditText.getText().toString();
				int count = 0;
				sqlitedb = new DB(getApplicationContext());
				sqlitedb.closeDB();
				sqlitedb.openInternalDB();
				sqlitedb.addMessage(message);
				count = sqlitedb.getCursorCount();
				sqlitedb.close();

				Intent returnIntent = new Intent();
				String itemStr = "SQLite " + count + ", "
						+ getCurrentDateTime();
				returnIntent.putExtra("result", itemStr);
				setResult(RESULT_OK, returnIntent);
				finish();
			}
		});
		Close.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View close) {
				finish();
			}
		});

	}
	
	public static String getCurrentDateTime() {
		long timeInMilliseconds = System.currentTimeMillis();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeInMilliseconds);
		SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		return date.format(calendar.getTime());
	}
}