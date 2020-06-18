
package HealthAttacker;

import Scenes.HUD;
import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

public class BabyDragon extends Enemy{
    
    private float stateTime;
    private Animation flyAnimation;
    private Animation dieAnimation;
    private TextureRegion currentRegion;
    private Array<TextureRegion> frames;
    private boolean setToDestroy;
    private boolean destroyed;
    private Music sound;
    
    public BabyDragon(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        
        frames = new Array<TextureRegion>();
        for (int i =0; i < 6; ++i)
            frames.add(new TextureRegion(screen.getTotalAtlas().findRegion("BabyDragons"), i*90, 60, 90, 60));
        flyAnimation = new Animation(0.2f, frames);
        frames.clear();
        
        for (int i =6; i < 9; ++i)
            frames.add(new TextureRegion(screen.getTotalAtlas().findRegion("BabyDragons"), i*90, 60, 90, 60));
        dieAnimation = new Animation(.2f, frames);
        
        stateTime = 0;
        setBounds(getX(), getY(), 100/Main.PPM, 100/Main.PPM);
        setToDestroy = false;
        destroyed = false;
        
        sound = Main.manager.get("Audio//Hercules - Voices//Hercules//BabyDragon.wav", Music.class);
    }
    
  @Override
    protected void defineEnemy() {
             BodyDef bdef = new BodyDef();
             bdef.position.set(getX(),getY());
             bdef.type = BodyDef.BodyType.DynamicBody ;
             body =world.createBody(bdef) ;
             
             FixtureDef fdef = new FixtureDef();
             CircleShape shape = new CircleShape();
             shape.setRadius(20 / Main.PPM);
             
             fdef.filter.categoryBits = Main.ENEMY_BIT;
             fdef.filter.maskBits = Main.BABYDRAGONS_SURFACE_BIT | Main.SKY_BORDER_BIT |Main.HERCULES_BORDER_BIT; 
                     
             fdef.shape = shape ;
             body.createFixture(fdef).setUserData(this); 
    }
    public void update (float dt){
        stateTime+=dt;
        if (setToDestroy && !destroyed){
            world.destroyBody(body);
            destroyed = true;
            setOrigin(getWidth()/2, getHeight()/2);
            stateTime = 0;
        }
        else if (destroyed){
            currentRegion = (TextureRegion)dieAnimation.getKeyFrame(stateTime, false);
            setRegion(currentRegion);
        }
        else if (!destroyed){
            body.setLinearVelocity(velocity);
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight()/2);
            currentRegion = (TextureRegion)flyAnimation.getKeyFrame(stateTime, true);
            setRegion(currentRegion);
            setOrigin(getWidth()/2, getHeight()/2);
            
        if (velocity.x> 0 && !currentRegion.isFlipX())
            currentRegion.flip(true, false);
        else if (velocity.x < 0 && currentRegion.isFlipX())
            currentRegion.flip(true, false);
        }
    }
    public void draw(Batch batch){
        if(!destroyed || stateTime < 2)
            super.draw(batch);
    }

    @Override
    public void Stap() {
      setToDestroy = true;
      if (!sound.isPlaying())
          sound.setVolume(Main.vol);
      sound.play();
      HUD.score += 15;
    }

    @Override
    public void attackHercules() {
        HUD.hit();
    }
    
}
