package com.kodesnippets.aaqib.symbolsofinterest;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.poi.symbolsofinterest.R;

public class PoiWikia extends ActionBarActivity {
	

	
	TextView machine;
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poiwikia);
		machine = (TextView) findViewById(R.id.poiwikiL);
		machine.setMovementMethod(LinkMovementMethod.getInstance());
		    
	    }
	
		
	}



 
