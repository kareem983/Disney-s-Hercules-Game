package HealthAttacker;

import Scenes.HUD;
import MovingObjects.Hercules;
import com.main.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


public class Fireball extends Sprite{
    private float posX;
    private float posY;
    private float FirePosY;
    private Animation animation;
    private float stateTimer;
    private float counter;
    private Hercules hercule;
    
    public Fireball(float posX, float posY,Hercules hercule){
        this.posX=posX;
        this.posY=posY;
        this.stateTimer=0;
        this.counter=0;
        this.hercule=hercule;
        
        setBounds(0, 0, 137 / Main.PPM, 320 / Main.PPM);
        DefineAnimation();
        
    }
    
    private void DefineAnimation(){
     Array<TextureRegion> frame = new Array<TextureRegion>();
        frame.add(new TextureRegion(new Texture("Sprites\\Level 1\\HealthAttacker\\FireballCannon.png"),0,0,137,320));
        animation=new Animation(0.09f,frame);
        frame.clear();
    }
    
    public void update(){
        
    FirePosY=(this.posY+(2*this.counter));
    
    //3lshan lma yousal t7t tnzl tany
    if(FirePosY<-180){
       counter=0; 
    }
    
        if (hercule.body.getPosition().x > (this.posX-25)/Main.PPM && hercule.body.getPosition().x < (this.posX+100)/Main.PPM && hercule.body.getPosition().y>(this.FirePosY+20)/Main.PPM && hercule.body.getPosition().y<(this.FirePosY+250)/Main.PPM)
    {
      counter=0;
      HUD.FireDecrease=true;
    }
    else this.counter-=3;    
     
        setPosition(this.posX /Main.PPM , FirePosY /Main.PPM);   
        stateTimer+=Gdx.graphics.getDeltaTime();
        setRegion((TextureRegion) animation.getKeyFrame(stateTimer,true ));
        if(stateTimer>1)stateTimer=0;
        
    }
    
}
