package MovingObjects;

import com.main.Main;
import Screens.PlayScreen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


public class Chicken extends Sprite{
    private PlayScreen screen;
    private float PosX;
    private float PosY;
    private float ChickenPosx;
    private float counter;
    private float RightstateTimer;
    private float LeftstateTimer;
    private int stat;
    private static int cnt=0;
    private Animation RightChickenDraw;
    private Animation LeftChickenDraw;
    private String path;
    private boolean Right;
    private Music music;
    
    public Chicken(PlayScreen screen, float PosX , float PosY){
        this.screen = screen;
        this.PosX=PosX;
        this.PosY=PosY;
        this.RightstateTimer=0;
        this.LeftstateTimer=0;
        this.counter=0;
        this.Right=true;
        
        setBounds(0, 0, 120 / Main.PPM, 80 / Main.PPM);
        
        DefineAnimation();
        music = Main.manager.get("Audio//Hercules - sounds//Chicken.mp3", Music.class);
        cnt++;
    }
    
    public final void DefineAnimation(){
        path = (cnt%2==0)? "RedChicken":"BrownChicken";
        stat = (cnt%2==0)?250:230;
        Array<TextureRegion> Rightframe = new Array<TextureRegion>();
        for(int i = 0 ; i < 12;++i)
            Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\"+path+".png"),i*stat,0,stat,256));
        
        Array<TextureRegion> Leftframe = new Array<TextureRegion>();
        for(int i = 0 ; i < 12;++i)
            Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\"+path+".png"),i*stat,256,stat,256));
        
        RightChickenDraw=new Animation(0.09f,Rightframe);
        LeftChickenDraw=new Animation(0.09f,Leftframe);
    }
    
    
    public void update(float dt){
        if(counter==0)Right=true;
        else if(counter==600)Right=false;
        
        if(Right){
            ChickenPosx=(this.PosX+(2*this.counter));
            counter+=3f;
            setPosition(this.ChickenPosx /Main.PPM , PosY /Main.PPM); 
            RightstateTimer+=dt;  
            setRegion((TextureRegion) RightChickenDraw.getKeyFrame(RightstateTimer,true ));
            if(RightstateTimer>12){RightstateTimer=0;}
        }
        
        else if(!Right){
            ChickenPosx=(this.PosX+(2*this.counter));
            counter-=3f;
            setPosition(this.ChickenPosx /Main.PPM , PosY /Main.PPM); 
            LeftstateTimer+=dt;  
            setRegion((TextureRegion) LeftChickenDraw.getKeyFrame(LeftstateTimer,true ));
            if(LeftstateTimer>12){LeftstateTimer=0;}
        }
        if (screen.getPlayer().body.getPosition().x > getX()-0.5f && screen.getPlayer().body.getPosition().x < getX()+0.5f && !music.isPlaying()){
            music.play();
            music.setVolume(Main.vol);
        }
    }

}
