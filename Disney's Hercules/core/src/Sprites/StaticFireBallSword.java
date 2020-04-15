
package Sprites;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class StaticFireBallSword extends Swords{
    int counter;
    public StaticFireBallSword(float x, float y, Hercules herucle) {
        this.herucle = herucle;this.x=x;this.y=y;
        Tsword = new Texture("Sprites\\fireball1.png");
        Array<TextureRegion> frame= new Array<TextureRegion>();
        frame.add(new TextureRegion(Tsword,181,30,71,200));
        frame.add(new TextureRegion(Tsword,323,18,73,225));
        frame.add(new TextureRegion(Tsword,185,236,79,211));
        frame.add(new TextureRegion(Tsword,329,259,77,198));

        Asword = new Animation(0.1f, frame);
        Asword.setPlayMode(Animation.PlayMode.LOOP);
        frame.clear();
        counter=0;
        setBounds(0, 0, 79 / Main.PPM, 225 / Main.PPM);
       
        setPosition(x, y);
    }
     @Override
    public void update() {
        if(herucle.b2body.getPosition().x >x && herucle.b2body.getPosition().y>y && counter==0)
        {
            counter++;
            herucle.pickedfireballsword=true;
            setBounds(0,0,0,0);
        }
        statetimer += Gdx.graphics.getDeltaTime();
        region = (TextureRegion) Asword.getKeyFrame(statetimer, true);
        setRegion(region);
        if (statetimer > 5) {
            statetimer = 0;
        }
    }
}

