package com.iantra.rain;

import android.graphics.Typeface;

import com.iantra.framework.Screen;
import com.iantra.framework.implementation.AndroidGame;

public class MainActivity extends AndroidGame {

	@Override
    public Screen getInitScreen() {
        Assets.roboto = Typeface.createFromAsset(getAssets(), "fonts/ROBOTO-THIN.TTF");
        return new LoadingScreen(this);
    }
	
	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}

}
