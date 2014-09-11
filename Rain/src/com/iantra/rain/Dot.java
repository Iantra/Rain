package com.iantra.rain;

import com.iantra.framework.Graphics;

public class Dot extends GameObject{
	float screenHeight;
	int type;
	float speed;
	public Dot(float xx, float yy, float sh, int t, float s){
		setX(xx);
		setY(yy);
		setWidth(120);
		setHeight(160);
		screenHeight = sh;
		type = t;
		speed = s;
	}
	
	public int getType(){
		return type;
	}
	public void update(float _dt){
		setY(getY()+screenHeight * _dt * speed);
	}
}
