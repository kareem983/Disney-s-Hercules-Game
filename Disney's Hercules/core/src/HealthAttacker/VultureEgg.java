
package HealthAttacker;

import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class VultureEgg extends Sprite{
    
    private Animation animation;
    private float stateTimer;

    public VultureEgg(float x, float y) {
        defineEgg();
        setPosition(x, y);
    }

    private void defineEgg() {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 8; ++i)
            frames.add(new TextureRegion(new Texture("Sprites/Level 2/Vulture/Egg.png"), i*66, 0, 66, 66));
        animation = new Animation(0.1f, frames);
        setBounds(0, 0, 60/Main.PPM, 60/Main.PPM);
    }
    
    public void update(float dt){
        stateTimer += dt;
        setRegion((TextureRegion) animation.getKeyFrame(stateTimer, true));
        setPosition(getX(), getY() - 2/Main.PPM);
    }
    
}
