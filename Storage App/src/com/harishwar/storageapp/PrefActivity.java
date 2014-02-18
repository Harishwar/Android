package com.harishwar.storageapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import com.harishwar.storageapp.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class PrefActivity extends Activity {
	public static SharedPreferences sharedPreference;
	public static Context context;
	int count;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pref);

		Button Save = (Button) findViewById(R.id.button_pref_save);
		Button Close = (Button) findViewById(R.id.button_pref_close);
		
		final EditText book = (EditText) findViewById(R.id.book_editText);
		final EditText author = (EditText) findViewById(R.id.author_editText);
		final EditText desc = (EditText) findViewById(R.id.desc_editText);

		Save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View save){
				String bookName = book.getText().toString();
				String authorName = author.getText().toString();
				String description = desc.getText().toString();

				Set<String> strSet = new HashSet<String>();
				strSet.add(bookName);
				strSet.add(authorName);
				strSet.add(description);

				context = getApplicationContext();
				sharedPreference = context.getSharedPreferences(context.getResources().getString(R.string.book_details), context.MODE_WORLD_READABLE);
				SharedPreferences.Editor prefEditor = sharedPreference
						.edit();
				prefEditor.putStringSet(bookName, strSet).commit();
				if (sharedPreference != null) {
					count = sharedPreference.getInt(getResources().getString(R.string.count), 0);
				}
				count++;
				prefEditor.putInt(getResources().getString(R.string.count), count).commit();
				Intent returnIntent = new Intent();
				String itemStr = "Pref " + count + ", " + getCurrentDateTime();
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
		String strDate = date.format(calendar.getTime());
		return strDate;
	}
}
