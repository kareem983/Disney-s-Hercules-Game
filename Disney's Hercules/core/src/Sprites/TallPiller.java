package Sprites;

import MovingObjects.Hercules;
import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
    public boolean STATE, x , crashed, cc, vv;
    private int normalcounter;
    public float pposx,pposy;
    private Music Pillarmusic, sound;
    private Hercules player;
    
    public TallPiller(World world, PlayScreen screen , Hercules player, float pposx , float pposy) {
        super(screen.getAtlas_pillar().findRegion("t10"));

        this.world = world;
        this.screen = screen;
        this.player = player;
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
        Pillarmusic = Main.manager.get("Audio//Hercules - sounds//Tall pillar Cracked.wav");
        sound = Main.manager.get("Audio//Hercules - Voices//Phil//Excellenty.wav", Music.class);
        cc = vv = false; normalcounter = 0;
    }

    public void update(float dt) {
        stateTimer += Gdx.graphics.getDeltaTime();
        setPosition(pposx /Main.PPM, pposy /Main.PPM);
        returned = Animate(dt);

        if (returned == crashedpic ) 
            setRegion(crashedpic);
        else
            setRegion(returned);
        
        if (stateTimer > 10) stateTimer = 0;
        
        if (Gdx.input.isKeyJustPressed(Main.powerPunch)){
            cc = true;
            handleTallPillarCrash();
        }
        else if (Gdx.input.isKeyJustPressed(Main.normalPunch)) {
            vv = true;
            handleTallPillarCrash();
        }
    }
    
    private void handleTallPillarCrash() {
        //Allow Crash Animation to start
        if (crashed == false) {
            if (getBoundingRectangle().overlaps(player.getBoundingRectangle())) {
                Pillarmusic.setVolume(Main.vol);
                Pillarmusic.setLooping(false);
                Pillarmusic.play();

                if (cc == true || (vv == true && normalcounter == 4)) {
                    sound.setVolume(Main.vol);
                    sound.play();

                    STATE = true;
                    world.destroyBody(b2body);
                    crashed = true;
                } else if (vv) {
                    normalcounter++;
                    vv = false;
                }
            }
        }
         vv = cc = false;
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