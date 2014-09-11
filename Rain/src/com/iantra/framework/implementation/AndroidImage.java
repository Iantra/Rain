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
    public Paint colorize(float r, float g, float b){
    	    float[] colorTransform = {
    	            0, r, 0, 0, 0, 
    	            0, 0, g, 0, 0,
    	            0, 0, 0, b, 0, 
    	            0, 0, 0, 1f, 0};

    	    ColorMatrix colorMatrix = new ColorMatrix();
    	    colorMatrix.setSaturation(0f); //Remove Colour 
    	    colorMatrix.set(colorTransform); //Apply the Red

    	    ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
    	    Paint paint = new Paint();
    	    paint.setColorFilter(colorFilter);   

    	    return paint;
    }
}
 