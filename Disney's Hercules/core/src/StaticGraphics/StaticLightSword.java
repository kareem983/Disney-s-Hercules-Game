package StaticGraphics;

import MovingObjects.Hercules;
import Sprites.Swords;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class StaticLightSword extends Swords{
    int counter,soundcounter;
    
    public StaticLightSword(float x, float y, Hercules herucle) {
        this.herucle = herucle;this.x=x;this.y=y;
        Tsword = new Texture("Sprites\\lightsword.png");
        Array<TextureRegion> frame= new Array<TextureRegion>();
        for (int i = 0; i < 3; i++) {
            frame.add(new TextureRegion(Tsword, i * 76, 0, 76, 215));
        }
        Asword = new Animation(0.1f, frame);
        Asword.setPlayMode(Animation.PlayMode.LOOP);
        frame.clear();
        counter=0;soundcounter=0;
        setBounds(0, 0, 76 * 3 / Main.PPM, 215 / Main.PPM);
        setPosition(x, y);
        music = Main.manager.get("Audio//Hercules - Voices//Hercules//LighteningSword.wav", Music.class);
    }
     @Override
    public void update() {
        if (herucle.b2body.getPosition().x > x-(940/Main.PPM) && soundcounter==0)
        {
             music.play();
             soundcounter++; 
        }
        
         if (herucle.b2body.getPosition().x > x && herucle.b2body.getPosition().x < x+76 * 3 / Main.PPM&&herucle.b2body.getPosition().y>y &&herucle.b2body.getPosition().y<y+215 / Main.PPM&&counter==0)
        {
            
           counter++;  
           herucle.pickedlightsword=true;
           setBounds(0,0,0,0);  
        }
        
        statetimer += Gdx.graphics.getDeltaTime();
        region = (TextureRegion) Asword.getKeyFrame(statetimer, true);
        setRegion(region);
        if (statetimer > 5) {
            statetimer = 0;
        }
    }
     public boolean Finish(){
        return false ;
    }
}