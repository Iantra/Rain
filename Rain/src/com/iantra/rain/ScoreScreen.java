package com.iantra.rain;

import android.graphics.Paint;

import com.iantra.framework.Game;
import com.iantra.framework.Graphics;
import com.iantra.framework.Screen;

public class ScoreScreen extends Screen{
	Graphics g;
	Paint p;
	
	public ScoreScreen(Game game) {
		super(game);
		g = game.getGraphics();
		p = new Paint();
	}

	@Override
	public void update(float deltaTime) {
		
	}

	@Override
	public void paint(float deltaTime) {
		g.clearScreen(0xFEFEF1);
		drawScores();
	}
	
	public void drawScores(){
		p.setTypeface(Assets.roboto);
		p.setTextSize(Assets.screenWidth/14);
		p.setAntiAlias(true);
		p.setTextAlign(Paint.Align.CENTER);
		g.drawString("TODO: add a scoreboard", Assets.screenWidth/2, Assets.screenHeight/3, p);
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
		game.setScreen(new MenuScreen(game));		
	}

}
