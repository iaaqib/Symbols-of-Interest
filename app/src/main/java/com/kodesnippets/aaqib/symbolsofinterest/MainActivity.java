package com.kodesnippets.aaqib.symbolsofinterest;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import com.poi.symbolsofinterest.R;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
		
		Thread timer = new Thread(){
			public void run(){
			try{
				sleep(2000);
				
				
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
				
				
			}
			finally{
				
				Intent startAct = new Intent("com.poi.symbolsofinterest.MENUCLASS");
				startActivity(startAct);
				
			}
			
			}
			
		};
		timer.start();
	
	
	
	} 

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	









}
