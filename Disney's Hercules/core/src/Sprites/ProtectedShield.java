package Sprites;

import Scenes.HUD;
import MovingObjects.Hercules;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

public class ProtectedShield extends Sprite{
  
  private float posX;
  private float posY;
  private Animation ShieldDraw;
  private int stateTimer;
  private Hercules hercule;
  private static HUD hud;
  private static float timer;
  private static float state;
  private static int shieldTimer;
  private boolean isFound;
  private boolean isplayed;
  private Music music;
  
  public ProtectedShield(Hercules hercule,HUD hud, float posX, float posY){
     this.posX=posX; 
     this.posY=posY;
     this.hercule=hercule;
     this.hud=hud;
     this.stateTimer=0;
     this.timer=0;
     this.shieldTimer=14;
     this.isFound=true;
     this.isplayed=true;
     
      setBounds(0,0,100/Main.PPM,104/Main.PPM);
      DefineAnimation();
  }


  private void DefineAnimation(){
      Array<TextureRegion> frame=new Array<TextureRegion>();
     
      frame.add(new TextureRegion(new Texture("Sprites\\Level 1\\HealthAttacker\\Shield.png"),0,0,100,104));
       
      ShieldDraw=new Animation(0.09f,frame);
      frame.clear();
      
  }

  public void update(float dt){
    //Timer 15 sec
    if(!this.hud.DecreasePeriod){
        this.timer += dt;
        if(this.timer>15){
            this.timer=0;
            this.hud.DecreasePeriod=true;
        }
    }
    
    if (this.isplayed && hercule.body.getPosition().x > (this.posX-950)/Main.PPM && hercule.body.getPosition().x < (this.posX+120)/Main.PPM )
    {
           music= Main.manager.get("Audio//Hercules - Voices//Hercules//A Gift from the gods.wav", Music.class);
           music.setVolume(Main.vol);
           music.play();
           this.isplayed=false;
    }
    
    if (hercule.body.getPosition().x > (this.posX-25)/Main.PPM && hercule.body.getPosition().x < (this.posX+120)/Main.PPM && hercule.body.getPosition().y>(this.posY-60)/Main.PPM && hercule.body.getPosition().y<(this.posY+80)/Main.PPM)
    {
        //to ensure that Sheild is found or not
       if(this.isFound){
            setBounds(0,0,-50,-50);
            this.hud.DecreasePeriod=false;
            
            //Shield sounds
            music = Main.manager.get("Audio//Hercules - Voices//Hercules//Helmet.wav", Music.class);
            music.setVolume(Main.vol);
            music.play();
       }
       this.isFound=false;
    }
    
      setPosition(this.posX/Main.PPM,this.posY/Main.PPM);
      stateTimer+=Gdx.graphics.getDeltaTime();
      setRegion((TextureRegion) ShieldDraw.getKeyFrame(stateTimer,true ));
      if(stateTimer>1)stateTimer=0;   
  
  }

    public static void ShowShieldTimer(Label label, Label timeCounter, float dt) {
        if(!hud.DecreasePeriod){
            state += dt;
            label.setText("SHIELD TIMER");
            if (state > 1){
                state = 0;
                shieldTimer-=1;
                timeCounter.setText(String.valueOf(shieldTimer));
            }
        }
        else 
            label.setText("SWORD TIMER");
    }

}