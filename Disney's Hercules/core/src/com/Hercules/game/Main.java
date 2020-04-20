package com.Hercules.game;

import Screens.PlayScreen;
import Screens.PlayScreen2;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {            // Class Game: Is A class Build By LibGDX that Contains The Main Game Loop
    
    public static final int WIDTH = 2000;    // Window Screen Width             2000
    public static final int HEIGHT = 800;   // Window Screen Height            800
    public static final float PPM = 800;       // Pixels Per Meter   ---> And Responsiple For The Game Loop Speed
    public static final short GROUND_BIT = 1;
    public static final short HERCULES_BIT = 2;
    public static final short HERCULES_BORDER_BIT = 4;
    public static final short ENEMY_BIT = 8;
    public static final short BABYDRAGONS_SURFACE_BIT = 16;
    public static final short SKY_BORDER_BIT = 32;
    public static final short CHARACTERS_GROUND_BIT = 64;
    public static final short BIRDS_GROUND_BIT = 128;
    public static SpriteBatch batch;  // Container That Holds All The Sprites In The Whole Project
    
    @Override
    public void create() {
             batch = new SpriteBatch();
            
            setScreen(new PlayScreen(this));
            //setScreen(new PlayScreen2(this));
    }
    
    @Override
    public void render () {
            super.render();
    }
    
    @Override
    public void dispose() {
        super.dispose();
   //     manager.dispose();
        batch.dispose();
    }
    
}
