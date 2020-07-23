package com.main;

import Scenes.IntroScenes;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {

    public static int x = 0, y = 0;
    public static float vol = 1.0f; // 0.5f
    public static int up =19, down =20, left =21, right =22, sword1 = 52, sword2 = 54, normalPunch =31, powerPunch = 50;
    public static String username = "user";
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
    public static final short WAGON_BIT = 256;
    
    public static SpriteBatch batch;
    public static AssetManager manager;
    

    @Override
    public void create() {
        batch = new SpriteBatch();
        addMusicAndSounds();

        setScreen(new IntroScenes(this));
        //setScreen(new StartMenu(this));
        //setScreen(new GameInstruction(this));
        //setScreen(new Level1(this));
        //setScreen(new Level2(this));
        //setScreen(new Level3(this));
        //setScreen(new Transition(new Level2(this), 800, 20));
        //setScreen(new Credit());
        //setScreen (new GameOver(this));
    }
        
   private void addMusicAndSounds(){
        manager = new AssetManager();
        manager.load("Audio//Hercules - sounds//Tall pillar Cracked.wav", Music.class);
        manager.load("Audio//Hercules - sounds//CrashPillar.mp3", Music.class);
        manager.load("Audio//Hercules - Voices//Phil//Excellenty.wav", Music.class);
        manager.load("Audio//Hercules - sounds//Game Over.mp3", Music.class);
        manager.load("Audio//Hercules - sounds//Game Over 2.mp3", Music.class);
        manager.load("Audio//Hercules - Voices//Hercules//Herculad.wav", Music.class);
        manager.load("Audio//Hercules - Voices//Hercules//HerculadToken.wav", Music.class);
        manager.load("Audio//Hercules - Voices//Hercules//A Gift from the gods.wav", Music.class);
        manager.load("Audio//Hercules - Voices//Hercules//Helmet.wav", Music.class);
        manager.load("Audio//Hercules - sounds//Water Sound.wav", Music.class);
        manager.load("Audio//Hercules - sounds//Coin.wav", Music.class);
        manager.load("Audio//Hercules - sounds//Hercules_Atacked.wav", Music.class);
        manager.load("Audio//Hercules - Voices//Hercules/Oh boy.wav", Music.class);
        manager.load("Audio//Hercules - sounds//Lightening Sword.wav", Music.class);
        manager.load("Audio//Hercules - sounds//Fireball Sword.wav", Music.class);
        manager.load("Audio//Hercules - Voices//Hercules//Sandal.wav", Music.class);
        manager.load("Audio//Hercules - Voices//Hercules//LighteningSword.wav", Music.class);
        manager.load("Audio//Hercules - Voices//Hercules//FireballSword.wav", Music.class);
        manager.load("Audio//Hercules - sounds//Nature Sound.wav", Music.class);    // NATURE
        manager.load("Audio//Hercules - Voices//Phil//Concentrate.wav", Music.class); // CONCENTRATE KID
        manager.load("Audio//Hercules - Voices//Phil//Get your sword.wav", Music.class); // GET YOUR SWORD
        manager.load("Audio//Hercules - Voices//Hercules//BabyDragon.wav", Music.class); //KILLING BABY DRAGONS
        manager.load("Audio//Hercules - Voices//Phil//Rule number 95.wav", Music.class); //STARTING GAME
        manager.load("Audio//Hercules - Voices//Phil//Rule number 96.wav", Music.class); // SHOOTING RANGE
        manager.load("Audio//Hercules - sounds//MainMenu.mp3", Music.class); // Main Menu Soundtrack

        manager.load("Audio//Hercules - sounds//Punch.wav", Music.class);
        manager.load("Audio//Hercules - sounds//sword.wav", Music.class);
        manager.load("Audio//Hercules - sounds//featherFinish.wav", Music.class);
        manager.load("Audio//Hercules - sounds//a3.wav", Music.class);
        manager.load("Audio//Hercules - sounds//a2.wav", Music.class);
        manager.load("Audio//Hercules - Voices//Hercules//Jumb2.wav", Music.class);
        
        manager.load("Audio//Hercules - Voices//Meg//Dont waste my time.wav" , Music.class);
        manager.load("Audio//Hercules - Voices//Meg//More Wrong Password.wav" , Music.class);
        manager.load("Audio//Hercules - Voices//Meg//Oh we got a winner.wav" , Music.class);
        manager.load("Audio//Hercules - Voices//Meg//So u have a password with you.wav" , Music.class);
        manager.load("Audio//Hercules - Voices//Meg//time to play vases.wav" , Music.class);
        manager.load("Audio//Hercules - Voices//Meg//Wrong password.wav" , Music.class);
        manager.load("Audio//Hercules - Voices//Meg//vase down.mp3" , Music.class);
        manager.load("Audio//Hercules - Voices//Meg//vase up.mp3" , Music.class);
        
        manager.load("Audio//Hercules - Voices//Name//H.mp3", Music.class);
        manager.load("Audio//Hercules - Voices//Name//E.mp3", Music.class);
        manager.load("Audio//Hercules - Voices//Name//R.mp3", Music.class);
        manager.load("Audio//Hercules - Voices//Name//C.mp3", Music.class);
        manager.load("Audio//Hercules - Voices//Name//U.mp3", Music.class);
        manager.load("Audio//Hercules - Voices//Name//L.mp3", Music.class);
        manager.load("Audio//Hercules - Voices//Name//S.mp3", Music.class);
        
        
        manager.load("Audio//Hercules - sounds//The End.mp3", Music.class); // Finished
        manager.load("Audio//Hercules - Voices//Cyclops//Count Down 10.mp3", Music.class);// Finished
        manager.load("Audio//Hercules - sounds//CrashEgg.mp3", Music.class); // Finished
        manager.load("Audio//Hercules - sounds//Chicken.mp3", Music.class); // Finished
        manager.load("Audio//Hercules - sounds//Camel.mp3", Music.class); // Finished
        manager.load("Audio//Hercules - sounds//Nature.mp3", Music.class); // Finished
        manager.load("Audio//Hercules - sounds//Red Zone.mp3", Music.class); // Finished
        manager.load("Audio//Hercules - sounds//Victory.mp3", Music.class); // Finished
        manager.load("Audio//Hercules - sounds//Vulture.mp3", Music.class); // Finished
        manager.load("Audio//Hercules - sounds//Wagon.mp3", Music.class); // Finished
        manager.load("Audio//Hercules - sounds//Wolf.mp3", Music.class); // Finished
        
        manager.load("Audio//Hercules - Voices//Cyclops//Level3 Sound.mp3", Music.class); // Finished
        manager.load("Audio//Hercules - Voices//Cyclops//Hercules Where are you.wav", Music.class); // Finished
        manager.load("Audio//Hercules - Voices//Cyclops//Hahaha.wav", Music.class); // Finished
        manager.load("Audio//Hercules - Voices//Cyclops//Run run magets.wav", Music.class); // Finished
        manager.load("Audio//Hercules - Voices//Cyclops//Hercules come out face me.wav", Music.class); // Finished
        
        manager.finishLoading();
    }
    
    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        manager.dispose();
        batch.dispose();
    }

}
