package com.harishwar.sensorapp;

import java.io.IOException;

import com.harishwar.sensorapp.R;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	Camera camera;
    Parameters params;
    int i;
    int delay = 500;
    private ToggleButton Light;
    private boolean LightOn;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		
		//Timer for 10 Seconds
		new CountDownTimer(10000, 1000) {
			public void onTick(long millisUntilFinished) {
				if(millisUntilFinished<=5999 && millisUntilFinished>=5001){		    		    	 
					Log.d("5000",""+millisUntilFinished);
		    		flashon();
		    	} 
		    }
			public void onFinish() {
				blink();
			}
		}.start();
		    		  		    	
		Light=(ToggleButton)findViewById(R.id.LightButton);		
		Light.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(((ToggleButton) v).isChecked()){
					if(!LightOn){
						flashon();
						//LightOn = true;
					} else {
						flashoff();
						//LightOn = false;
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void flashon(){
		try{
			if(camera==null){
				camera=Camera.open();
				params = camera.getParameters();
				if(params.getFlashMode()!=Parameters.FLASH_MODE_TORCH){
					params.setFlashMode(Parameters.FLASH_MODE_TORCH);
					camera.setParameters(params);
					camera.startPreview();
					LightOn = true;
				}
			} else if(params.getFlashMode()!=Parameters.FLASH_MODE_TORCH){
					params.setFlashMode(Parameters.FLASH_MODE_TORCH);
					camera.setParameters(params);
		            camera.startPreview();
		            LightOn = true;
			}
		} catch (Exception e) {
            e.printStackTrace();
            camera.stopPreview();
            LightOn = false;
        }	
	}
	
	public void flashoff(){
		if (params.getFlashMode()!=Parameters.FLASH_MODE_OFF){
			params.setFlashMode(Parameters.FLASH_MODE_OFF);
			camera.setParameters(params);
			camera.stopPreview();
			LightOn = false;
			}
	}

	public void blink(){
		Thread t = new Thread() {
			public void run() {
				try {
					if (camera == null) {
                camera = Camera.open();
                try {
                    camera.setPreviewDisplay(null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                camera.startPreview();
            }

            for (int i=0; i <= 10; i++) {
                toggleFlashLight();
                sleep(delay);
            }

            if (camera != null) {
                camera.stopPreview();
                camera.release();
                camera = null;
            }
				} catch (Exception e){ 
					e.printStackTrace(); 
				}
			}
		};
		t.start();
	}
	
	public void toggleFlashLight() {
        if (!LightOn) { 
        	flashon();
        } else { 
            flashoff();
        }
    }

	@Override
	protected void onStop() {
		super.onStop();
 
		if (camera != null) {
			camera.release();
		}
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();		
	}
	
}
