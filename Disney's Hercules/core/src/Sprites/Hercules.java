



package Sprites;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Hercules extends Sprite {
        
    public World world;
    public Body b2body;
    public boolean pickedlightsword=false,pickedfireballsword=false;
    
    private float HerculesInitPosX = 800f;
    private float HerculesInitPosY = 180f;
    public float HerculesMaxSpeed = 1.5f;
    public float HerculesMaxSpeedHigh = 0.3f;
    
 public enum State { FALLING, JUMPING, STANDING, RUNNING, pushing_hand ,pushing_sword , Drink , die };
    public State currentState;
    public State previousState;
    private Animation HerculesRun; 
    private Animation HerculesStand; 
    private Animation HerculesJump;
    public Animation HerculesPush;
    public Animation HerculesSword;
    public Animation HerculesDrink;
    public Animation HerculesDie;
    private float stateTimer;
    private boolean runningRight;
        
public  BodyDef bdef ;

   public  boolean  hercules_push = false ;
   private float timePush =0 ;
   public  boolean  hercules_sword = false ;
   private float timeSword =0 ;
   public  boolean  hercules_Drink = false ;
   private float timeDrink =0 ;
   public  boolean  hercules_Die = false ;
   private float timeDie =0 ;

   public Hercules(World world , PlayScreen screen){

    this.world =world ;
    defineHercules();
 
    currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;
        setBounds(0, 0, 50*3/ Main.PPM , 75*3/ Main.PPM);
         defineAnimation(screen);

    
}

    public boolean isRunningRight() {
        return runningRight;
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x  - getWidth()/2, b2body.getPosition().y - getHeight()/2 + 50/ Main.PPM);
        setRegion(getFrame(dt)); 
    }
    
       // this fn return which animation that will draw now 
    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;

        //depending on the state, get corresponding animation keyFrame.
        switch(currentState){
           case pushing_hand:
                region =  (TextureRegion) HerculesPush.getKeyFrame(stateTimer );
                break;
           case pushing_sword:
                region =  (TextureRegion) HerculesSword.getKeyFrame(stateTimer );
                break;
           case Drink:
                region =  (TextureRegion) HerculesDrink.getKeyFrame(stateTimer );
                break;
           case die:
                region =  (TextureRegion) HerculesDie.getKeyFrame(stateTimer );
                break;
          
            case JUMPING:
                region =   (TextureRegion) HerculesJump.getKeyFrame(stateTimer , true);;
                break;
            case RUNNING:
                region =  (TextureRegion) HerculesRun.getKeyFrame(stateTimer , true);
                break;
            case FALLING:
            case STANDING:
            default:
              //  region = HerculesStand;
            region =  (TextureRegion) HerculesStand.getKeyFrame(stateTimer , true);

                break;
        }
          //if Hercules is running left and the texture isnt facing left... flip it.
        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }

        //if Hercules is running right and the texture isnt facing right... flip it.
       else  if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }
        //if the current state is the same as the previous state increase the state timer.
        //otherwise the state has changed and we need to reset timer.
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        //update previous state
        previousState = currentState;
        
        //return our final adjusted frame
        return region;
    }
    
       // this fn return the Hercules state that doing now 
    public State getState(){
        //Test to Box2D for velocity on the X and Y-Axis
       if(pushing_hand()) return State.pushing_hand;   
       
       else if(pushing_sword()) return State.pushing_sword;
       
       else if(Hercules_Drink()) return State.Drink;
       
       else if(Hercules_Die()) return State.die; 
       
      //if Hercules is going positive in Y-Axis he is jumping... or if he just jumped and is falling remain in jump state
        else  if(b2body.getLinearVelocity().y > 0  || ( b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
         return State.JUMPING;  
        //if negative in Y-Axis mario is falling
        else if(b2body.getLinearVelocity().y < 0)
            return State.FALLING;
        //if mario is positive or negative in the X axis he is running
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        //if none of these return then he must be standing
        else
            return State.STANDING;
        
      }
    
       // this fn make the circle Which we move it and take his postion and give it to Hercules sprite  
    private void defineHercules() {
             bdef = new BodyDef();
             bdef.position.set(HerculesInitPosX / Main.PPM,HerculesInitPosY / Main.PPM);
             bdef.type = BodyDef.BodyType.DynamicBody ;
             b2body =world.createBody(bdef) ;
             
             FixtureDef fdef = new FixtureDef();
             CircleShape shape = new CircleShape();
             shape.setRadius(30 / Main.PPM);
             fdef.shape = shape ;
             b2body.createFixture(fdef) ;
    }
    
   private void  defineAnimation(PlayScreen screen){
       Array<TextureRegion> frames = new Array<TextureRegion>();

        //get run animation frames and add them to HerculesRun Animation
        for(int i = 1; i < 8; i++)
                   frames.add(new TextureRegion(screen.getAtlas_Run().findRegion("run__"), i * 50, 0, 47, 75));

        HerculesRun = new Animation(0.1f, frames);

        frames.clear();
       
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_jump\\jump1.png"),  0, 0, 50, 96));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_jump\\jump2.png"),  0, 0, 50, 96));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_jump\\jump3.png"),  0, 0, 45, 96));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_jump\\jump4.png"),  0, 0, 45, 96));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_jump\\jump5.png"),  0, 0, 50, 96));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_jump\\jump6.png"),  0, 0, 50, 96));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_jump\\jump7.png"),  0, 0, 50, 96));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_jump\\jump8.png"),  0, 0, 50, 96));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_jump\\jump9.png"),  0, 0, 50, 96));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_jump\\jump10.png"),  0, 0, 50, 96));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_jump\\jump11.png"),  0, 0, 50, 96));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_jump\\jump12.png"),  0, 0, 50, 96));
                   
        HerculesJump = new Animation(0.04f, frames);
frames.clear();

                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_push\\push1.png"),  0, 0, 50, 78));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_push\\push2.png"),  0, 0, 50, 78));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_push\\push3.png"),  0, 0, 50, 78));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_push\\push4.png"),  0, 0, 50, 78));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_push\\push5.png"),  0, 0, 50, 78));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_push\\push6.png"),  0, 0, 50, 78));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_push\\push7.png"),  0, 0, 50, 78));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_push\\push8.png"),  0, 0, 50, 78));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_push\\push9.png"),  0, 0, 50, 78));
                   frames.add(new TextureRegion(new Texture("Sprites\\Hercules_push\\push10.png"),  0, 0, 50, 78));
    
        HerculesPush = new Animation(0.1f, frames);
        HerculesPush.setPlayMode(Animation.PlayMode.NORMAL );
frames.clear();
     
                   frames.add(new TextureRegion(new Texture("Sprites\\Herclues_sword\\sword1.png"),  0, 0, 50, 80));
                   frames.add(new TextureRegion(new Texture("Sprites\\Herclues_sword\\sword2.png"),  0, 0, 50, 80));
                   frames.add(new TextureRegion(new Texture("Sprites\\Herclues_sword\\sword3.png"),  0, 0, 59, 80));
                   frames.add(new TextureRegion(new Texture("Sprites\\Herclues_sword\\sword4.png"),  0, 0, 50, 80));
                   frames.add(new TextureRegion(new Texture("Sprites\\Herclues_sword\\sword5.png"),  0, 0, 50, 80));
                   frames.add(new TextureRegion(new Texture("Sprites\\Herclues_sword\\sword6.png"),  0, 0, 50, 80));
                   frames.add(new TextureRegion(new Texture("Sprites\\Herclues_sword\\sword7.png"),  0, 0, 50, 80));
                   frames.add(new TextureRegion(new Texture("Sprites\\Herclues_sword\\sword8.png"),  0, 0, 60, 80));
                   frames.add(new TextureRegion(new Texture("Sprites\\Herclues_sword\\sword9.png"),  0, 0, 60, 80));

        HerculesSword = new Animation(0.1f, frames);
        HerculesSword.setPlayMode(Animation.PlayMode.NORMAL );
frames.clear();
 
                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_drink\\1.png"),  0, 0, 50, 75));
                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_drink\\2.png"),  0, 0, 50, 75));
                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_drink\\3.png"),  0, 0, 45, 75));
                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_drink\\4.png"),  0, 0, 45, 75));
                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_drink\\5.png"),  0, 0, 45, 75));
        
        HerculesDrink = new Animation(0.1f, frames);
        HerculesDrink.setPlayMode(Animation.PlayMode.NORMAL );
frames.clear();

                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_die\\1.png"),  0, 0, 45, 75));
                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_die\\2.png"),  0, 0, 50, 75));
                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_die\\3.png"),  0, 0, 50, 75));
                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_die\\4.png"),  0, 0, 35, 75));
                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_die\\5.png"),  0, 0, 35, 75));
                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_die\\6.png"),  0, 0, 50, 75));
                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_die\\7.png"),  0, 0, 50, 75));
        HerculesDie = new Animation(0.15f, frames);
        HerculesDie.setPlayMode(Animation.PlayMode.NORMAL );
frames.clear();

                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_stand\\1.png"),  0, 0, 45, 75));
                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_stand\\2.png"),  0, 0, 45, 75));
                  frames.add(new TextureRegion(new Texture("Sprites\\Hercules_stand\\3.png"),  0, 0, 45, 75));
        HerculesStand = new Animation(0.15f, frames);
        HerculesStand.setPlayMode(Animation.PlayMode.LOOP );
frames.clear();

      

   }
       // this fn return true when Hercures pushing by hand
   public boolean pushing_hand(){
       if  (hercules_push == true){
                             timePush+= Gdx.graphics.getDeltaTime() ;
          if(timePush<1) return true ;
          else{
             timePush =0 ;
             hercules_push = false ;
           return false ; 
         } }else {
           return false ;
       }
   }
   
   // this fn return true when Hercures pushing by sword 
   public boolean pushing_sword(){
       if  (hercules_sword == true){
                             timeSword+= Gdx.graphics.getDeltaTime() ;
           if(timeSword<1) return true ;
           else{
             timeSword =0 ;
             hercules_sword = false ;
           return false ; 
         } }else {
           return false ;
       }
   }

   // this fn return true when Hercures Drink 
   public boolean Hercules_Drink(){
       if  (hercules_Drink == true){
                             timeDrink+= Gdx.graphics.getDeltaTime() ;
           if(timeDrink<1) return true ;
           else{
             timeDrink =0 ;
             hercules_Drink = false ;
           return false ; 
         } }else {
           return false ;
       }
   }
   
      // this fn return true when Hercures Die
   public boolean Hercules_Die(){
       if  (hercules_Die == true){
                             timeDie+= Gdx.graphics.getDeltaTime() ;
           if(timeDie<1.5) return true ;
           else{
             timeDie =0 ;
             hercules_Die = false ;
           return false ; 
         } }else {
           return false ;
       }
   }
}