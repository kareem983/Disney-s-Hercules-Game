
package MovingObjects;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

public class Ape extends SecondaryCharacter{
    private float stateTime;
    private boolean once;
    private boolean smach;
    private Animation walkingAnimation;
    private Animation smachingAnimation;
    private TextureRegion currentRegion;
    private Array<TextureRegion> frames;
    
    public Ape(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for (int i =0; i < 6; ++i)
            frames.add(new TextureRegion(screen.getTotalAtlas().findRegion("Apes"), i*87, 87, 85, 87));
        walkingAnimation = new Animation(.2f, frames);
        frames.clear();
        
        for (int i =0; i < 8; ++i)
            frames.add(new TextureRegion(screen.getTotalAtlas().findRegion("Apes"), i*87, 0, 85, 87));
        smachingAnimation = new Animation(.2f, frames);
            frames.clear();
        stateTime = 0;
        once =false;
        smach = false;
        setBounds(getX(), getY(), 100/Main.PPM, 100/Main.PPM);
    }

    
    @Override
    protected void defineCharacter() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(),getY());
        bdef.type = BodyDef.BodyType.DynamicBody ;
        body =world.createBody(bdef) ;

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(20 / Main.PPM);
        fdef.filter.maskBits = Main.CHARACTERS_GROUND_BIT;
        fdef.shape = shape ;
        body.createFixture(fdef).setUserData(this); 
    }

    @Override
    public void update(float dt) {
        stateTime+=dt;
        body.setLinearVelocity(velocity);
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight()/2);
        
        if (smach){
            currentRegion = (TextureRegion)smachingAnimation.getKeyFrame(stateTime, true);
            body.setLinearVelocity(0, 0);
            if (stateTime > 5)
                smach = false;
        }
        else
            currentRegion = (TextureRegion)walkingAnimation.getKeyFrame(stateTime, true);
        
        setRegion(currentRegion);

    if (velocity.x> 0 && currentRegion.isFlipX())
        currentRegion.flip(true, false);
        
    else if (velocity.x < 0 && !currentRegion.isFlipX())
        currentRegion.flip(true, false);
    
    if (stateTime > 19 && once){
        reverseVelocity(true, false);
        stateTime=0;
        smach=true;
        once =false;
    }
    else if (stateTime > 19 && !once){
        reverseVelocity(true, false);
        stateTime=0;
        smach=true;
        once = true;
    }
  }
    
}
