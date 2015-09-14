package com.kodesnippets.aaqib.symbolsofinterest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
public class Save {
    private Context TheThis;
    private String NameOfFolder = "/Symbols Of Interest";
    private String NameOfFile   = "SOI";
    public void SaveImage(Context context, Bitmap ImageToSave){
       TheThis = context;
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath()+ NameOfFolder;
        String CurrentDateAndTime= getCurrentDateAndTime();
        File dir = new File(file_path);
         
        if(!dir.exists()){
            dir.mkdirs();
        }
         
        File file = new File(dir, NameOfFile +CurrentDateAndTime+ ".jpg");
         
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            ImageToSave.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
            fOut.flush();
            fOut.close();
            MakeSureFileWasCreatedThenMakeAvabile(file);
            AbleToSave();
             
        } 
        catch (FileNotFoundException e) {UnableToSave();}
        catch (IOException e){UnableToSave();}
         
         
          
    }
 
     
 
   /* public void MakeSureFileWasCreatedThenMakeAvabile(File file) {
        MediaScannerConnection.scanFile(TheThis,
                new String[] { file.toString() }, null,
                new MediaScannerConnection.OnScanCompletedListener() {
            public void onScanCompleted(String path, Uri uri) {
                Log.e("ExternalStorage", "Scanned " + path + ":");
                Log.e("ExternalStorage", "-> uri=" + uri);
                
            }
        }); */
         
   
        public void MakeSureFileWasCreatedThenMakeAvabile(File file) {
            MediaScannerConnection.scanFile(TheThis,
                    new String[] { file.toString() }, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String path, Uri uri) {
                    Log.e("ExternalStorage", "Scanned " + path + ":");
                    Log.e("ExternalStorage", "-> uri=" + uri);
                    
                }
            });
             
        
    
    
    
    
    
    }
 
 
 
    public String getCurrentDateAndTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
     
     
private void UnableToSave() {
    Toast.makeText(TheThis, "Picture cannot be saved to the gallery", Toast.LENGTH_SHORT).show();
         
    }
 
private void AbleToSave() {
    Toast.makeText(TheThis, "Picture was saved successfully", Toast.LENGTH_SHORT).show();
         
    }

public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
    Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
    Canvas canvas = new Canvas(bmOverlay);
    canvas.drawBitmap(bmp1, new Matrix(), null);
    canvas.drawBitmap(bmp2, 0, 0, null);
    return bmOverlay;
}






public void createImageFromBitmap(Bitmap combinedImage) {
	// TODO Auto-generated method stub
	

	FileOutputStream fileOutputStream = null;
	try {

	    // create a File object for the parent directory
	    File wallpaperDirectory = new File("/sdcard/Capture/");
	    // have the object build the directory structure, if needed.
	    wallpaperDirectory.mkdirs();

	    //Capture is folder name and file name with date and time
	    fileOutputStream = new FileOutputStream(String.format(
	            "/sdcard/Capture/%d.jpg",
	            System.currentTimeMillis()));

	    // Here we Resize the Image ...
	    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    combinedImage.compress(Bitmap.CompressFormat.JPEG, 100,
	            byteArrayOutputStream); // bm is the bitmap object
	    byte[] bsResized = byteArrayOutputStream.toByteArray();


	    fileOutputStream.write(bsResized);
	    fileOutputStream.close();


	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	}

}}