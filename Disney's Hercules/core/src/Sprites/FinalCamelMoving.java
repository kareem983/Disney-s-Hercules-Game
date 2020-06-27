package Sprites;

import MovingObjects.Hercules;
import Screens.ScoreScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


public class FinalCamelMoving extends Sprite{
    private float PosX;
    private float PosY;
    private float CamelPosx;
    private float counter;
    private float stateTimer;
    private float HerculesPosX;
    private float HerculesPosY;
    private Hercules player;
    private Main game;
    private Animation CamelDraw;
    private boolean isOn,isOk;
    

    public FinalCamelMoving(Hercules player,Main game){
        this.player=player;
        this.game=game;
        this.PosX=70800f;
        this.PosY=140f;
        this.stateTimer=0;
        this.counter=0;
        this. CamelPosx=(this.PosX+(2*this.counter));;
        this.isOn=false;
        this.isOk=false;
        this.HerculesPosX=0;
        this.HerculesPosY=0;
        
        setBounds(0, 0, 107 / Main.PPM, 320 / Main.PPM);
        DefineAnimation();
        
    }
    
    public void DefineAnimation(){
     Array<TextureRegion> frame = new Array<TextureRegion>();
        frame.add(new TextureRegion(new Texture("Sprites\\Level 2\\Camel\\camel1.png"),0,0,205,194));
        frame.add(new TextureRegion(new Texture("Sprites\\Level 2\\Camel\\camel2.png"),0,0,217,211));
        frame.add(new TextureRegion(new Texture("Sprites\\Level 2\\Camel\\camel3.png"),0,0,209,190));
        frame.add(new TextureRegion(new Texture("Sprites\\Level 2\\Camel\\camel4.png"),0,0,209,198));
        frame.add(new TextureRegion(new Texture("Sprites\\Level 2\\Camel\\camel5.png"),0,0,210,194));
        
        CamelDraw=new Animation(0.5f,frame);
        frame.clear();
    }
    
    
    public void update(){
        //hercules positions
       this.HerculesPosX=player.b2body.getPosition().x;
       this.HerculesPosY=player.b2body.getPosition().y;
      
        if( (this.HerculesPosX >CamelPosx /Main.PPM ) && (this.HerculesPosY > (this.PosY+200) /Main.PPM ) ){
            isOn=true;}
        else if(this.HerculesPosY  < (this.PosY+200) /Main.PPM && !isOk){
            isOn=false;}
        
        
        if(!isOn){
            setPosition(this.PosX /Main.PPM , PosY /Main.PPM); 
        }
 
        else{
            CamelPosx=(this.PosX+(2*this.counter));
            counter+=1f;
            setPosition(this.CamelPosx /Main.PPM , PosY /Main.PPM); 
            player.setPosition((this.CamelPosx-20) /Main.PPM , (PosY+180) /Main.PPM); isOk=true;
            if(CamelPosx >= PosX+1000){
            //counter=0;
            game.setScreen(new ScoreScreen(game));
            }
             
            stateTimer+=Gdx.graphics.getDeltaTime();  
        }
    
        setRegion((TextureRegion) CamelDraw.getKeyFrame(stateTimer,true ));
        if(stateTimer>5){stateTimer=0;}
    
    }

    
    
}
