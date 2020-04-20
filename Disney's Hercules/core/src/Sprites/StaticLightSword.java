package Sprites;

import MovingObjects.Hercules;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class StaticLightSword extends Swords{
    int counter;
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
        counter=0;
        setBounds(0, 0, 76 * 3 / Main.PPM, 215 / Main.PPM);
        setPosition(x, y);
    }
     @Override
    public void update() {
         if (herucle.b2body.getPosition().x > x && herucle.b2body.getPosition().y>y &&counter==0&&herucle.b2body.getPosition().x<x+100/Main.PPM)
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
