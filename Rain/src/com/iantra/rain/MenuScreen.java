package com.iantra.rain;


import java.util.List;

import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;

import com.iantra.framework.Game;
import com.iantra.framework.Graphics;
import com.iantra.framework.Screen;
import com.iantra.framework.Input.TouchEvent;


public class MenuScreen extends Screen {
	Graphics g;
	boolean isButtonPressed;
	
    public MenuScreen(Game game) {
        super(game);
        g = game.getGraphics();
        isButtonPressed = false;
    }


    @Override
    public void update(float deltaTime) {
    	List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
    	int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
	    	try{
	            TouchEvent event = touchEvents.get(i);
	            if(event.x >= (float)Assets.screenWidth/3 && event.x <= (float)Assets.screenWidth*2/3 &&
	            	event.y >= (float)Assets.screenHeight/5 && event.y <= (float)Assets.screenHeight*2/5){
	            	
		            if (event.type == TouchEvent.TOUCH_DOWN) {
		               isButtonPressed = true;
		            }
		            if(event.type == TouchEvent.TOUCH_UP){
		            	 game.setScreen(new GameScreen(game));
		            }
	            }else if(event.type == TouchEvent.TOUCH_UP){
	            	isButtonPressed = false;
	            }
    		}catch(Exception e){
        	}
        }
    }


    @Override
    public void paint(float deltaTime) {
    	g.clearScreen(0xFEFEF1);
    	drawMenu();
    }
    
    void drawMenu(){
    	g.drawScaledImage(Assets.menu, 0, 0, Assets.screenWidth, Assets.screenHeight, 0, 0, 1080, 1920);
    	if(isButtonPressed){
    		g.drawScaledImage(Assets.select, (int)((float)Assets.screenWidth/3), (int)((float)Assets.screenHeight*1.9/6), 30, 30, 0, 0, 60, 60);
    	}
    }

    @Override
    public void pause() {
    	
    }
    
    @Override
    public void resume() {


    }


    @Override
    public void dispose() {


    }


    @Override
    public void backButton() {


    }
}
