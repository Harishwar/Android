package com.harishwar.sensorapp;

import java.io.IOException;

import com.harishwar.sensorapp.R;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Toast;
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
		
		Context context = this;
		PackageManager packageManager = context.getPackageManager();

		//Check if flash is supported
		if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
			//Timer for 10 Seconds
			new CountDownTimer(10000, 1000) {
				public void onTick(long millisUntilFinished) {
					//Turn on Flash at 5th Second
					if(millisUntilFinished<=5999 && millisUntilFinished>=5001){		    		    	 
						System.out.println("Flash"+"Turned on Flash Light at "+millisUntilFinished+" millis");
			    		flashon();
			    	} 
			    }
				//Turn on Blinking Flash at 10th Second
				public void onFinish() {
					blink();
					System.out.println("Blink"+"Blinking Flash Light");
				}
			}.start();		
		}
		else{
			CharSequence text = "Flash Not Supported";
			finish();
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
		}
		
		Light=(ToggleButton)findViewById(R.id.LightButton);		
		Light.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(((ToggleButton) v).isChecked()){
					if(!LightOn){
						flashon();
						System.out.println("ToggleButton"+"FlashLight On");
					} 
				} else {
					flashoff();
					System.out.println("ToggleButton"+"FlashLight Off");
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
	//Turn on Flash Light
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
	//Turn of Flash Light
	public void flashoff(){
		if (params.getFlashMode()!=Parameters.FLASH_MODE_OFF){
			params.setFlashMode(Parameters.FLASH_MODE_OFF);
			camera.setParameters(params);
			camera.stopPreview();
			LightOn = false;
			}
	}
	//Blinking Flashlight
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
			//Loop for Blinking Flashlight 5 times
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
	//On and Off Flashlight
	public void toggleFlashLight() {
        if (!LightOn) { 
        	flashon();
        	System.out.println("Blink"+"FlashLight On");
        } else { 
            flashoff();
            System.out.println("Blink"+"FlashLight Off");
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
