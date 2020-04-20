package Sprites;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class LeftFireBallSword extends Swords {

    Array<TextureRegion> frame = new Array<TextureRegion>();
    public final float directionx, directiony;
    float increment = 0;
    int count = 0;

    public LeftFireBallSword(float x, float y, Hercules herucle) {
        this.herucle = herucle;this.x=x;this.y=y;
        Tsword = new Texture("Sprites\\fireball2.png");
        frame.add(new TextureRegion(Tsword, 26, 184, 202, 80));
        frame.add(new TextureRegion(Tsword, 232, 183, 206, 77));
        frame.add(new TextureRegion(Tsword, 15, 326, 198, 79));
        frame.add(new TextureRegion(Tsword, 227, 322, 222, 80));
        Asword = new Animation(1f / Main.PPM, frame);
        setBounds(0, 0, 222 / Main.PPM, 80 / Main.PPM);
        directionx = herucle.b2body.getPosition().x;
        directiony = herucle.b2body.getPosition().y;
    }

    @Override
    public void update() {
        statetimer += Gdx.graphics.getDeltaTime();
        region = new TextureRegion((TextureRegion) Asword.getKeyFrame(statetimer, true));
        region.flip(true, false);
        setRegion(region);
        increment += Gdx.graphics.getDeltaTime()*-30;
        setPosition(directionx + 100 * increment / Main.PPM, directiony);
        if (statetimer > 1) {
            setBounds(0, 0, 0, 0);
        }
      

    }
      public boolean Finish(){
        return (statetimer > 1) ;
    }

}
