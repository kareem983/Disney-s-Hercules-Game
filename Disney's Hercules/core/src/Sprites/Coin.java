package Sprites;

import MovingObjects.Hercules;
import Scenes.HUD;
import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Coin extends Sprite{
    protected int startX;
    protected int startY;
    protected int width;
    protected int height;
    protected int posX;
    protected int posY;
    protected Animation CoinDraw;
    protected float stateTimer;
    public Hercules hercule;
    protected HUD hud; 
    protected boolean isfound; 
    protected Music music;
    
     public Coin(PlayScreen screen ,int startX,int startY,int width,int height,int posX,int posY){
         this.startX=startX;
         this.startY=startY;
         this.width=width;
         this.height=height;
         this.posX=posX;
         this.posY=posY;
         this.stateTimer=0;
         this.isfound=true;
         
         setBounds(0,0,45/Main.PPM,50/Main.PPM);
         DefineAnimation();
     }
     
     public abstract void update();
     
     public abstract void DefineAnimation();
     
}
   