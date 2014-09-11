package com.iantra.rain;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import com.iantra.framework.Game;
import com.iantra.framework.Graphics;
import com.iantra.framework.Image;
import com.iantra.framework.Screen;
import com.iantra.framework.Input.TouchEvent;

public class GameScreen extends Screen {
    enum GameState {
        Running, /*Paused,*/ GameOver
    }

    GameState state = GameState.Running;

    // Variable Setup
    // You would create game objects here.
    Paint paint;    
    final int BG_COLOR = 0xFEFEF1;
    float spawnTimer;
    float spawnRate;
    float yStep;
    int screenWidth;
    int screenHeight;
    int lvl;
    int[] gameColor;
    Graphics g;
    ArrayList<Dot> dots;
    Ground ground;

    public GameScreen(Game game) {
        super(game);
        // Initialize game objects here
        g = game.getGraphics();
        screenWidth = Assets.screenWidth;
        screenHeight = Assets.screenHeight;
        yStep = screenHeight/25;
        spawnTimer = 0;
        spawnRate = .6f;
        dots = new ArrayList<Dot>();
        gameColor = new int[3];
        lvl = 1;
        
        ground = new Ground(0, screenHeight-yStep, screenWidth*2, screenHeight);
       
        newRandomColor();
        // Defining a paint object
        paint = new Paint();
        resetPaint();
    }


	@Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        /*if (state == GameState.Paused)
            updatePaused(touchEvents);*/
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }
    
    
    private void updateRunning(List<TouchEvent> touchEvents, float _dt) {
        
        //This is identical to the update() method from our Unit 2/3 game.
        // 1. All touch input is handled here:
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
	    	try{
	            TouchEvent event = touchEvents.get(i);
	
	            if (event.type == TouchEvent.TOUCH_DOWN) {
	                handlePress(event);
	            }else{
	            }
    		}catch(Exception e){
        	}
        }
        
        //Spawn Dots
       if(spawnTimer >= spawnRate){
    	   spawnTimer = 0;
    	   spawnDot();
       }
        
        
        // Update()s
       for(int i = 0; i < dots.size(); i++){
    	   Dot dot = dots.get(i);
	       dot.update(_dt);
	       if(isInBoundsNormal(dot.getX(), dot.getY(), ground)){
	       		dots.remove(i);
	       		i--;
	       		if(dot.getType() == 1){
	       			ground.setTargetY(ground.getTargetY()-yStep);
	       		}else{
	           		ground.setTargetY(ground.getTargetY()+(yStep*(float) lvl * 1.5f));
	       		}
	       }
       }
       ground.update(_dt);
       if(ground.getY() <= screenHeight/2){
    	   ground.setTargetY(screenHeight-yStep);
    	   newRandomColor();
    	   lvl++;
    	   yStep *= .8;
    	   spawnRate *= .9;
       }
       
       if(ground.getY() >= screenHeight){
    	   state = GameState.GameOver;
    	   /*for(int i = 0; i < dots.size(); i++){
    	     		   	dots.remove(i);
    		       		i--;
            }
    	   ground.setTargetY(screenHeight-yStep);
    	   newRandomColor();*/
       }
       
       Log.d("length", ""+dots.size());
        //Times
       spawnTimer += _dt;
    }

    
    private void handlePress(TouchEvent e) {
        TouchEvent event = e;
        for(int i = 0; i < dots.size(); i++){
     	   Dot dot = dots.get(i);
     	   if(isInBoundsMiddle(event.x, event.y, dot)){
     		   if(dot.getType() == 1){
     			   dots.remove(i);
     			   i--;
     			   ground.setTargetY(ground.getTargetY()+(yStep*(float) lvl * 0.5f));
     		   }else if(dot.getType() == 2){
	     		   	dots.remove(i);
		       		i--;
		       		ground.setTargetY(ground.getTargetY()-(yStep*0.5f));
	     	   }else if(dot.getType() == 3){
	     		   	dots.remove(i);
		       		i--;
		       		ground.setTargetY(ground.getTargetY()-(yStep*0.5f));
	     	   }
     	   }
        }
	}

/*	private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {

            }
        }
    }*/

    private void updateGameOver(List<TouchEvent> touchEvents) {
    	int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
	    	try{
	            TouchEvent event = touchEvents.get(i);
	            if(event.x >= (float)Assets.screenWidth/2 - 100 && event.x <= (float)Assets.screenWidth/2 + 100 &&
	                	event.y >= (float)Assets.screenHeight*2/5+125 && event.y <= (float)Assets.screenHeight*2/5+175){
	            	game.setScreen(new GameScreen(game));
	            } 
	    	}catch(Exception e){
	    		}
	    	}
    	
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        // First draw the game elements.
        g.clearScreen(BG_COLOR);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Running)
            drawRunningUI();
        /*if (state == GameState.Paused)
            drawPausedUI();*/
        if (state == GameState.GameOver)
            drawGameOverUI();

    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;

        // Call garbage collector to clean up memory.
        System.gc();
    }

    private void drawRunningUI() {
        for(int i = 0; i < dots.size(); i++){
        	Dot dot = dots.get(i);
        	float newR = (float)gameColor[0]/255;
        	float newG = (float)gameColor[1]/255;
        	float newB = (float)gameColor[2]/255;
        	if(dot.getType() == 1){
        		/*Test hitbox (for dot type 2) *///g.drawRect((int)(dot.getX() - dot.getWidth()/2), (int)(dot.getY() - dot.getHeight()/2), (int) dot.getWidth(), (int) dot.getHeight(), Color.rgb(gameColor[0], gameColor[1], gameColor[2]));
        		g.drawImage(Assets.dot, (int)(dot.getX() - Assets.dot.getWidth()/2), (int)(dot.getY() - Assets.dot.getHeight()/2), Assets.dot.colorize(newR, newG, newB));
        	}else if(dot.getType() == 2){
        		g.drawImage(Assets.dot, (int)(dot.getX() - Assets.dot.getWidth()/2), (int)(dot.getY() - Assets.dot.getHeight()/2), Assets.dot.colorize(1-newR, 1-newG, 1-newB));
        	}else if(dot.getType() == 3){
        		if(dot.getY() <= ground.getTargetY()/4)
        			g.drawImage(Assets.dot, (int)(dot.getX() - Assets.dot.getWidth()/2), (int)(dot.getY() - Assets.dot.getHeight()/2), Assets.dot.colorize(newR, newG, newB));
        		else
        			g.drawImage(Assets.dot, (int)(dot.getX() - Assets.dot.getWidth()/2), (int)(dot.getY() - Assets.dot.getHeight()/2), Assets.dot.colorize(1-newR, 1-newG, 1-newB));
        	}
        }
        g.drawRect(ground.getX(), ground.getY(), ground.getWidth(), ground.getHeight(), Color.rgb(gameColor[0], gameColor[1], gameColor[2]));
        g.drawString(""+lvl, 10, 50, paint);
    }
    
/*    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);

    }*/

    private void drawGameOverUI() {
    	
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, screenWidth+1, screenHeight+1, BG_COLOR);
        paint.setColor(Color.BLACK);
        paint.setTextAlign(Paint.Align.CENTER);
        g.drawString("GAME OVER.", screenWidth/2, screenHeight*2/5, paint);
        g.drawString("You lost at level " + lvl + ".", screenWidth/2, screenHeight*2/5+40, paint);
        paint.setTextSize(50);
        g.drawString("try again", screenWidth/2, screenHeight*2/5+150, paint);
        resetPaint();
    }

    @Override
    public void pause() {
        /*if (state == GameState.Running)
            state = GameState.Paused;*/

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }
    
    
    //Iantra Functions
    
    void spawnDot(){
    	int typeCap = 1;
    	if(lvl <= 2){
    		typeCap = lvl + 1;
    	}else{
    		typeCap = 3;
    	}
    	Dot dot = new Dot(new Random().nextInt(screenWidth), -20, screenHeight, new Random().nextInt(typeCap)+1, (float)Math.sqrt((float)lvl * 0.80 + new Random().nextFloat() * (float)lvl * 0.7)/(3 + lvl/3));
    	dots.add(dot);
    	
    }
    boolean isInBoundsNormal(float x, float y, GameObject o){
    	if(x >= o.getX() && x <= o.getX() + o.getWidth() && y >= o.getY() && y <= o.getY() + o.getHeight()){
    		return true;
    	}
    	return false;
    }
    
    boolean isInBoundsMiddle(float x, float y, GameObject o){
    	if(x >= o.getX() - o.getWidth()/2 && x <= o.getX() + o.getWidth()/2 && y >= o.getY() - o.getHeight()/2&& y <= o.getY() + o.getHeight()/2){
    		return true;
    	}
    	return false;
    }
    
    void newRandomColor(){
    	Random ran = new Random();
    	int[] ic = {ran.nextInt(255), ran.nextInt(255), ran.nextInt(255)};
    	int[] color = {(ic[0]+255)/2, (ic[1]+255)/2, (ic[2]+255)/2};
    	gameColor = color;
    }
    
    private void resetPaint() {
    	paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.create("Roboto Thin", 0));
        paint.setColor(Color.BLACK);
		
	}
}