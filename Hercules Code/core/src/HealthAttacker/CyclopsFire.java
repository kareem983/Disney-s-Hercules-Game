package HealthAttacker;

import MovingObjects.Hercules;
import Scenes.HUD3;
import Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.main.Main;

public class CyclopsFire extends Sprite{
    private PlayScreen screen;
    private Hercules player;
    private Main game;
    private float posX, posY, FirePosx, FireStateTimer, HerculesPosX;
    public static float Firecounter;
    private Animation FireDraw;
    private Music x;
    
    
    public CyclopsFire(PlayScreen screen){
        this.screen = screen;
        this.player=screen.getPlayer();
        this.game=screen.game;
        this.posX=Cyclops.CyclopsPosx;
        this.posY=100f;
        FirePosx=posX;
        
        FireStateTimer=this.Firecounter=0;
        x= this.game.manager.get("Audio//Hercules - Voices//Cyclops//Hahaha.wav", Music.class);
        
        setBounds(0, 0, 100 / Main.PPM, 120 / Main.PPM);
        DefineAnimation();
    }

    private void DefineAnimation() {
        
        Array<TextureRegion> Fireframe = new Array<TextureRegion>();
        Fireframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\fire1.png"),0,0,48,52));
        
        FireDraw=new Animation(0.18f,Fireframe);
        Fireframe.clear();

    }
    
    
     public void update(){
         FirePosx=(Cyclops.CyclopsPosx+(2*this.Firecounter));
         setPosition(this.FirePosx / Main.PPM, this.posY / Main.PPM);
         Firecounter-=10f;
        
         //collission hercules with fireball
         if((player.body.getPosition().x > (FirePosx-10) /Main.PPM && player.body.getPosition().x < (FirePosx+20) /Main.PPM && player.body.getPosition().y > (this.posY -150) / Main.PPM && player.body.getPosition().y < (this.posY + 120) / Main.PPM)){
             HUD3.FireDecrease=true;
             Firecounter=-9000f;
             x.setVolume(Main.vol);
             x.play();
         }
         
         //collission hercules with Cyclops
         if(Gdx.input.isKeyJustPressed(Main.sword1) && player.body.getPosition().x > (Cyclops.CyclopsPosx-40)/Main.PPM){
             HUD3.CyclopsDecrease=true;
             HUD3.score+=10;
         }
         if(player.body.getPosition().x > (Cyclops.CyclopsPosx+60)/Main.PPM){
              HUD3.FireDecrease=true;    
         }
         
       
       
           FireStateTimer+=Gdx.graphics.getDeltaTime();
           setRegion((TextureRegion) FireDraw.getKeyFrame(FireStateTimer,true ));
           if(FireStateTimer>1){FireStateTimer=0;}
     }

    
}
