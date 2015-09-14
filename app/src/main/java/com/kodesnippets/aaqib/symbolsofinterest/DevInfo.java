package com.kodesnippets.aaqib.symbolsofinterest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.poi.symbolsofinterest.R;

public class DevInfo extends ActionBarActivity {
	Button twitterbtn, gplusbtn, tumblrbtn;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.devinfo);
		twitterbtn = (Button) findViewById(R.id.twit);
		twitterbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("https://twitter.com/iAaqibHussain");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);	
			
			}
		});
		gplusbtn = (Button) findViewById(R.id.gplus);
		gplusbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("https://plus.google.com/+AaqibHussain");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);	
			
			}
		});
		tumblrbtn = (Button) findViewById(R.id.tumblr);
		tumblrbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("http://aaqibhussain.tumblr.com/");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);	
			
			}
		});
				


	
	
	
	}
	
	
		


}
 
