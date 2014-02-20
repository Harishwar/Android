package com.harishwar.servicesapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//PDFs Download Activity Page
	public void button_pdf_click(View v){
		if(v.getId() == R.id.button_pdf){
			Intent pdf = new Intent(MainActivity.this, PDFActivity.class);
			startActivity(pdf);
		}
	}
	//Images Download Activity Page
	public void button_img_click(View v){
		if(v.getId() == R.id.button_img){
			Intent img = new Intent(MainActivity.this, ImageActivity.class);
			startActivity(img);
		}
	}
	//Text Files Download Activity Page
	public void button_txt_click(View v){
		if(v.getId() == R.id.button_txt){
			Intent txt = new Intent(MainActivity.this, TextActivity.class);
			startActivity(txt);
		}
	}
	//Exit Application
	public void button_exit_click(View v){
		if(v.getId() == R.id.button_exit){
		MainActivity.this.finish();	
		}
	}
	
}
