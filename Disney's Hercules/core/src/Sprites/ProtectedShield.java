package Sprites;

import Scenes.Hud;
import Screens.PlayScreen;
import Sprites.Hercules;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


public class ProtectedShield extends Sprite{
  
  private int posX;
  private int posY;  
  private Animation ShieldDraw;
  private int stateTimer;
  private Hercules hercule;
  private Hud hud;
  private int timer;
  private boolean isFound;

  public ProtectedShield(Hercules hercule,Hud hud){
     this.posX=10000;
     this.posY=300;
     this.hercule=hercule;
     this.hud=hud;
     this.stateTimer=0;
     this.timer=0;
     this.isFound=true;
     
      setBounds(0,0,100/Main.PPM,104/Main.PPM);
      DefineAnimation();
  }


  private void DefineAnimation(){
      Array<TextureRegion> frame=new Array<TextureRegion>();
     
      frame.add(new TextureRegion(new Texture("Sprites\\HealthAttacker\\Shield.png"),0,0,100,104));
       
      ShieldDraw=new Animation(0.09f,frame);
      frame.clear();
      
  }


  public void update(){
      
    //Timer 17 sec
    if(!this.hud.DecreasePeriod){
        this.timer+=1;
        if(this.timer>1000){
            this.timer=0;
            this.hud.DecreasePeriod=true;
        }
    }
      
    
    if (hercule.b2body.getPosition().x > (this.posX-25)/Main.PPM && hercule.b2body.getPosition().x < (this.posX+120)/Main.PPM && hercule.b2body.getPosition().y>(this.posY-60)/Main.PPM && hercule.b2body.getPosition().y<(this.posY+80)/Main.PPM)
    {
        //to ensure that Sheild is found or not
       if(this.isFound){
            setBounds(0,0,-50,-50);
            this.hud.DecreasePeriod=false;
       }
       this.isFound=false;
    }
    
      setPosition(this.posX/Main.PPM,this.posY/Main.PPM);
      stateTimer+=Gdx.graphics.getDeltaTime();
      setRegion((TextureRegion) ShieldDraw.getKeyFrame(stateTimer,true ));
      if(stateTimer>1)stateTimer=0;   
  
  }

}
