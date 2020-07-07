
package HealthAttacker;

import Screens.PlayScreen;
import MovingObjects.Hercules;
import Scenes.HUD;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class FeatherSack extends Sprite {
    private Animation animation ;
    private float stateimer ;
    protected PlayScreen screen;
    protected float x , y, xx, yy, xdomin, xcopy ; private boolean x_ ,y_;
    public boolean move ,right;
    boolean feather_is_collision , finish ;
    public static int Num_of_feather_Destroyed = 0;
    public static boolean child=false;
    private int fristcollion ;
    public int order ;
    public Sprite Rope;
    private  Music m;
    
    public FeatherSack(float posx  , float posy , PlayScreen screen){
        this.screen  = screen;
        setRegion(new Texture("Sprites\\Level 1\\HealthAttacker\\Feather_Sack2.png"));
        setPosition(posx, posy);
        stateimer = order = 0 ;
        x =95/Main.PPM; y = 155/Main.PPM;    x_ = true ;  y_ = true;
        setBounds(posx, posy,x, y);
        feather_is_collision =false ; finish=  false ;
                
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HealthAttacker\\f1.png") , 0 , 0 , 67 , 56  ));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HealthAttacker\\f2.png") , 0 , 0 , 67 , 56  ));
        animation  =new Animation(0.3f ,frames);
        fristcollion =0;
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
        //=========================================\\
        if(!child){
            if (featherCollsoin(screen.getPlayer()) == 1) {
                screen.creator.getFeathers().removeValue(this, true);
                Num_of_feather_Destroyed++;
                HUD.score+=10;
            } else {
                if (featherCollsoin(screen.getPlayer()) == 2) {
                    HUD.featherHit();
                } 
            }
        }
     //=========================================\\
        }
     public int featherCollsoin(Hercules player ){
        Rectangle feather_rec = this.getBoundingRectangle();
        Rectangle palyer_rec = player.getBoundingRectangle();
        
        if(feather_rec.overlaps(palyer_rec) && player.timeSword2 >0){
            feather_is_collision =true ;
            fristcollion++ ;
               if(fristcollion ==1 ){
            
            
         m = Main.manager.get("Audio//Hercules - sounds//featherFinish.wav",Music.class);
                m.setVolume(Main.vol); 
                m.play();
    }
        }
        if(feather_is_collision == true ){
          //  x = 130 ; y = 190 ; 
            setRegion((TextureRegion)animation.getKeyFrame(stateimer, true));
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
   
    
    public void Update(){}
}
