package com.iantra.framework;

import android.graphics.Paint;

import com.iantra.framework.Graphics.ImageFormat;

public interface Image {
    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();
    public Paint colorize(float r, float g, float b);
}