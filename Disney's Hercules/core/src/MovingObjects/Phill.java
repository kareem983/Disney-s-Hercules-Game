
package MovingObjects;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

public class Phill extends SecondaryCharacter{
    private float stateTime;
    private float motionTime;
    private boolean once;
    private Animation walkingAnimation;
    private TextureRegion currentRegion;
    private Array<TextureRegion> frames;
    
    public Phill(PlayScreen screen, float x, float y){
        super (screen, x, y);
        
        frames = new Array<TextureRegion>();
        for (int i =0; i < 6; ++i)
            frames.add(new TextureRegion(screen.getTotalAtlas().findRegion("Phill"), i*136, 0, 140, 260));
           
        walkingAnimation = new Animation(0.2f, frames);
        frames.clear();
        velocity.x = -0.5f;
        stateTime = 0;
        motionTime = 0;
        once =false;
        setBounds(getX(), getY(), 120/Main.PPM, 120/Main.PPM);
    }

    @Override
    protected void defineCharacter(){
            BodyDef bdef = new BodyDef();
            bdef.position.set(getX(),getY());
            bdef.type = BodyDef.BodyType.DynamicBody ;
            body =world.createBody(bdef) ;

            FixtureDef fdef = new FixtureDef();
            CircleShape shape = new CircleShape();
            shape.setRadius(20 / Main.PPM);

            //fdef.filter.categoryBits = Main.ENEMY_BIT;
            fdef.filter.maskBits = Main.CHARACTERS_GROUND_BIT; 

            fdef.shape = shape ;
            body.createFixture(fdef).setUserData(this); 
}
    
    @Override
    public void update(float dt) {
          stateTime+=dt;
          motionTime+=dt;
            
            body.setLinearVelocity(velocity);
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight()/2);
            currentRegion = (TextureRegion)walkingAnimation.getKeyFrame(stateTime, true);
            setRegion(currentRegion);
        
        if (motionTime > 30){
            reverseVelocity(true, false);
            motionTime=0;
            once = false;
        }
        else if (motionTime > 5 && !once){
            reverseVelocity(true, false);
            once = true;
        }
        if (velocity.x> 0 && currentRegion.isFlipX())
            currentRegion.flip(true, false);
        else if (velocity.x < 0 && !currentRegion.isFlipX())
            currentRegion.flip(true, false);
        
    }

}
