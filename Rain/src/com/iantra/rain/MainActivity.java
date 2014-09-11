package com.iantra.rain;

import com.iantra.framework.Screen;
import com.iantra.framework.implementation.AndroidGame;

public class MainActivity extends AndroidGame {

	@Override
    public Screen getInitScreen() {
        return new LoadingScreen(this); 
    }

}
