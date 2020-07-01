package com.Launcher.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Hercules.game.Main;

public class DesktopLauncher {
    public static void main (String[] arg) {
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "Disney's Hercules";
                config.width = 1366;
                config.height = 768;    
                config.x = -10;
                config.fullscreen = true;
            new LwjglApplication(new Main(), config);
    }
}