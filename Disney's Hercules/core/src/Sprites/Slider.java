
package Sprites;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Slider{
    
    private World world;
    private final float x, y;
    private Body slider, slider2;
    
    public Slider(PlayScreen screen) {
        this.world = screen.getWorld();
        this.x = 42960/Main.PPM; 
        this.y = 180/Main.PPM;
        
        defineSlider();
        defineSlider2();
    }

    private void defineSlider() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.StaticBody;
        
        FixtureDef fdef = new FixtureDef();
        PolygonShape border = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
            
        vertice[0] = new Vector2(0, 0).scl(1 / Main.PPM);
        vertice[1] = new Vector2(50, -20).scl(1 / Main.PPM);
        vertice[2] = new Vector2(300, 400).scl(1 / Main.PPM);
        vertice[3] = new Vector2(350, 420).scl(1 / Main.PPM);
        border.set(vertice);
        
        fdef.density=50f;
        fdef.friction=1f;
        fdef.shape = border;
        slider = world.createBody(bdef);
        slider.createFixture(fdef);
    }
    
    private void defineSlider2() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x+2.5f, y);
        bdef.type = BodyDef.BodyType.StaticBody;
        
        FixtureDef fdef = new FixtureDef();
        PolygonShape border = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
            
        vertice[0] = new Vector2(0, 0).scl(1 / Main.PPM);
        vertice[1] = new Vector2(-50, 20).scl(1 / Main.PPM);
        vertice[2] = new Vector2(-550, 350).scl(1 / Main.PPM);
        vertice[3] = new Vector2(-600, 370).scl(1 / Main.PPM);
        border.set(vertice);
        
        fdef.density=50f;
        fdef.friction=1f;
        fdef.shape = border;
        slider2 = world.createBody(bdef);
        slider2.createFixture(fdef);
    }
}
