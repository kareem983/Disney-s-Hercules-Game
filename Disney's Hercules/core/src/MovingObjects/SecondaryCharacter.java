
package MovingObjects;

import Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class SecondaryCharacter extends Sprite{
    
    protected World world;
    protected PlayScreen screen;
    public Body body;
    public Vector2 velocity;
    
      public SecondaryCharacter(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineCharacter();
        velocity = new Vector2(-0.4f, 0);
    }
      
    protected abstract void defineCharacter();
    public abstract void update (float dt);
    
    public void reverseVelocity(boolean x, boolean y){
        if (x)
            velocity.x = -velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }
    
}
