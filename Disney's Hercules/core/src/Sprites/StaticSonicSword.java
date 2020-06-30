
package Sprites;

import MovingObjects.Hercules;
import Sprites.Swords;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class StaticSonicSword extends Swords{
    int counter;
    public StaticSonicSword(float x, float y, Hercules herucle) {
        this.herucle = herucle;this.x=x;this.y=y;
        Array<TextureRegion> frame= new Array<TextureRegion>();
        Tsword = new Texture("Sprites\\Level 1\\Swords\\LeftSonicSword.png");
        frame.add(new TextureRegion(Tsword));
       Tsword = new Texture("Sprites\\Level 1\\Swords\\UpSonicSword.png");
        frame.add(new TextureRegion(Tsword));
        Tsword = new Texture("Sprites\\Level 1\\Swords\\RightSonicSword.png");
        frame.add(new TextureRegion(Tsword));
        Asword = new Animation(0.1f, frame);
        Asword.setPlayMode(Animation.PlayMode.LOOP);
        frame.clear();
        counter=0;
        setBounds(0,0,200/Main.PPM,200/Main.PPM);
        setPosition(x, y);
    }

    @Override
    public void update() {
          if(herucle.body.getPosition().x >x && herucle.body.getPosition().y>y && counter==0&&herucle.body.getPosition().x<x+100/Main.PPM)
        {
            counter++;
            herucle.pickedsonicsword=true;
            setBounds(0,0,0,0);
        }
         statetimer += Gdx.graphics.getDeltaTime();
      
        region = (TextureRegion) Asword.getKeyFrame(statetimer, true);
        setRegion(region);
        if (statetimer>5)statetimer=0;
    }
    
        public boolean Finish(){
        return false ;
    }

}
