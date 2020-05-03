package com.Launcher.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Hercules.game.Main;
import com.badlogic.gdx.Input;

public class DesktopLauncher {
    public static void main (String[] arg) {
            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "Disney's Hercules";
                config.width = 1366;
                config.height = 768;
                config.x = -10;
                config.fullscreen = false;
                System.out.println(Input.Keys.valueOf("ESCAPE"));
            new LwjglApplication(new Main(), config);
    }
}