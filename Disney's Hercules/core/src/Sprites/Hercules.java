
package Sprites;

import Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.Hercules.game.Main;


public class Hercules extends Sprite{
    
    private float HerculesInitPosX = 48f;
    private float HerculesInitPosY = 48f;
    public float HerculesMaxSpeed = 1.2f;
    
    public enum State {JUMPING_DOWN, JUMPING_UP, STANDING, RUNNING};
    
     public Hercules(World world, PlayScreen screen){
            defineHercules();
     }
     public void defineHercules(){
         
     }
}
