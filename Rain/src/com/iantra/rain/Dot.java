package com.iantra.rain;

import android.graphics.Color;

import com.iantra.framework.Graphics;

public class Dot extends GameObject{
	float screenHeight;
	int type;
	float speed;
	int r, g, b;
	int tr, tg, tb;
	
	public Dot(float xx, float yy, float sh, int t, float s, int[] c){
		setX(xx);
		setY(yy);
		setWidth(120);
		setHeight(160);
		screenHeight = sh;
		type = t; 				 //Dot type, 1 = good, 2 = bad, 3 = changing
		speed = s;
		tr = c[0];
		tg = c[1];
		tb = c[2];
		r = c[0];
		g = c[1];
		b = c[2];
	}
	
	public int getType(){
		return type;
	}
	
	public void setType(int t){
		type = t;
	}
	
	public void setColor(int[] c){
		tr = c[0];
		tg = c[1];
		tb = c[2];
		r = c[0];
		g = c[1];
		b = c[2];
	}
	public void setTargetColor(int[] c){
		tr = c[0];
		tg = c[1];
		tb = c[2];
	}
	
	public int[]getTargetColor(){
		return new int[]{tr,tg,tb};
	}
	
	public int[] getColor(){
		return new int[]{r,g,b};
	}
	public void update(float _dt){
		setY(getY()+screenHeight * _dt * speed);
		
		if(tr != r){
			r += (tr - r)/Math.abs(tr - r)*_dt*screenHeight*speed;
		}
		if(tg != g){
			g += (tg - g)/Math.abs(tg - g)*_dt*screenHeight*speed;
		}
		if(tb != b){
			b += (tb - b)/Math.abs(tb - b)*_dt*screenHeight*speed;
		}
	}
}
