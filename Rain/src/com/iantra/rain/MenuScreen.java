package com.iantra.rain;


import java.util.List;

import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.iantra.framework.Game;
import com.iantra.framework.Graphics;
import com.iantra.framework.Screen;
import com.iantra.framework.Input.TouchEvent;


public class MenuScreen extends Screen {
	Graphics g;
	Paint p = new Paint();
	int ty;
	String t[] = {"Rain", "Play", "Scores"};
	int sizes[] = {Assets.screenWidth/4, Assets.screenWidth/7, Assets.screenWidth/8};
	int yPositions[] = {Assets.screenHeight/6, Assets.screenHeight/3, Assets.screenHeight*10/21};
	Rect[] bounds;
	boolean isButtonPressed;
	
    public MenuScreen(Game game) {
        super(game);
    	p.setTypeface(Assets.roboto);
    	p.setAntiAlias(true);
    	p.setTextAlign(Paint.Align.CENTER);
        g = game.getGraphics();
        isButtonPressed = false;
        bounds = new Rect[3];
        for(int i = 0; i < t.length; i++){
        	bounds[i] = new Rect();
        	p.setTextSize(sizes[i]);
        	p.getTextBounds(t[i], 0, t[i].length(), bounds[i]);
        	bounds[i].offset(Assets.screenWidth/2-bounds[i].width()/2, yPositions[i]);
        }
    }


    @Override
    public void update(float deltaTime) {
    	List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
    	int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
	    	try{
	            TouchEvent event = touchEvents.get(i);
	            if(event.y >= yPositions[0]){
	            /*(event.x >= (float)Assets.screenWidth/3 && event.x <= (float)Assets.screenWidth*2/3 &&
	            	event.y >= (float)Assets.screenHeight/5 && event.y <= (float)Assets.screenHeight*2/5){*/
	            	
		            if (event.type == TouchEvent.TOUCH_DOWN) {
		            	ty = event.y;
		               isButtonPressed = true;
		            }
		            if (event.type == TouchEvent.TOUCH_DRAGGED){
		            	ty = event.y;
		            }
		            if(event.type == TouchEvent.TOUCH_UP){
		            	if(bounds[1].contains(event.x, event.y))
		            		game.setScreen(new GameScreen(game));
		            	
		            	else if(bounds[2].contains(event.x, event.y))
		            		game.setScreen(new ScoreScreen(game));
		            	
		            	else
			            	isButtonPressed = false;
		            }
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
    	
    	
    	
    	p.setTextSize(sizes[0]);
    	g.drawString(t[0], Assets.screenWidth/2, yPositions[0], p);
		g.drawScaledImage(Assets.dot, (int)((float)Assets.screenWidth/2-(bounds[0].width()*2/3)), (int)((float)Assets.screenHeight/6-bounds[0].height()/1.9), 20, 20, 0, 0, 60, 60, Assets.dot.colorize(new int[]{0,0,0}));
    	
		p.setTextSize(sizes[1]);
    	g.drawString(t[1], Assets.screenWidth/2, yPositions[1], p);
    	
    	p.setTextSize(sizes[2]);
    	g.drawString(t[2], Assets.screenWidth/2, yPositions[2], p);
    	if(isButtonPressed){
    		g.drawScaledImage(Assets.dot, (int)((float)Assets.screenWidth/4), ty/*(int)((float)Assets.screenHeight/3-bounds.height()/2.3)*/, 16, 16, 0, 0, 60, 60, Assets.dot.colorize(new int[]{0,0,0}));
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
    	android.os.Process.killProcess(android.os.Process.myPid());
    }
    
    boolean isInBounds(float x, float y, Rect r){
    	if(x >= r.left && x <= r.right && y >= r.top && y <= r.bottom){
    		return true;
    	}
    	return false;
    }
}
