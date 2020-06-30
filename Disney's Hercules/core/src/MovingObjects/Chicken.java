package MovingObjects;

import MovingObjects.Hercules;
import Scenes.Transition2;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
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
        Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickRight1.png"),0,0,250,249));
        Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickRight2.png"),0,0,250,249));
        Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickRight3.png"),0,0,250,249));
        Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickRight4.png"),0,0,250,249));
        Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickRight5.png"),0,0,250,250));
        Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickRight6.png"),0,0,250,250));
        Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickRight7.png"),0,0,250,250));
        Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickRight8.png"),0,0,250,250));
        Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickRight9.png"),0,0,250,249));
        Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickRight10.png"),0,0,250,249));
        Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickRight11.png"),0,0,250,249));
        Rightframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickRight12.png"),0,0,250,249));
     
        
        Array<TextureRegion> Leftframe = new Array<TextureRegion>();
        Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickLeft1.png"),0,0,250,249));
        Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickLeft2.png"),0,0,250,249));
        Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickLeft3.png"),0,0,250,249));
        Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickLeft4.png"),0,0,250,249));
        Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickLeft5.png"),0,0,250,250));
        Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickLeft6.png"),0,0,250,250));
        Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickLeft7.png"),0,0,250,250));
        Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickLeft8.png"),0,0,250,250));
        Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickLeft9.png"),0,0,250,249));
        Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickLeft10.png"),0,0,250,249));
        Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickLeft11.png"),0,0,250,249));
        Leftframe.add(new TextureRegion(new Texture("Sprites\\Level 2\\Chicken\\chickLeft12.png"),0,0,250,249));
        
        RightChickenDraw=new Animation(0.09f,Rightframe);
        LeftChickenDraw=new Animation(0.09f,Leftframe);
     
        Rightframe.clear();
        Leftframe.clear();
    }
    
    
    public void update(){
        if(counter==0)Right=true;
        else if(counter==498)Right=false;
        
        
        
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
