package Sprites;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TallPiller extends Sprite {
   
    private PlayScreen screen;
    public  Body body;
    private float powerPunchTimer, X, Y;
    public boolean inPower;
    private int i, powerCnt;
    private Music pillarSound, pillarSound2, excellenty;
    
    public TallPiller(PlayScreen screen, float X , float Y) {
        this.screen = screen;
        this.X = X;
        this.Y= Y;
        inPower=false;
        powerPunchTimer=0f;
        i=powerCnt=0;
        
        setTexture(new Texture("Sprites\\Level 1\\Complement\\Tall Pillar.png"));
        setRegion(78*(i++), 0, 78, 247); // Original
        setBounds(0, 0, 220 / Main.PPM, 700 / Main.PPM);
        setPosition(X /Main.PPM, Y /Main.PPM);
        pillarSound = Main.manager.get("Audio//Hercules - sounds//Tall pillar Cracked.wav");
        pillarSound2 = Main.manager.get("Audio//Hercules - sounds//CrashPillar.mp3", Music.class);
        excellenty = Main.manager.get("Audio//Hercules - Voices//Phil//Excellenty.wav", Music.class);
        
        definePillar();
    }

    public void update(float dt) {
        powerPunchTimer += dt;
        
        if(getBoundingRectangle().overlaps(screen.getPlayer().getBoundingRectangle()) 
                && Gdx.input.isKeyJustPressed(screen.game.normalPunch) &&i<5){
                setRegion(78*(i++), 0, 78, 247);
                pillarSound.play();
                pillarSound.setVolume(Main.vol);
        }
            
        
        if(getBoundingRectangle().overlaps(screen.getPlayer().getBoundingRectangle()) 
                && Gdx.input.isKeyJustPressed(screen.game.powerPunch)&&!inPower){
                inPower=true;
        }
            if(inPower && powerPunchTimer>.2f&&!screen.getPlayer().pushing_hand()&&i<5){
                setRegion(78*(i++), 0, 78, 247);
                powerPunchTimer=0f;
                powerCnt++;
                pillarSound2.play();
                pillarSound2.setVolume(Main.vol);
                if(powerCnt==2)inPower=false;
            }
        
        if(i==5){
            body.setActive(false);i++;
            excellenty.play();
            excellenty.setVolume(Main.vol);
        }
    }
    
    public void definePillar(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(((X+110)/ Main.PPM) , Y / Main.PPM);
        bdef.type = BodyDef.BodyType.StaticBody ;
        body =screen.getWorld().createBody(bdef) ;

        FixtureDef fdef = new FixtureDef();
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(90/Main.PPM, 362*2  / Main.PPM);
        fdef.shape=polygon;
        body.createFixture(fdef);

    }

    public void resetData(){
        i=powerCnt=0;
        powerPunchTimer=0;
        inPower=false;
        setRegion(78*(i++), 0, 78, 247);
        body.setActive(true);
    }
}