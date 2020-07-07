package MovingObjects;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


public class Chicken extends Sprite{
    private float PosX;
    private float PosY;
    private float ChickenPosx;
    private float counter;
    private float RightstateTimer;
    private float LeftstateTimer;
    private Animation RightChickenDraw;
    private Animation LeftChickenDraw;
    private boolean Right;
    
    public Chicken(float PosX , float PosY){
      
        this.PosX=PosX;
        this.PosY=PosY;
        this.RightstateTimer=0;
        this.LeftstateTimer=0;
        this.counter=0;
        this.Right=true;
        
        setBounds(0, 0, 120 / Main.PPM, 80 / Main.PPM);
        
        DefineAnimation();
        
    }
    
    public void DefineAnimation(){
        Array<TextureRegion> Rightframe = new Array<TextureRegion>();
        for(int i = 1 ; i <= 12;++i)
            Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickRight"+i+".png"),0,0,250,250));
        
        Array<TextureRegion> Leftframe = new Array<TextureRegion>();
        for(int i = 1 ; i <= 12;++i)
            Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickLeft"+i+".png"),0,0,250,250));
        
        RightChickenDraw=new Animation(0.09f,Rightframe);
        LeftChickenDraw=new Animation(0.09f,Leftframe);
    }
    
    
    public void update(){
        if(counter==0)Right=true;
        else if(counter==600)Right=false;
        
        if(Right){
            ChickenPosx=(this.PosX+(2*this.counter));
            counter+=3f;
            setPosition(this.ChickenPosx /Main.PPM , PosY /Main.PPM); 
            RightstateTimer+=Gdx.graphics.getDeltaTime();  
            setRegion((TextureRegion) RightChickenDraw.getKeyFrame(RightstateTimer,true ));
            if(RightstateTimer>12){RightstateTimer=0;}
        }
        
        else if(!Right){
            ChickenPosx=(this.PosX+(2*this.counter));
            counter-=3f;
            setPosition(this.ChickenPosx /Main.PPM , PosY /Main.PPM); 
            LeftstateTimer+=Gdx.graphics.getDeltaTime();  
            setRegion((TextureRegion) LeftChickenDraw.getKeyFrame(LeftstateTimer,true ));
            if(LeftstateTimer>12){LeftstateTimer=0;}
        }
        
    }

}
