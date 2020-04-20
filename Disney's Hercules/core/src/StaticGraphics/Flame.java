
package StaticGraphics;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Flame extends Sprite{
    
    private Animation candleFlame;
    private float elapsedTime;
    
    public Flame(PlayScreen screen, float x, float y) {
        super(screen.getFlameAtlas().findRegion("Flame"));
        
        setPosition(x, y);
        Array <TextureRegion> frames = new Array<TextureRegion>();
        
        for (int i =0; i < 4; ++i)
            frames.add(new TextureRegion(getTexture(), i*155, 0, 155, 152));
        candleFlame = new Animation(0.2f, frames);
        setBounds(getX(), getY(), 150/Main.PPM, 150/Main.PPM);
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
