package MovingObjects;

import com.main.Main;
import Intro.ScoreBoard;
import MovingObjects.Hercules;
import Scenes.HUD;
import Scenes.HUD2;
import Screens.PlayScreen;
import Scenes.Transition;
import Sprites.Letter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


public class Camel extends Sprite{
    private float PosX;
    private float PosY;
    private float CamelPosx;
    private float counter;
    private float stateTimer;
    private float HerculesPosX;
    private float HerculesPosY;
    public boolean isOn,isOk, once, losed;
    private PlayScreen screen;
    private Hercules player;
    private Main game;
    private Animation CamelDraw;
    private Music music;
    
    public Camel(PlayScreen screen){
        this.screen = screen;
        this.player=screen.getPlayer();
        this.game = screen.game;
        this.PosX=70800f;
        this.PosY=140f;
        this.stateTimer=0;
        this.counter=0;
        this. CamelPosx=(this.PosX+(2*this.counter));;
        this.isOn=false;
        this.isOk=false;
        this.once=false;
        this.losed=false;
        this.HerculesPosX=0;
        this.HerculesPosY=0;
        
        setBounds(0, 0, 300 / Main.PPM, 320 / Main.PPM);
        DefineAnimation();
        music = Main.manager.get("Audio//Hercules - sounds//Camel.mp3", Music.class);
        
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
       this.HerculesPosX=player.body.getPosition().x;
       this.HerculesPosY=player.body.getPosition().y;
      
        if( (this.HerculesPosX >CamelPosx /Main.PPM ) && (this.HerculesPosY > (this.PosY+200) /Main.PPM ) ){
            isOn=true;}
        else if(this.HerculesPosY  < (this.PosY+200) /Main.PPM && !isOk){
            isOn=false;}
        
        
        if(!isOn){
            setPosition(this.PosX /Main.PPM , PosY /Main.PPM); 
        }
 
        else{
            /***************************/
            //Check For Victory Conditions
            if(!once){
                once=true;
                for(int i = 0 ; i < 8; ++i)
                if (Letter.GET[i]==false){
                    if(HUD2.NumberOfTrials==0)
                        screen.hud2.GameOver();
                    else 
                        screen.hud2.LooseTrial();
                    losed=true;
                    break;
                }
            }
            /***************************/
                if(!losed){
                CamelPosx=(this.PosX+(2*this.counter));
                counter+=1f;
                setPosition(this.CamelPosx /Main.PPM , PosY /Main.PPM); 
                player.setPosition((this.CamelPosx-20) /Main.PPM , (PosY+180) /Main.PPM); isOk=true;
                screen.stopHercAction=true;
                if(CamelPosx >= PosX+1000){ // TRAVELING TO LEVEL 3
                    screen.Game.stop();
                    player.danger.stop(); 
                    ScoreBoard.addNewScore(Main.username, HUD.score + (HUD2.score + HUD2.timer * 2));
                    game.setScreen(new Transition(screen, HUD2.score, HUD2.timer));
                    screen.dispose();
                }
                screen.Victory.play();
                screen.Victory.setLooping(true);
                screen.Victory.setVolume(game.vol);
                stateTimer+=Gdx.graphics.getDeltaTime();  
            }
        }
    
        setRegion((TextureRegion) CamelDraw.getKeyFrame(stateTimer,true ));
        if(stateTimer>5){stateTimer=0;}
    
        if(player.body.getPosition().x > getX()-0.5f && player.body.getPosition().x < getX()+0.5f && !music.isPlaying()){
            music.play();
            music.setVolume(Main.vol);
        }
    }

    
    
}
