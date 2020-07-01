package MovingObjects;

import Screens.*;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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

public class Hercules extends Sprite {
    
    public PlayScreen screen;
    public World world;
    public Body body;
    public static boolean onGround;
    public boolean pickedlightsword = false, pickedfireballsword = false,pickedsonicsword=false;

    private float HerculesInitPosX=10f;
    private float HerculesInitPosY = 180f;
    public float HerculesMaxSpeed = 1.5f;
    
    public enum State {
        FALLING, JUMPING, STANDING, RUNNING, pushing_hand, pushing_sword, pushing_sword2, pushing_sword3, Drink, die, smallPush
    };
    public State currentState;
    public State previousState;
    private Animation HerculesRun;
    private Animation HerculesStand;
    private Animation HerculesJump;
    public Animation HerculesPush;
    public Animation HerculesSmallPush;
    public Animation HerculesSword;
    public Animation HerculesSword2;
    public Animation HerculesSword3;
    public Animation HerculesDrink;
    public Animation HerculesDie;
    private float stateTimer;
    private boolean runningRight;
    public BodyDef bdef;
    public boolean hercules_push = false;
    public float timePush = 0;
    public static boolean hercules_sword = false;
    private float timeSword = 0;
    public static boolean hercules_sword2 = false;
    public float timeSword2 = 0;
    public static boolean hercules_sword3 = false;
    private float timeSword3 = 0;
    public static boolean hercules_Drink = false;
    private float timeDrink = 0;
    public static boolean hercules_Die = false;
    private float timeDie = 0;
    public boolean hercules_Smallpush = false;
    public float timeSmallPush = 0;
    private Music sound;
    private static float soundTimer;
    
    public Hercules(World world, PlayScreen screen, float posX){
         this.HerculesInitPosX = posX;
         initializeConstructors(world, screen);
    }
    public Hercules(World world, PlayScreen screen) {
       initializeConstructors(world, screen);
    }
    
    private void initializeConstructors(World world, PlayScreen screen){
        this.world = world;
        this.screen = screen;
        defineHercules();

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = soundTimer=0;
        runningRight = true;
        setBounds(0, 0, 50 * 3 / Main.PPM, 75 * 3 / Main.PPM);
        defineAnimation(screen);
        
        sound = Main.manager.get("Audio//Hercules - Voices//Phil//Get your sword.wav", Music.class);
    }

    public boolean isRunningRight() {
        return runningRight;
    }

    public void update(float dt) {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2 + 50 / Main.PPM);
        setRegion(getFrame(dt));
        getYourSword(dt);
    }

    // this fn return which animation that will draw now 
    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;

        //depending on the state, get corresponding animation keyFrame.
        switch (currentState) {
            case pushing_hand:
                region = (TextureRegion) HerculesPush.getKeyFrame(stateTimer);
                break;
            case smallPush:
                region = (TextureRegion) HerculesSmallPush.getKeyFrame(stateTimer);
                break;

            case pushing_sword:
                region = (TextureRegion) HerculesSword.getKeyFrame(stateTimer);
                break;
            case pushing_sword2:
                region = (TextureRegion) HerculesSword2.getKeyFrame(stateTimer);
                break;
            case Drink:
                region = (TextureRegion) HerculesDrink.getKeyFrame(stateTimer);
                break;
            case die:
                region = (TextureRegion) HerculesDie.getKeyFrame(stateTimer);
                break;

            case JUMPING:
                region = (TextureRegion) HerculesJump.getKeyFrame(stateTimer, true);
                ;
                break;
            case RUNNING:
                region = (TextureRegion) HerculesRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default:
                //  region = HerculesStand;
                region = (TextureRegion) HerculesStand.getKeyFrame(stateTimer, true);

                break;
        }
        //if Hercules is running left and the texture isnt facing left... flip it.
        if ((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } //if Hercules is running right and the texture isnt facing right... flip it.
        else if ((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }
        //if the current state is the same as the previous state increase the state timer.
        //otherwise the state has changed and we need to reset timer.
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        //update previous state
        previousState = currentState;

        //return our final adjusted frame
        return region;
    }

    // this fn return the Hercules state that doing now 
    public State getState() {
        //Test to Box2D for velocity on the X and Y-Axis
        if(Wagon.running)
            return State.STANDING;
        else if (pushing_hand()) {
            return State.pushing_hand;
        } else if (pushing_sword()) {
            return State.pushing_sword;
        }else if (pushing_sword2()) {
            return State.pushing_sword2;
        } else if (Hercules_Drink()) {
            return State.Drink;
        } else if (Hercules_Die()) {
            return State.die;
        } else if (pushing_Smallhand()) {
            return State.smallPush;
        } //if Hercules is going positive in Y-Axis he is jumping... or if he just jumped and is falling remain in jump state
        else if (body.getLinearVelocity().y > 0 || (body.getLinearVelocity().y < 0 && previousState == State.JUMPING)) {
            return State.JUMPING;
        } //if negative in Y-Axis mario is falling
        else if (body.getLinearVelocity().y < 0) {
            return State.FALLING;
        } //if mario is positive or negative in the X axis he is running
        else if (body.getLinearVelocity().x != 0) {
            return State.RUNNING;
        } //if none of these return then he must be standing
        else {
            return State.STANDING;
        }

    }

    // this fn make the circle Which we move it and take his postion and give it to Hercules sprite  
    private void defineHercules() {
        bdef = new BodyDef();
        bdef.position.set(HerculesInitPosX / Main.PPM, HerculesInitPosY / Main.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        FixtureDef fdef2 = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(30 / Main.PPM);
        fdef2.shape = shape;
        fdef2.filter.categoryBits = Main.HERCULES_BIT;
        body.createFixture(fdef2).setUserData(this);

        // CREATE SIDES FOR BABY DRAGON
        PolygonShape border = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-50, 120).scl(1 / Main.PPM);
        vertice[1] = new Vector2(50, 120).scl(1 / Main.PPM);
        vertice[2] = new Vector2(-50, 30).scl(1 / Main.PPM);
        vertice[3] = new Vector2(50, 30).scl(1 / Main.PPM);
        border.set(vertice);

        fdef.shape = border;
        fdef.filter.categoryBits = Main.HERCULES_BORDER_BIT;
        fdef.filter.maskBits = Main.GROUND_BIT | Main.ENEMY_BIT;
        fdef.isSensor = true;
        body.createFixture(fdef);
    }

    private void defineAnimation(PlayScreen screen) {
        Array<TextureRegion> frames = new Array<TextureRegion>();

        //get run animation frames and add them to HerculesRun Animation
            for (int i = 1; i < 8; i++) 
                frames.add(new TextureRegion(screen.getAtlas_Run().findRegion("run__"), i * 50, 0, 47, 75));
        
        HerculesRun = new Animation(0.1f, frames);

        frames.clear();

        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_jump\\jump1.png"), 0, 0, 50, 96));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_jump\\jump2.png"), 0, 0, 50, 96));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_jump\\jump3.png"), 0, 0, 45, 96));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_jump\\jump4.png"), 0, 0, 45, 96));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_jump\\jump5.png"), 0, 0, 50, 96));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_jump\\jump6.png"), 0, 0, 50, 96));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_jump\\jump7.png"), 0, 0, 50, 96));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_jump\\jump8.png"), 0, 0, 50, 96));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_jump\\jump9.png"), 0, 0, 50, 96));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_jump\\jump10.png"), 0, 0, 50, 96));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_jump\\jump11.png"), 0, 0, 50, 96));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_jump\\jump12.png"), 0, 0, 50, 96));

        HerculesJump = new Animation(0.04f, frames);
        frames.clear();

        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_push\\push1.png"), 0, 0, 50, 78));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_push\\push2.png"), 0, 0, 50, 78));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_push\\push3.png"), 0, 0, 50, 78));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_push\\push4.png"), 0, 0, 50, 78));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_push\\push5.png"), 0, 0, 50, 78));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_push\\push6.png"), 0, 0, 50, 78));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_push\\push7.png"), 0, 0, 50, 78));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_push\\push8.png"), 0, 0, 50, 78));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_push\\push9.png"), 0, 0, 50, 78));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_push\\push10.png"), 0, 0, 50, 78));

        HerculesPush = new Animation(0.09f, frames);
        HerculesPush.setPlayMode(Animation.PlayMode.NORMAL);
        frames.clear();

        //     frames.add(new TextureRegion(new Texture("Sprites\\Herclues_sword\\sword1.png"),  0, 0, 50, 80));
        //  frames.add(new TextureRegion(new Texture("Sprites\\Herclues_sword\\sword2.png"),  0, 0, 50, 80));
        //  frames.add(new TextureRegion(new Texture("Sprites\\Herclues_sword\\sword3.png"),  0, 0, 59, 80));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Herclues_sword\\sword4.png"), 0, 0, 50, 80));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Herclues_sword\\sword5.png"), 0, 0, 50, 80));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Herclues_sword\\sword6.png"), 0, 0, 50, 80));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Herclues_sword\\sword7.png"), 0, 0, 50, 80));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Herclues_sword\\sword8.png"), 0, 0, 60, 80));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Herclues_sword\\sword9.png"), 0, 0, 60, 80));

        HerculesSword = new Animation(0.1f, frames);
        HerculesSword.setPlayMode(Animation.PlayMode.NORMAL);
        frames.clear();
        
         frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Herclues_sword2\\1.png"), 0, 0, 45, 84));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Herclues_sword2\\2.png"), 0, 0, 75, 86));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Herclues_sword2\\3.png"), 0, 0, 50, 86));

        HerculesSword2 = new Animation(0.1f, frames);
        HerculesSword2.setPlayMode(Animation.PlayMode.NORMAL);
        frames.clear();


        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_drink\\1.png"), 0, 0, 50, 75));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_drink\\2.png"), 0, 0, 50, 75));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_drink\\3.png"), 0, 0, 45, 75));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_drink\\4.png"), 0, 0, 45, 75));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_drink\\5.png"), 0, 0, 45, 75));

        HerculesDrink = new Animation(0.1f, frames);
        HerculesDrink.setPlayMode(Animation.PlayMode.NORMAL);
        frames.clear();

        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_die\\1.png"), 0, 0, 45, 75));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_die\\2.png"), 0, 0, 50, 75));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_die\\3.png"), 0, 0, 50, 75));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_die\\4.png"), 0, 0, 35, 75));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_die\\5.png"), 0, 0, 35, 75));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_die\\6.png"), 0, 0, 50, 75));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_die\\7.png"), 0, 0, 50, 75));
        HerculesDie = new Animation(0.15f, frames);
        HerculesDie.setPlayMode(Animation.PlayMode.NORMAL);
        frames.clear();

        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_stand\\1.png"), 0, 0, 45, 75));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_stand\\2.png"), 0, 0, 45, 75));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Hercules_stand\\3.png"), 0, 0, 45, 75));
        HerculesStand = new Animation(0.15f, frames);
        HerculesStand.setPlayMode(Animation.PlayMode.LOOP);
        frames.clear();

        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Herclues_sword\\sword1.png"), 0, 0, 50, 80));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Herclues_sword\\sword2.png"), 0, 0, 50, 80));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\HERCULES\\Herclues_sword\\sword3.png"), 0, 0, 59, 80));

        HerculesSmallPush = new Animation(0.1f, frames);
        HerculesSmallPush.setPlayMode(Animation.PlayMode.NORMAL);
        frames.clear();
    }
    // this fn return true when Hercures pushing by hand

    public boolean pushing_hand() {
        if (hercules_push == true) {
            timePush += Gdx.graphics.getDeltaTime();
            if (timePush < 0.9) {
                return true;
            } else {
                timePush = 0;
                hercules_push = false;
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean pushing_Smallhand() {
        if (hercules_Smallpush == true) {
            timeSmallPush += Gdx.graphics.getDeltaTime();
            if (timeSmallPush < 0.4) {
                return true;
            } else {
                timeSmallPush = 0;
                hercules_Smallpush = false;
                return false;
            }
        } else {
            return false;
        }
    }
    // this fn return true when Hercures pushing by sword 

    public boolean pushing_sword() {
        if (hercules_sword == true) {
            timeSword += Gdx.graphics.getDeltaTime();
            if (timeSword < 0.7) {
                return true;
            } else {
                timeSword = 0;
                hercules_sword = false;
                return false;
            }
        } else {
            return false;
        }
    }
    public boolean pushing_sword2() {
        if (hercules_sword2 == true) {
            timeSword2 += Gdx.graphics.getDeltaTime();
            if (timeSword2 < 0.4) {
                return true;
            } else {
                timeSword2 = 0;
                hercules_sword2 = false;
                return false;
            }
        } else {
            return false;
        }
    }

    // this fn return true when Hercures Drink 
    public boolean Hercules_Drink() {
        if (hercules_Drink == true) {
            timeDrink += Gdx.graphics.getDeltaTime();
            if (timeDrink < 1) {
                return true;
            } else {
                timeDrink = 0;
                hercules_Drink = false;
                return false;
            }
        } else {
            return false;
        }
    }

    // this fn return true when Hercures Die
    public boolean Hercules_Die() {
        if (hercules_Die == true) {
            timeDie += Gdx.graphics.getDeltaTime();
            if (timeDie < 1.5) {
                return true;
            } else {
                timeDie = 0;
                hercules_Die = false;
                return false;
            }
        } else {
            return false;
        }
    }
     //
   public float getStateTimer(){
       return stateTimer;
   }
   
    private void getYourSword(float dt){
        if (body.getLinearVelocity().x==0 && body.getLinearVelocity().y==0){
            soundTimer += dt;
            if (soundTimer > 8 && soundTimer != 0){
                soundTimer=0;
                if (!sound.isPlaying())
                    sound.setVolume(Main.vol);
                    sound.play();
            }
        }
        else soundTimer=0;
    }
   
    public static void toLevel2(){
        
    }
}
