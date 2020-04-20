package Sprites;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Animation;

public class TallPiller extends Sprite {
    Array<TextureRegion> frames;
    float stateTimer;
    public  Body b2body;
    public BodyDef bdef;
    public enum State {stand, crash};
    public  FixtureDef fdef;
    float crashtime;
    State current;
    public TextureRegion Original, crash , crashedpic,returned;
    World world;
    PlayScreen screen;
    Animation piller_animation;
    public boolean STATE, x , crashed;
    public float pposx,pposy;

    public TallPiller(World world, PlayScreen screen , float pposx , float pposy) {
        super(screen.getAtlas_pillar().findRegion("t10"));

        this.world = world;
        this.screen = screen;

        this.pposx = pposx;
        this.pposy=pposy;
        current = State.stand;
        STATE = false;
        x = false;
        Original = new TextureRegion(getTexture(), 505, 1, 113, 362);
        setBounds(0, 0, 250 / Main.PPM, 351 / Main.PPM);
        setRegion(Original);
        crash = new TextureRegion();
        crashedpic = new TextureRegion();
        returned=new TextureRegion();
        definePillar();
        crashed = false;
    }

    public void update(float dt) {
        stateTimer += Gdx.graphics.getDeltaTime();
        setPosition(pposx /Main.PPM, pposy /Main.PPM);
        returned = Animate(dt);

        if (returned == crashedpic ) {

            setRegion(crashedpic);
        }
        else {
            setRegion(returned);
        }
        if (stateTimer > 10) stateTimer = 0;
    }

    public TextureRegion Animate(float dt) {

        frames = new Array<TextureRegion>();

        frames.add(new TextureRegion(screen.getAtlas_pillar().findRegion("t10").getTexture(),505, 1, 113, 362));

        frames.add(new TextureRegion(screen.getAtlas_pillar().findRegion("t1").getTexture(), 1, 365, 250, 351));


        frames.add(new TextureRegion(screen.getAtlas_pillar().findRegion("t2").getTexture(), 1, 12, 250, 351));

        frames.add(new TextureRegion(screen.getAtlas_pillar().findRegion("t5").getTexture(), 253, 12, 250, 351));

        crashedpic =new TextureRegion(screen.getAtlas_pillar().findRegion("t6").getTexture(), 505, 365, 250, 351);
        setBounds(0, 0, 113 * 2 / Main.PPM, 362 * 2 / Main.PPM);
        piller_animation = new Animation(0.7f, frames, Animation.PlayMode.NORMAL);
        setPosition(pposx / Main.PPM, pposy / Main.PPM);

        frames.clear();
        crash = (TextureRegion) piller_animation.getKeyFrame(stateTimer,true);
        if (STATE == true && x == false) {
            crashtime+=Gdx.graphics.getDeltaTime();
            if (crashtime< 0.9f ){x=false;}
            else{x = true;}


            return crash;
        } else if(x == false) {

            return Original;
        }

        else {
            return crashedpic;
        }
    }
    public void definePillar(){
        bdef = new BodyDef();
        bdef.position.set( ((pposx+105)/ Main.PPM) , pposy / Main.PPM);
        bdef.type = BodyDef.BodyType.StaticBody ;
        b2body =world.createBody(bdef) ;

        fdef = new FixtureDef();
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(113/Main.PPM, 362*2  / Main.PPM);
        fdef.shape=polygon;
        b2body.createFixture(fdef);

    }

}