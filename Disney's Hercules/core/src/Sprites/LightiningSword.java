package Sprites;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class LightiningSword extends Swords {

    Array<TextureRegion> frame = new Array<TextureRegion>();

    public LightiningSword(float x, float y, Hercules herucle) {
        this.herucle = herucle;this.x=x;this.y=y;
        Tsword = new Texture("Sprites\\lightsword2.png");
        for (int i = 0; i < 3; i++) {
            frame.add(new TextureRegion(Tsword, 0, i * 76, 215, 76));
        }
        Asword = new Animation(5f / Main.PPM, frame);
        setBounds(0, 0, 215 * 6 / Main.PPM, 76 / Main.PPM);
    }
    
    @Override
    public void update() {
        if (statetimer > 0.8) {
            setBounds(0, 0, 0, 0);
        }
        if (!herucle.isRunningRight())
        {
            
        setPosition(herucle.b2body.getPosition().x - getWidth() / 2 + 250/ Main.PPM-215 * 4 / Main.PPM, herucle.b2body.getPosition().y - getHeight() / 2 + 50 / Main.PPM);
        statetimer += Gdx.graphics.getDeltaTime();
        region = (TextureRegion) Asword.getKeyFrame(statetimer,true);
        if(!region.isFlipX()){
        region.flip(true, false);
        }
        setRegion(region);
        }
        else
        {
        setPosition(herucle.b2body.getPosition().x - getWidth() / 2+165/Main.PPM+215*2 / Main.PPM, herucle.b2body.getPosition().y - getHeight() / 2 + 50 / Main.PPM);
        statetimer += Gdx.graphics.getDeltaTime();
        region = (TextureRegion) Asword.getKeyFrame(statetimer , true);
        if(region.isFlipX()){
        region.flip(true, false);
        }
        setRegion(region);
        }
       
    }
}
