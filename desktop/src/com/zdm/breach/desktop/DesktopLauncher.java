package com.zdm.breach.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.zdm.breach.BreachGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Breach Game";
		config.useGL30 = false;
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new BreachGame(), config);
	}
}
