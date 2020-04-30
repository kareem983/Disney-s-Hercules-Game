package com.Hercules.game;

import Intro.GameInstruction;
import Intro.StartMenu;
import Scenes.IntroScenes;
import Screens.PlayScreen;
import Screens.PlayScreen2;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
    
    public static final int WIDTH = 2000;   
    public static final int HEIGHT = 800;
    public static final float PPM = 800f;      
    public static final short GROUND_BIT = 1;
    public static final short HERCULES_BIT = 2;
    public static final short HERCULES_BORDER_BIT = 4;
    public static final short ENEMY_BIT = 8;
    public static final short BABYDRAGONS_SURFACE_BIT = 16;
    public static final short SKY_BORDER_BIT = 32;
    public static final short CHARACTERS_GROUND_BIT = 64;
    public static final short BIRDS_GROUND_BIT = 128;
    public static final short HERCULES_SENSOR_BIT = 256;
    public static SpriteBatch batch; 
    public static AssetManager manager;
    
    @Override
    public void create() {
             batch = new SpriteBatch();
            manager = new AssetManager();
            manager.load("Audio//Hercules - sounds//Tall pillar Cracked.wav", Music.class);
            manager.load("Audio//Hercules - Voices//Phil//Excellenty.wav", Sound.class);
            manager.load("Audio//Hercules - sounds//Game Over.mp3", Music.class);
            manager.load("Audio//Hercules - Voices//Hercules//Herculad.wav" , Music.class);
            manager.load("Audio//Hercules - Voices//Hercules//HerculadToken.wav", Music.class);
            manager.load("Audio//Hercules - Voices//Hercules//A Gift from the gods.wav",Music.class);
            manager.load("Audio//Hercules - Voices//Hercules//Helmet.wav",Music.class);
            manager.load("Audio//Hercules - sounds//Water Sound.wav",Music.class);
            manager.load("Audio//Hercules - sounds//Coin.wav",Music.class);
            manager.load("Audio//Hercules - sounds//Hercules_Atacked.wav",Music.class);
            manager.load("Audio//Hercules - Voices//Hercules/Oh boy.wav",Music.class);
            manager.load("Audio//Hercules - sounds//Lightening Sword.wav",Music.class);
            manager.load("Audio//Hercules - sounds//Fireball Sword.wav",Music.class);
            manager.load("Audio//Hercules - Voices//Hercules//Sandal.wav", Sound.class);
            manager.load("Audio//Hercules - Voices//Hercules//LighteningSword.wav", Music.class);
            manager.load("Audio//Hercules - Voices//Hercules//FireballSword.wav", Music.class);
            manager.load("Audio//Hercules - sounds//Nature Sound.wav", Music.class);    // NATURE
            manager.load("Audio//Hercules - Voices//Phil//Concentrate.wav", Music.class); // CONCENTRATE KID
            manager.load("Audio//Hercules - Voices//Phil//Get your sword.wav", Music.class); // GET YOUR SWORD
            manager.load("Audio//Hercules - Voices//Hercules//BabyDragon.wav", Music.class); //KILLING BABY DRAGONS
            manager.load("Audio//Hercules - Voices//Phil//Rule number 95.wav", Sound.class); //STARTING GAME
            manager.load("Audio//Hercules - Voices//Phil//Rule number 96.wav", Sound.class); // SHOOTING RANGE
            manager.load("Audio//Hercules - sounds//IntroMainMenu.mp3", Music.class); // Main Menu Soundtrack
            manager.finishLoading();
            
            // TOGGLE SCREENS
            //setScreen(new IntroScenes(this));
            setScreen(new StartMenu(this));            
            //setScreen(new PlayScreen(this));
            //setScreen(new PlayScreen2(this));
    }
    
    @Override
    public void render () {
            super.render();
    }
    
    @Override
    public void dispose() {
        super.dispose();
        manager.dispose();
        batch.dispose();
    }
    
}
