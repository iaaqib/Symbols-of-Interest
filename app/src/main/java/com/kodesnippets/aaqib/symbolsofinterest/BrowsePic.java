package com.kodesnippets.aaqib.symbolsofinterest;



import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.poi.symbolsofinterest.R;

@SuppressWarnings("deprecation")
public class BrowsePic extends ActionBarActivity {
		 
	//the images to display
 Integer[] imageIDs = {
		 R.drawable.pic4,
		 R.drawable.pic5,
		 R.drawable.pic6,
		 R.drawable.pic7,
		 R.drawable.pic8,
		 R.drawable.pic9,
		 R.drawable.pic10,
		 R.drawable.pic11,
		 R.drawable.pic13,
		 R.drawable.pic14,
		 R.drawable.pic15 };
 @Override
 protected void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.browsepic);
 
/* Button openimg = (Button) findViewById(R.id.savep);
 openimg.setOnClickListener(new View.OnClickListener() {
      
     @Override
     public void onClick (View arg0) {
  	 Intent intent = new Intent("com.poi.symbolsofinterest.CAMGAL");
    startActivity(intent);
        
     }
     });*/
 
 // Note that Gallery view is deprecated in Android 4.1---
 Gallery gallery = (Gallery) findViewById(R.id.gallery1);
 gallery.setAdapter(new ImageAdapter(this));
 gallery.setOnItemClickListener(new OnItemClickListener() {
 public void onItemClick(AdapterView<?> parent, View v, int position,long id)
 {
 //Toast.makeText(getBaseContext(),"pic" + (position + 1) + " selected",
// Toast.LENGTH_SHORT).show();
 // display the images selected
 ImageView imageView = (ImageView) findViewById(R.id.image2);
 imageView.setImageResource(imageIDs[position]); 
 }
 });
 

 

	 
	 
 
 
 
 }
     
 
 
 
 
 
 
 class ImageAdapter extends BaseAdapter {
 private Context context;
 private int itemBackground;
 public ImageAdapter(Context c)
 {
 context = c;
 // sets a grey background; wraps around the images
 TypedArray a =obtainStyledAttributes(R.styleable.MyGallery);
 itemBackground = a.getResourceId(R.styleable.MyGallery_android_galleryItemBackground, 0);
 a.recycle();
 }
 // returns the number of images
 public int getCount() {
 return imageIDs.length;
 }
 // returns the ID of an item
 public Object getItem(int position) {
 return position;
 }
 // returns the ID of an item
 public long getItemId(int position) {
 return position;
 }
 // returns an ImageView view
 public View getView(int position, View convertView, ViewGroup parent) {
 ImageView imageView = new ImageView(context);
 imageView.setImageResource(imageIDs[position]);
 imageView.setLayoutParams(new Gallery.LayoutParams(100, 100));
 imageView.setBackgroundResource(itemBackground);
 return imageView;
 }
 }
}