
package StaticGraphics;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Flame extends Sprite{
    
    private Animation candleFlame;
    private float elapsedTime;
    
    public Flame(PlayScreen screen) {
        super(screen.getAtlas().findRegion("Flame"));
    
        Array <TextureRegion> frames = new Array<TextureRegion>();
        
        for (int i =0; i < 4; ++i)
            frames.add(new TextureRegion(getTexture(), i*155, 0, 155, 152));
        candleFlame = new Animation(0.4f, frames);
    }
    
    public void update(float dt){
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        elapsedTime += dt;
       TextureRegion region = (TextureRegion) candleFlame.getKeyFrame(elapsedTime, true);
       return region;
    }
}
