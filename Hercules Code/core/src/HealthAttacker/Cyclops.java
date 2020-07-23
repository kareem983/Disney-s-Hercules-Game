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

public class Cyclops extends Sprite{
    private PlayScreen screen;
    private Hercules player;
    private Main game;
    private float posX, posY, Cyclopscounter, AttackstateTimer, BackstateTimer, PushstateTimer, HerculesPosX;
    public static float CyclopsPosx;
    private Animation AttackCyclopsDraw, BackCyclopsDraw, PushCyclopsDraw;
    private boolean Attack,pushMovement;
    private Music x;
    
    
    public Cyclops(PlayScreen screen){
        this.screen = screen;
        this.player=screen.getPlayer();
        this.game=screen.game;
        this.posX=4300f;
        this.posY=40f;
        AttackstateTimer=BackstateTimer=PushstateTimer=Cyclopscounter=0;
        Attack=true;
        pushMovement=false;
        this.CyclopsPosx=this.posX;
        x=this.game.manager.get("Audio//Hercules - Voices//Cyclops//Hercules come out face me.wav", Music.class);
        
        setBounds(0, 0, 350 / Main.PPM, 400 / Main.PPM);
        DefineAnimation();
        
    }
    
    
    public void DefineAnimation(){
      Array<TextureRegion> AttackWalkingframe = new Array<TextureRegion>();
        AttackWalkingframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\walking1.png"),0,0,128,195));
        AttackWalkingframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\walking2.png"),0,0,108,183));
        AttackWalkingframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\walking3.png"),0,0,142,179));
        AttackWalkingframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\walking4.png"),0,0,116,179));
        AttackWalkingframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\walking5.png"),0,0,124,187));
        AttackWalkingframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\walking6.png"),0,0,132,193));
        
      Array<TextureRegion> BackWalkingframe = new Array<TextureRegion>();
        BackWalkingframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\walking6.png"),0,0,132,193));
        BackWalkingframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\walking5.png"),0,0,124,187));
        BackWalkingframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\walking4.png"),0,0,116,179));
        BackWalkingframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\walking3.png"),0,0,142,179));
        BackWalkingframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\walking2.png"),0,0,108,183));
        BackWalkingframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\walking1.png"),0,0,128,195));
        
      Array<TextureRegion> Pushframe = new Array<TextureRegion>();
        Pushframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\push1.png"),0,0,164,217));
        Pushframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\push2.png"),0,0,136,219));
        Pushframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\push3.png"),0,0,142,211));
        Pushframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\push4.png"),0,0,194,201));
     
    
        
        AttackCyclopsDraw=new Animation(0.18f,AttackWalkingframe);
        BackCyclopsDraw=new Animation(0.18f,BackWalkingframe);
        PushCyclopsDraw=new Animation(0.18f,Pushframe);
        
        
        AttackWalkingframe.clear();
        BackWalkingframe.clear();
        Pushframe.clear();
        
      
    }

    
    
    
     public void update(){
       //hercules X position
       this.HerculesPosX=player.body.getPosition().x;
       
       this.CyclopsPosx=(this.posX+(2*this.Cyclopscounter));
       setPosition(this.CyclopsPosx / Main.PPM, this.posY / Main.PPM);
      
            
            if(Cyclopscounter==0 || Cyclopscounter==-200 || Cyclopscounter==-350){
                if(Cyclopscounter!=0){
                x.setVolume(Main.vol); 
                x.play();}
                pushMovement=true;
            }


            if(pushMovement){
                 PushstateTimer+=Gdx.graphics.getDeltaTime();
                 setRegion((TextureRegion) PushCyclopsDraw.getKeyFrame(PushstateTimer,true ));
                 if(PushstateTimer>4){
                     CyclopsFire.Firecounter=0;
                     PushstateTimer=0;
                     pushMovement=false; 
                     if(Attack)Cyclopscounter--;
                     else Cyclopscounter++;
                 }
            }
            else{
               CyclopsWalking();
             }
      
     }
     
     
     
     
     
     
     private void CyclopsWalking(){
        if(Attack){
           Cyclopscounter-=1f;
           if(Cyclopscounter<=-350)Attack=false;
           AttackstateTimer+=Gdx.graphics.getDeltaTime();
           setRegion((TextureRegion) AttackCyclopsDraw.getKeyFrame(AttackstateTimer,true ));
           if(AttackstateTimer>6){AttackstateTimer=0;}
           }

           else{ 
           Cyclopscounter+=1f;
           if(Cyclopscounter>=0)Attack=true;
           BackstateTimer+=Gdx.graphics.getDeltaTime();
           setRegion((TextureRegion) BackCyclopsDraw.getKeyFrame(BackstateTimer,true ));
           if(BackstateTimer>6){BackstateTimer=0;}
           }
     }


     
}
