package com.iantra.rain;

import android.util.Log;

public class Ground extends GameObject{
	float targetY;
	public Ground(float xx, float yy, int w, int h){
		super(xx, yy, w, h);
		targetY = yy;
	}
	
	public void setTargetY(float h){
		targetY = h;
	}
	
	public float getTargetY(){
		return targetY;
	}
	public void update(float _dt){
		if((int)getY() != (int)targetY){
			setY(getY()+((targetY - getY())* _dt * 3));
		}else{
		}
	}
}
