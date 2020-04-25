package Sprites;

import MovingObjects.Hercules;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class RightFireBallSword extends Swords {

    Array<TextureRegion> frame = new Array<TextureRegion>();
    public final float directionx, directiony;
    float increment = 0,soundsword;

    public RightFireBallSword(float x, float y, Hercules herucle) {
        this.herucle = herucle;this.x=x;this.y=y;
             music = Main.manager.get("Audio//Hercules - sounds//Fireball Sword.wav",Music.class);
             music.setLooping(false);
             music.setVolume(0.5f); 
             soundsword=0;
        frame.add(new TextureRegion(Msword, 26, 184, 202, 80));
        frame.add(new TextureRegion(Msword, 232, 183, 206, 77));
        frame.add(new TextureRegion(Msword, 15, 326, 198, 79));
        frame.add(new TextureRegion(Msword, 227, 322, 222, 80));
        Asword = new Animation(1f / Main.PPM, frame);
        setBounds(0, 0, 222 / Main.PPM, 80 / Main.PPM);
        directionx = herucle.b2body.getPosition().x;
        directiony = herucle.b2body.getPosition().y;
    }

    @Override
    public void update() {
         if (soundsword==0)
       {   
           soundsword++ ;
           music.play();
       }
        statetimer += Gdx.graphics.getDeltaTime();
        region = new TextureRegion((TextureRegion) Asword.getKeyFrame(statetimer, true));
        setRegion(region);
        increment += Gdx.graphics.getDeltaTime() * 30;
        setPosition(directionx + 100 * increment / Main.PPM, directiony);
        if (statetimer > 5) {
            setBounds(0, 0, 0, 0);
        }
      
    }
     public boolean Finish(){
        return (statetimer > 1) ;
    }
}