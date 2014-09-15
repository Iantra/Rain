package com.iantra.framework.implementation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.view.Display;

import com.iantra.framework.Image;
import com.iantra.framework.Graphics.ImageFormat;

public class AndroidImage implements Image {
    Bitmap bitmap;
    ImageFormat format;
    
    public AndroidImage(Bitmap bitmap, ImageFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public ImageFormat getFormat() {
        return format;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }      
    
    @Override
    public Paint colorize(int[] c){
    		float[] ce = new float[]{(float)c[0], (float)c[1], (float)c[2]};
    	    float[] colorTransform = {
    	            0, ce[0]/255, 0, 0, 0, 
    	            0, 0, ce[1]/255, 0, 0,
    	            0, 0, 0, ce[2]/255, 0, 
    	            0, 0, 0, 1f, 0};

    	    ColorMatrix colorMatrix = new ColorMatrix();
    	    colorMatrix.setSaturation(0f); //Remove Colour 
    	    colorMatrix.set(colorTransform); //Apply the Color

    	    ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
    	    Paint paint = new Paint();
    	    paint.setColorFilter(colorFilter);   

    	    return paint;
    }
}
 