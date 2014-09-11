package com.iantra.rain;


import java.util.List;

import android.graphics.Typeface;

import com.iantra.framework.Game;
import com.iantra.framework.Graphics;
import com.iantra.framework.Screen;
import com.iantra.framework.Graphics.ImageFormat;
import com.iantra.framework.Input.TouchEvent;


public class LoadingScreen extends Screen {
	Graphics g;
	
    public LoadingScreen(Game game) {
        super(game);
        g = game.getGraphics();
    }


    @Override
    public void update(float deltaTime) {
    	Graphics g = game.getGraphics();
        Assets.dot = g.newImage("dot.png", ImageFormat.RGB565);
        Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
        Assets.select = g.newImage("select.png", ImageFormat.RGB565);
        Assets.screenWidth = g.getWidth();
        Assets.screenHeight = g.getHeight();
        game.setScreen(new MenuScreen(game));

    }
    
    

    @Override
    public void paint(float deltaTime) {
    	g.clearScreen(0xFEFEF1);
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
