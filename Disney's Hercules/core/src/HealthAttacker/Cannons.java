package HealthAttacker;

import Scenes.HUD;
import Screens.PlayScreen;
import MovingObjects.Hercules;
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


public class Cannons extends Sprite{
    private int posX;
    private int posY;
    private int FirePosY;
    private Animation CannonDraw;
    private int stateTimer;
    private int counter;
    private Hercules hercule;
    private HUD hud;
    
    public Cannons(int posX, int posY,Hercules hercule,HUD hud){
        this.posX=posX;
        this.posY=posY;
        this.stateTimer=0;
        this.counter=0;
        this.hercule=hercule;
        this.hud=hud;
        
        setBounds(0, 0, 137 / Main.PPM, 320 / Main.PPM);
        DefineAnimation();
        
    }
    
    
    
    private void DefineAnimation(){
     Array<TextureRegion> frame = new Array<TextureRegion>();
        frame.add(new TextureRegion(new Texture("Sprites\\HealthAttacker\\FireballCannon.png"),0,0,137,320));
        CannonDraw=new Animation(0.09f,frame);
        frame.clear();
    
    }
    
    
    
    
    public void update(){
        
    FirePosY=(this.posY+(2*this.counter));
    
    //3lshan lma yousal t7t tnzl tany
    if(FirePosY<-180){
       counter=0; 
    }
    
        if (hercule.b2body.getPosition().x > (this.posX-25)/Main.PPM && hercule.b2body.getPosition().x < (this.posX+100)/Main.PPM && hercule.b2body.getPosition().y>(this.FirePosY+20)/Main.PPM && hercule.b2body.getPosition().y<(this.FirePosY+250)/Main.PPM)
    {
      counter=0;
      this.hud.FireDecrease=true;
    }
    else this.counter-=3;    
     
    
        setPosition(this.posX /Main.PPM , FirePosY /Main.PPM);   
        stateTimer+=Gdx.graphics.getDeltaTime();
        setRegion((TextureRegion) CannonDraw.getKeyFrame(stateTimer,true ));
        if(stateTimer>1)stateTimer=0;
        
    }
    
}
