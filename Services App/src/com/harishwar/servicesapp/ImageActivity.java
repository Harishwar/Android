package com.harishwar.servicesapp;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class ImageActivity extends Activity {

    private EditText url_box1;
    private EditText url_box2;
    private EditText url_box3;
    private EditText url_box4;
    private EditText url_box5; 
    int PDFCount = 1;
    
    private Context context;
    private AsService async_service;
    private boolean async_bound = false;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		// Show the Up button in the action bar.
		setupActionBar();
		context = this;
        url_box1 = (EditText) findViewById(R.id.ImgUri1);
        url_box2 = (EditText) findViewById(R.id.ImgUri2);
        url_box3 = (EditText) findViewById(R.id.ImgUri3);
        url_box4 = (EditText) findViewById(R.id.ImgUri4);
        url_box5 = (EditText) findViewById(R.id.ImgUri5);
        bindService(new Intent(context, AService.class), async_service_connection, Context.BIND_AUTO_CREATE);
	}
	
	private AsServiceCallback.Stub async_callback = new AsServiceCallback.Stub() {
        @Override
        public void onDownloadFinished(final String file_name) throws RemoteException {
            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                	Log.d("Test", "Testing");
                } 
            }); */
        }
    };
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private ServiceConnection async_service_connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            async_service = AsService.Stub.asInterface(service);
            async_bound = true;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            async_service = null;
            async_bound = false;
        }
    };
    
    
	//Start PDF Download
		public void button_img_start_click(View v){
			if(v.getId() == R.id.button_img_start){
				CharSequence text0 = "Downloading Images";
		        Log.d(this.getClass().getSimpleName(), "--- Process Started in " + this.getClass().getSimpleName()+" ---");
		        Toast toast0 = Toast.makeText(context, text0, Toast.LENGTH_SHORT);
		        toast0.show();
		        for(int PDFCount=1;PDFCount<=5;PDFCount++){
		        	switch (PDFCount) {
		        	case 1 : img1();
		        	break;
		        	case 2 : img2();
		        	break;
		        	case 3 : img3();
		        	break;
		        	case 4 : img4();
		        	break;
		        	case 5 : img5();
		        	default:
		        		imgdefault();
		        	}
		        }	
			}
		}
		
		public  void img1(){
			if (async_bound) {
	            try {
	            	CharSequence text1 = "Image File 1 Download Complete";
	                async_service.setCallback(async_callback, url_box1.getText().toString());
	                Toast toast1 = Toast.makeText(context, text1, Toast.LENGTH_SHORT);
	                toast1.show();

	            } catch (RemoteException e) {
	                Log.d(getClass().getSimpleName(), "Error", e);
	            }
	        } else {
	            CharSequence text1 = "The Service is not bound. Attempting to bind again.";
	            Toast toast = Toast.makeText(context, text1, Toast.LENGTH_SHORT);
	            toast.show();
	            bindService(new Intent(context, AService.class), async_service_connection, Context.BIND_AUTO_CREATE);
	        }
		}
		public  void img2(){
			if (async_bound) {
	            try {
	            	CharSequence text2 = "Image File 2 Download Complete";
	                async_service.setCallback(async_callback, url_box2.getText().toString());
	                Toast toast2 = Toast.makeText(context, text2, Toast.LENGTH_SHORT);
	                toast2.show();

	            } catch (RemoteException e) {
	                Log.d(getClass().getSimpleName(), "Error", e);
	            }
	        } else {
	            CharSequence text2 = "The Service is not bound. Attempting to bind again.";
	            Toast toast = Toast.makeText(context, text2, Toast.LENGTH_SHORT);
	            toast.show();
	            bindService(new Intent(context, AService.class), async_service_connection, Context.BIND_AUTO_CREATE);
	        }
		}	
		public  void img3(){
			if (async_bound) {
	            try {
	            	CharSequence text3 = "Image File 3 Download Complete";
	                async_service.setCallback(async_callback, url_box3.getText().toString());
	                Toast toast3 = Toast.makeText(context, text3, Toast.LENGTH_SHORT);
	                toast3.show();
	            } catch (RemoteException e) {
	                Log.d(getClass().getSimpleName(), "Error", e);
	            }
	        } else {
	            CharSequence text3 = "The Service is not bound. Attempting to bind again.";
	            Toast toast4 = Toast.makeText(context, text3, Toast.LENGTH_SHORT);
	            toast4.show();
	            bindService(new Intent(context, AService.class), async_service_connection, Context.BIND_AUTO_CREATE);
	        }
		}
		public  void img4(){
			if (async_bound) {
	            try {
	            	CharSequence text4 = "Image File 4 Download Complete";
	                async_service.setCallback(async_callback, url_box4.getText().toString());
	                Toast toast4 = Toast.makeText(context, text4, Toast.LENGTH_SHORT);
	                toast4.show();
	            } catch (RemoteException e) {
	                Log.d(getClass().getSimpleName(), "Error", e);
	            }
	        } else {
	            CharSequence text4 = "The Service is not bound. Attempting to bind again.";
	            Toast toast = Toast.makeText(context, text4, Toast.LENGTH_SHORT);
	            toast.show();
	            bindService(new Intent(context, AService.class), async_service_connection, Context.BIND_AUTO_CREATE);
	        }
		}
		public  void img5(){
			if (async_bound) {
	            try {
	            	CharSequence text5 = "Image File 5 Download Complete";
	                async_service.setCallback(async_callback, url_box5.getText().toString());
	                Toast toast5 = Toast.makeText(context, text5, Toast.LENGTH_SHORT);
	                toast5.show();
	            } catch (RemoteException e) {
	                Log.d(getClass().getSimpleName(), "Error", e);
	            }
	        } else {
	            CharSequence text5 = "The Service is not bound. Attempting to bind again.";
	            Toast toast = Toast.makeText(context, text5, Toast.LENGTH_LONG);
	            toast.show();
	            bindService(new Intent(context, AService.class), async_service_connection, Context.BIND_AUTO_CREATE);
	        }
		}
		public void imgdefault(){
			Toast.makeText(this, "Process Complete", Toast.LENGTH_SHORT)
	    	.show();
		}
		
		@Override
	    protected void onStop() {
	        super.onStop();
	        if (async_bound) {
	            unbindService(async_service_connection);
	            async_bound = false;
	        }
	    }
		
		
		//End Activity
		public void button_img_close_click(View v){
			if(v.getId() == R.id.button_img_close){
				if (async_bound) {
		            unbindService(async_service_connection);
		            async_bound = false;
		        }
				ImageActivity.this.finish();	
			}
		}
}
