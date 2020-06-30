
package MovingObjects;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;

public class Wagon extends SecondaryCharacter{
    private float stateTime;
    public static boolean running;
    private Animation animation;
    private TextureRegion currentRegion;
    private Array<TextureRegion> frames;
    
    public Wagon(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        
        frames = new Array<TextureRegion>();
        for(int i=0;i<3;++i)
            frames.add(new TextureRegion(screen.getTotal2Atlas().findRegion("Wagon"), i*325, 0, 325, 190));
        
        animation = new Animation(0.08f, frames);
        frames.clear();
        velocity.x = stateTime = 0f;
        running = false;
        setBounds(getX(), getY(), 200/Main.PPM, 120/Main.PPM);
    }
    
    @Override
    protected void defineCharacter() {
            BodyDef bdef = new BodyDef();
            bdef.position.set(getX(),getY());
            bdef.type = BodyDef.BodyType.DynamicBody ;
            body =world.createBody(bdef) ;

            FixtureDef fdef = new FixtureDef(), fdef2 = new FixtureDef();
            
            PolygonShape border = new PolygonShape(), topBorder = new PolygonShape() ;
            Vector2[] vertice = new Vector2[4], vertice2 = new Vector2[4];
            
            vertice[0] = new Vector2(-100, 20).scl(1 / Main.PPM);
            vertice[1] = new Vector2(-100, -10).scl(1 / Main.PPM);
            vertice[2] = new Vector2(90, 20).scl(1 / Main.PPM);
            vertice[3] = new Vector2(90, -10).scl(1 / Main.PPM);
            border.set(vertice);
            
            fdef.shape = border;
            fdef.friction=10f;
            
            vertice2[0] = new Vector2(-90, 10).scl(1 / Main.PPM);
            vertice2[1] = new Vector2(-90, 20).scl(1 / Main.PPM);
            vertice2[2] = new Vector2(80, 10).scl(1 / Main.PPM);
            vertice2[3] = new Vector2(80, 20).scl(1 / Main.PPM);
            topBorder.set(vertice2);
            
            fdef2.filter.categoryBits = Main.WAGON_BIT;
            fdef2.filter.maskBits = Main.HERCULES_BIT;
            fdef2.shape = topBorder;
            fdef2.isSensor = true;
            
            body.createFixture(fdef);
            body.createFixture(fdef2).setUserData(this);
    }

    @Override
    public void update(float dt) {
            stateTime+=dt;
            if (!running) stateTime=0;
            body.setLinearVelocity(velocity);
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight()/2);
            currentRegion = (TextureRegion)animation.getKeyFrame(stateTime, true);
            setRegion(currentRegion);
    }
    
    public void move(){
       running = true;
       velocity.x=2f; 
    }
    public void stop(){
        running = false;
        velocity.x = 0f;
        screen.getPlayer().body.applyLinearImpulse(-1.38f, 0f, 0f, 0f, false);
    }
}
