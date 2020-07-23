package HealthAttacker;

import Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.main.Main;



public class CyclopsDie extends Sprite{
    private PlayScreen screen;
    private float posX, posY, DiestateTimer;
    private Animation DieCyclopsDraw;
    public static boolean FinalDie;
    private int cnt,aa;
    
    public CyclopsDie(PlayScreen screen){
        this.screen=screen;
        this.posX=Cyclops.CyclopsPosx;
        this.posY=40f;
        DiestateTimer=0;
        FinalDie=false;
        cnt=0;aa=0;
        
        setBounds(0, 0, 250 / Main.PPM, 300 / Main.PPM);
        DefineAnimation();
        
    }

    private void DefineAnimation() {

       Array<TextureRegion> Dieframe = new Array<TextureRegion>();
        Dieframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\die1.png"),0,0,146,181));
        Dieframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\die2.png"),0,0,170,147));
        Dieframe.add(new TextureRegion(new Texture("Sprites\\Level 3\\Cyclops\\die3.png"),0,0,208,113));
     
        DieCyclopsDraw=new Animation(0.28f,Dieframe);
        Dieframe.clear();

    }
    
     public void update(){
         cnt++;aa++;
         if(aa<=50){
         setPosition(Cyclops.CyclopsPosx / Main.PPM, this.posY / Main.PPM);
           DiestateTimer+=Gdx.graphics.getDeltaTime();
           setRegion((TextureRegion) DieCyclopsDraw.getKeyFrame(DiestateTimer,true ));
           if(DiestateTimer>3){DiestateTimer=0;}
         }
           if(cnt>150) {FinalDie=true; Cyclops.CyclopsPosx=8000;}
     }

    
}
