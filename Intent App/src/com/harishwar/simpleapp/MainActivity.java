package com.harishwar.simpleapp;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;

public class MainActivity extends Activity {
	
	Button b1;
    boolean Clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);     
        b1 = (Button) findViewById(R.id.button_SJSU);
        b1.setOnClickListener(HomePage);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //First Button to open SJSU Home page
    View.OnClickListener HomePage = new View.OnClickListener() {
        public void onClick(View v) {          
            goToUrl ( "http://sjsu.edu/");
            Clicked = true;
        }
      };
    //Second Button Activity
    public void Visited (View view) {
        //Check if first button is clicked or not
    	if(Clicked){
        	//Alert after visiting SJSU Home page
        	new AlertDialog.Builder(this)
        	.setTitle("Assignment Complete")
        	.setMessage("You can exit now!")
        	.setPositiveButton("Okay", null)
        	.setNegativeButton("Exit",
				new DialogInterface.OnClickListener() {
        			public void onClick(DialogInterface dialog,
					int id) {
					MainActivity.this.finish();
					}
				})
        	.show(); 
        }
        else
        {
        	//Show Alert To Visit SJU Home Page
        	Toast.makeText(this, "Click on First Button to open  San Jose State Unviersity Home Page", Toast.LENGTH_SHORT)
        	.show();  
        }
    }
    // Intent to Launch Browser
    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
 
    }
