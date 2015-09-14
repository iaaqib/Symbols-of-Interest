package com.kodesnippets.aaqib.symbolsofinterest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.provider.MediaStore;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.ImageView;
import android.view.View.OnClickListener;

import com.poi.symbolsofinterest.R;

public class CameraActivity extends BrowsePic {

	private Uri mImageCaptureUri;
	private ImageView mImageView;
	
	private static final int PICK_FROM_CAMERA = 1;
	private static final int CROP_FROM_CAMERA = 2;
	private static final int PICK_FROM_FILE = 3;
	
	//ZoomInOut Variables
	ImageView imageDetail; 
	Matrix matrix = new Matrix(); 
	Matrix savedMatrix = new Matrix(); 
	PointF startPoint = new PointF(); 
	PointF midPoint = new PointF(); 
	float oldDist = 1f; 
	static final int NONE = 0; 
	static final int DRAG = 1; 
	static final int ZOOM = 2; 
	int mode = NONE;
	
	Save sv = new Save();

	
    @SuppressLint("NewApi") public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        final ImageView resizeFrame = (ImageView) findViewById(R.id.image2);
        Toast.makeText(getApplicationContext(),"Drag and Pinch to move and resize the symbol", Toast.LENGTH_LONG).show();
        resizeFrame.setOnTouchListener(new View.OnTouchListener() {
           

        	public boolean onTouch(View v, MotionEvent event) {
        		 

        		    
        		
        		System.out.println("matrix=" + savedMatrix.toString());
        		switch (event.getAction() & MotionEvent.ACTION_MASK) 
        		{ 
        		case MotionEvent.ACTION_DOWN: 
        			savedMatrix.set(matrix); 
        			startPoint.set(event.getX(), event.getY()); 
        			mode = DRAG; break; 
        			case MotionEvent.ACTION_POINTER_DOWN: 
        				oldDist = spacing(event); 
        				if (oldDist > 10f) 
        				{ 
        					savedMatrix.set(matrix); 
        					midPoint(midPoint, event);
        					mode = ZOOM; } 
        				break; 
        					case MotionEvent.ACTION_UP: 
        						case MotionEvent.ACTION_POINTER_UP: 
        							mode = NONE; break; 
        							case MotionEvent.ACTION_MOVE: 
        								if (mode == DRAG) 
        								{ 
        									matrix.set(savedMatrix); 
        									matrix.postTranslate(event.getX() - startPoint.x, event.getY() - startPoint.y); 
        									} 
        								else if (mode == ZOOM) 
        								{ 
        									float newDist = spacing(event); 
        									if (newDist > 10f) 
        									{ matrix.set(savedMatrix); 
        									float scale = newDist / oldDist; matrix.postScale(scale, scale, midPoint.x, midPoint.y); 
        									} }
        								break; 
        								} 
        		resizeFrame.setImageMatrix(matrix); 
        		
        		return true; } 
        	@SuppressLint("FloatMath") 
        	private float spacing(MotionEvent event) 
        	{ float x = event.getX(0) - event.getX(1); 
        	float y = event.getY(0) - event.getY(1); 
        	return FloatMath.sqrt(x * x + y * y); 
        	}
        	private void midPoint(PointF point, MotionEvent event) 
        	{ float x = event.getX(0) + event.getX(1); 
        	float y = event.getY(0) + event.getY(1); 
        	point.set(x / 2, y / 2); 
   		
        		}
        	});
        		
        		

        	
        
        final String [] items			= new String [] {"Take from Camera", "Select from Gallery"};				
		ArrayAdapter<String> adapter	= new ArrayAdapter<String> (this, android.R.layout.select_dialog_item,items);
		AlertDialog.Builder builder		= new AlertDialog.Builder(this);
		
		builder.setTitle("Select Image");
		builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
			public void onClick( DialogInterface dialog, int item ) { //pick from camera
				if (item == 0) {
					Intent intent 	 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					
					mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
									   "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));

					intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);

					try {
						intent.putExtra("return-data", true);
						
						startActivityForResult(intent, PICK_FROM_CAMERA);
					} catch (ActivityNotFoundException e) {
						e.printStackTrace();
					}
				} else { //pick from file
					Intent intent = new Intent();
					
	                intent.setType("image/*");
	                intent.setAction(Intent.ACTION_GET_CONTENT);
	                
	                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);


				}
			}
		} );
		
		final AlertDialog dialog = builder.create();
		
		Button button 	= (Button) findViewById(R.id.brwp);
		mImageView		= (ImageView) findViewById(R.id.image1);
		
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mImageView.destroyDrawingCache();
                resizeFrame.destroyDrawingCache();
                dialog.show();

            }
		});

		Button saveButton = (Button) findViewById(R.id.savep);
		saveButton.setOnClickListener(new OnClickListener() {
		
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			//Combining Loaded image and the frame
			

			mImageView.setDrawingCacheEnabled(true);
                mImageView.buildDrawingCache();
                Bitmap bitmap = mImageView.getDrawingCache();

                if(mImageView.getDrawable() == null)
            {
            	Toast.makeText(getApplicationContext(),"Please add a picture to proceed", Toast.LENGTH_SHORT).show();
            }

            else
            {
                if (mImageView.getDrawable() != null){


                    resizeFrame.destroyDrawingCache();


                }
            resizeFrame.setDrawingCacheEnabled(true);
                       Bitmap bitmap2 = resizeFrame.getDrawingCache();
            Bitmap receiveCanvas = overlay(bitmap,bitmap2);
            //String CurrentDateandTime = sv.getCurrentDateAndTime();
            sv.SaveImage(CameraActivity.this, receiveCanvas);}}});
            
					
			}	
		
    
    boolean storeImage(Bitmap imageData) {
    	//get path to external storage (SD card)
    	String iconsStoragePath = Environment.getExternalStorageDirectory() + "/Symbols Of Interest";
    	File sdIconStorageDir = new File(iconsStoragePath);

    	//create storage directories, if they don't exist
    	sdIconStorageDir.mkdirs();

    	try {
    		String filePath = sdIconStorageDir.toString() + "SOI";
    		FileOutputStream fileOutputStream = new FileOutputStream(filePath);

    		BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);

    		//choose another format if PNG doesn't suit you
    		imageData.compress(CompressFormat.PNG, 100, bos);

    		bos.flush();
    		bos.close();

    	} catch (FileNotFoundException e) {
    		Log.w("TAG", "Error saving image file: " + e.getMessage());
    		return false;
    	} catch (IOException e) {
    		Log.w("TAG", "Error saving image file: " + e.getMessage());
    		return false;
    	}

    	return true;
    }
     
    
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode != RESULT_OK) return;
	   
	    switch (requestCode) {
		    case PICK_FROM_CAMERA:
		    	doCrop();
		    	
		    	break;
		    	
		    case PICK_FROM_FILE: 
		    	mImageCaptureUri = data.getData();
		    	
		    	doCrop();

		    	break;	    	
	    
		    case CROP_FROM_CAMERA:	    	
		        Bundle extras = data.getExtras();
	
		        if (extras != null) {	        	
		            Bitmap photo = extras.getParcelable("data");
		            
		            mImageView.setImageBitmap(photo);
		        }
	
		        File f = new File(mImageCaptureUri.getPath());            
		        
		        if (f.exists()) f.delete();
	
		        break;

	    }
	}
    
    private void doCrop() {
		final ArrayList<CropOption> cropOptions = new ArrayList<CropOption>();
    	
    	Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        
        List<ResolveInfo> list = getPackageManager().queryIntentActivities( intent, 0 );
        
        int size = list.size();
        
        if (size == 0) {	        
        	Toast.makeText(this, "Can not find image crop app", Toast.LENGTH_SHORT).show();
        	
            return;
        } else {
        	intent.setData(mImageCaptureUri);
            
            intent.putExtra("outputX", 310);
            intent.putExtra("outputY", 310);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);
            
        	if (size == 1) {
        		Intent i 		= new Intent(intent);
	        	ResolveInfo res	= list.get(0);
	        	
	        	i.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
	        	
	        	startActivityForResult(i, CROP_FROM_CAMERA);
        	} else {
		        for (ResolveInfo res : list) {
		        	final CropOption co = new CropOption();
		        	
		        	co.title 	= getPackageManager().getApplicationLabel(res.activityInfo.applicationInfo);
		        	co.icon		= getPackageManager().getApplicationIcon(res.activityInfo.applicationInfo);
		        	co.appIntent= new Intent(intent);
		        	
		        	co.appIntent.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
		        	
		            cropOptions.add(co);
		        }
	        
		        CropOptionAdapter adapter = new CropOptionAdapter(getApplicationContext(), cropOptions);
		        
		        AlertDialog.Builder builder = new AlertDialog.Builder(this);
		        builder.setTitle("Choose Crop App");
		        builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
		            public void onClick( DialogInterface dialog, int item ) {
		                startActivityForResult( cropOptions.get(item).appIntent, CROP_FROM_CAMERA);
		            }
		        });
	        
		        builder.setOnCancelListener( new DialogInterface.OnCancelListener() {
		            @Override
		            public void onCancel( DialogInterface dialog ) {
		               
		                if (mImageCaptureUri != null ) {
		                    getContentResolver().delete(mImageCaptureUri, null, null );
		                    mImageCaptureUri = null;
		                }
		            }
		        } );
		        
		        AlertDialog alert = builder.create();
		        
		        alert.show();
        	}
        }
	}

    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        return bmOverlay;
    }

}