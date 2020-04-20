
package HealthAttacker;

import Screens.PlayScreen;
import MovingObjects.Hercules;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class FeatherSack extends Sprite {
    private float posx , posy , Rotation ;
    private Animation AnimationfeatherDestroyed ;
    private float stateimer ;
    private World world ;
    private boolean RotaionLeft , RotaionRight ;
    private float x , y ; private boolean x_ ,y_;
    boolean feather_is_collision , finish ;
    public static int Num_of_feather_Destroyed = 0;
    
    public FeatherSack(float posx  , float posy ,World world , PlayScreen screen){
        this.posx =posx ;
        this.posy =posy ;
        this.world =world;
        setRegion(new Texture("Sprites\\Feather_Sack2.png"));
        setPosition(posx, posy);
        stateimer = 0 ;
      //  setRotation(20f);
        Rotation =1; 
        RotaionRight =true ; RotaionLeft =false;  x =95/Main.PPM; y = 155/Main.PPM;    x_ = true ;  y_ = true;
                setBounds(posx, posy,x, y);
                feather_is_collision =false ; finish=  false ;
                
                       Array<TextureRegion> frames = new Array<TextureRegion>();
                   frames.add(new TextureRegion(new Texture("Sprites\\f1.png") , 0 , 0 , 67 , 56  ));
                   frames.add(new TextureRegion(new Texture("Sprites\\F2.png") , 0 , 0 , 67 , 56  ));
                   AnimationfeatherDestroyed  =new Animation(0.3f ,frames);
                   
                

    }
    
    public void update(float dt){
       
        if(x_ ==true ){
            if(x<110/Main.PPM){
            x += 0.2/Main.PPM  ;
            }else x_=false ;
        }
        else {
              x -= 0.2/Main.PPM  ;

           if( x < 95/Main.PPM ) x_ =true ;
        }
        
        if(y_ ==true ){
            if(y<170/Main.PPM){
            y += 0.2/Main.PPM  ;
            }else y_=false ;
        }
        else {
              y -= 0.2/Main.PPM  ;

           if( y < 155/Main.PPM ) y_ =true ;
        }
        
       
        setSize(x, y);
        }
     public int featherCollsoin(Hercules player ){
        Rectangle feather_rec = this.getBoundingRectangle();
        Rectangle palyer_rec = player.getBoundingRectangle();
        
        if(feather_rec.overlaps(palyer_rec) && player.timeSword2 >0){
            feather_is_collision =true ;
        }
        if(feather_is_collision == true ){
          //  x = 130 ; y = 190 ; 
            setRegion((TextureRegion)AnimationfeatherDestroyed.getKeyFrame(stateimer, true));
           stateimer += Gdx.graphics.getDeltaTime();
        }
        if(feather_rec.overlaps(palyer_rec) && player.timeSword2 <=0 && feather_is_collision ==false){
                        return 2 ;

        }
       else if( stateimer>= 2*0.3 ){
            return 1 ;
        }else{
            return 0 ;
        }
        
        
    }
   /* public int featherCollsoin(Hercules player ){
        Rectangle feather_rec = this.getBoundingRectangle();
        Rectangle palyer_rec = player.getBoundingRectangle();
        
        if(feather_rec.overlaps(palyer_rec) && player.timePush >0.5){
            feather_is_collision =true ;
        }
        if(feather_is_collision == true ){
          //  x = 130 ; y = 190 ; 
            setRegion((TextureRegion)AnimationfeatherDestroyed.getKeyFrame(stateimer, true));
           stateimer += Gdx.graphics.getDeltaTime();
        }
        if(feather_rec.overlaps(palyer_rec) && player.timePush <=0 && feather_is_collision ==false){
                        return 2 ;

        }
       else if( stateimer>= 2*0.3 ){
            return 1 ;
        }else{
            return 0 ;
        }
        
        
    }*/
    
}
