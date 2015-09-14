package com.kodesnippets.aaqib.symbolsofinterest;

import com.poi.symbolsofinterest.R;
import com.poi.symbolsofinterest.R.id;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Context;


public class MenuClass extends ActionBarActivity {
	Context context;
	Button addpic,devinfo,poiinfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		addpic = (Button) findViewById(id.btn1);
		
	addpic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent bPic = new Intent("com.poi.symbolsofinterest.CAMGAL");
				startActivity(bPic);
			}
		});
	poiinfo = (Button) findViewById(id.btn3);
	poiinfo.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent bWeb = new Intent("com.poi.symbolsofinterest.POIWIKIA");
			startActivity(bWeb);
			 
		}
	});
	devinfo = (Button) findViewById(id.btn2);
	devinfo.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent bDev = new Intent("com.poi.symbolsofinterest.DEVINFOCLASS");
			startActivity(bDev);
		}
	});


	
	}
	
}
