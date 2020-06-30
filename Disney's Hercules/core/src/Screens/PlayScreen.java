
package Screens;

import StaticGraphics.*;
import MovingObjects.*;
import Sprites.*;
import Scenes.*;
import HealthAttacker.*;
import Tools.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.Hercules.game.Main;
import java.util.ArrayList;

public abstract class PlayScreen implements Screen{
    
    //  Some Essential Variables
    public Main game;
    public float swordTimer;
    public boolean stopHercAction;
    public boolean noSwords;
    public Timer timer;

    //Basic PlayScreen Variables
    protected OrthographicCamera gameCam;
    protected Viewport gamePort;
    protected HUD hud;
    
    //Tiled Map Variables
    protected TmxMapLoader mapLoader;
    protected TiledMap map;
    protected OrthogonalTiledMapRenderer renderer;

    //Atlas
    protected TextureAtlas FlameAtlas;
    protected TextureAtlas TotalAtlas;
    protected TextureAtlas Wagon;
    protected TextureAtlas atlas_run;
    protected TextureAtlas atlas_jumb;
    protected TextureAtlas atlas_pillar;

    //Box2d Variables
    protected World world;
    protected Box2DDebugRenderer debuger;
    public WorldCreator creator;
    public WorldContactListener worldContactListener;

    //Sounds Variables
    public Music Game, GameOver;
    protected Music m;
     
    //Sprites
    protected DrawClass staticGraphics;
    public Swords staticlightiningsword;
    public Swords staticfireballsword, leftfirball, rightfireball;
    public Swords staticsonicsword, sonicsword, lightningsword;
    protected Hercules player;
   
    //Helping Variables and Objects
    protected ArrayList<GoldenCoin> goldcoin=new ArrayList<>();
    protected ArrayList<Cannons> filreball=new ArrayList<>();
    protected ProtectedShield Shield;
    protected Herculad juice;
   
    public PlayScreen(Main game, float HercPosX, String mapPath) {
        this.game = game; 
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(game.WIDTH / Main.PPM, game.HEIGHT / Main.PPM, gameCam);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(mapPath);
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Main.PPM);
        
        FlameAtlas = new TextureAtlas("Sprites\\Level 1\\Main\\Flame.atlas");
        TotalAtlas = new TextureAtlas("Sprites\\Level 1\\Main\\Total.pack");
        Wagon = new TextureAtlas("Sprites\\Level 2\\Wagon\\Wagon.pack");
        atlas_run = new TextureAtlas("Sprites\\Level 1\\HERCULES\\Run600_75.pack");
        atlas_jumb = new TextureAtlas("Sprites\\Level 1\\HERCULES\\H_Jump.pack");
        atlas_pillar = new TextureAtlas("Sprites\\Level 1\\Complement\\tallpillar.pack");
        staticGraphics = new DrawClass(this);
        
        //CREATING THE BOX2D AND WORLD PHYSICS
        world = new World(new Vector2(0, -10f), true);
        worldContactListener = new WorldContactListener();
        world.setContactListener(worldContactListener);
        debuger = new Box2DDebugRenderer();
        creator = new WorldCreator(this);
        
        player = new Hercules(world, this, HercPosX);
        worldContactListener.player = player;
        
        hud = new HUD(player, game.batch);
        staticlightiningsword = new StaticLightSword(15555f / Main.PPM, 300f / Main.PPM, player);
        staticfireballsword = new StaticFireBallSword(10400f / Main.PPM, 300f / Main.PPM, player);
        leftfirball = rightfireball = staticfireballsword;
        staticsonicsword = new StaticSonicSword(20112f / Main.PPM, 336f / Main.PPM, player);
        sonicsword = new SonicSword(0, 0, player);
        lightningsword = staticsonicsword;
        
        timer = new Timer(player, hud);
        stopHercAction=false;
        
        //Extra Objects
        Shield = new ProtectedShield(player, hud, 4512f, 176f);
        juice = new Herculad(world, this, player, 18080, 432);
    }
   
    /*Start Objects GETTERS*/
    public TextureAtlas getFlameAtlas() { return FlameAtlas;}

    public TextureAtlas getTotalAtlas() { return TotalAtlas;}
    
    public TextureAtlas getTotal2Atlas() { return Wagon;}
    
    public TextureAtlas getAtlas_pillar() { return atlas_pillar;}

    public Hercules getPlayer() { return player;}

    public TextureAtlas getAtlas_Run() {return atlas_run;}

    public TextureAtlas getAtlas_jumb() {return atlas_jumb;}

    public World getWorld() {return world;}

    public TiledMap getMap() {return map;}
    /*End Objects GETTERS*/
    
    /*Start Some Helping Methods*/
    protected void handleInput() {
        if (!stopHercAction){ // MAKES HERCULES DOESN'T RESPOND TO USER ACTIONS
            //control our player using immediate impulses
                  if (Gdx.input.isKeyJustPressed(game.up) && player.onGround) {
                player.body.applyLinearImpulse(new Vector2(0, 2.5f), player.body.getWorldCenter(), true);
                        HerculesActionSound("Audio//Hercules - Voices//Hercules//Jumb2.wav");
            } else if (Gdx.input.isKeyJustPressed(game.down)) {
                player.body.applyLinearImpulse(new Vector2(0, -2.5f), player.body.getWorldCenter(), true);
            } else if (Gdx.input.isKeyPressed(game.right) && player.body.getLinearVelocity().x <= player.HerculesMaxSpeed) {
                player.body.applyForceToCenter(new Vector2(3, 0), true);
            } else if (Gdx.input.isKeyPressed(game.left) && player.body.getLinearVelocity().x >= -1 * player.HerculesMaxSpeed) {
                player.body.applyForceToCenter(new Vector2(-3, 0), true);
            }
            if (Gdx.input.isKeyJustPressed(game.powerPunch)) {
                player.hercules_push = true;
                HerculesActionSound("Audio//Hercules - sounds//a2.wav");
            } else if (Gdx.input.isKeyJustPressed(game.normalPunch)) {
                    player.hercules_Smallpush = true;
                    HerculesActionSound("Audio//Hercules - sounds//Punch.wav");
            }
            else if (!noSwords){ // (NO SWORDS IN LEVEL 2)
                if (Gdx.input.isKeyPressed(game.sword2)) {
                    handleSword();
                } else if (Gdx.input.isKeyJustPressed(game.sword1)) {
                    player.hercules_sword2 = true;
                    HerculesActionSound("Audio//Hercules - sounds//sword.wav");            
                }
            }
        }
    }

    protected void handleSword() {
        if (player.pickedlightsword == true) {
            if (timer.statetimer1 > 0) {
                player.hercules_sword = true;
                lightningsword = new LightiningSword(0, 0, player);
            }

        } else if (player.pickedfireballsword == true) {

            if (timer.statetimer2 > 0) {
                if (player.isRunningRight()) {
                    rightfireball = new RightFireBallSword(0, 0, player);
                } else {
                    leftfirball = new LeftFireBallSword(0, 0, player);
                }
            }

        } else if (player.pickedsonicsword == true) {
            if (timer.statetimer3 > 0) {
                sonicsword = new SonicSword(0, 0, player);
            }

        } else {
            player.hercules_sword = true;
                                      HerculesActionSound("Audio//Hercules - sounds//a3.wav");

        }
    }

    protected void updateCoins() {
        //golden coins
        for(int i=0;i<goldcoin.size();i++){
            goldcoin.get(i).update(player);
        }
    }

    protected void updateCharacters(float dt) {
        creator.getPhill().update(dt);
        for (SecondaryCharacter birds : creator.getBirds()) {
            birds.update(dt);
        }
        for (SecondaryCharacter deers : creator.getDeers()) {
            deers.update(dt);
        }
        for (SecondaryCharacter apes : creator.getApes()) {
            apes.update(dt);
        }
        for (Flame flame : staticGraphics.getFlames()) {
            flame.update(dt);
        }
        for (Enemy enemy : creator.getBabyDragons()) {
            enemy.update(dt);
        }
    }

    protected void updateSwords() {
        staticlightiningsword.update();
        lightningsword.update();
        staticfireballsword.update();
        leftfirball.update();
        rightfireball.update();
        staticsonicsword.update();
        sonicsword.update();
    }

    protected void updateFireBalls() {
      for(int i=0;i<filreball.size();i++){
            filreball.get(i).update();
        }
    }
    
    protected void  HerculesActionSound (String MusicPath){
         m = Main.manager.get(MusicPath,Music.class);
                m.setVolume(Main.vol); 
                m.play();
    }
     
    protected boolean gameOver() {
        if (player.currentState == Hercules.State.die && player.getStateTimer() > 1.4) {
            GameOver.setVolume(Main.vol);
            GameOver.play();
            return true;
        }
        return false;
    }
     
    protected void cameraScoop(float startX, float endX){
         if (player.body.getPosition().x>startX/Main.PPM  && player.body.getPosition().x<endX/Main.PPM)
            gameCam.position.x = player .body.getPosition().x ;
         
        if (player.body.getPosition().y<470/Main.PPM )
            gameCam.position.y = player .body.getPosition().y+255/Main.PPM ;
     }
    /*End Some Helping Methods*/
    
    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {
    }
    
    protected void renderCharacters() {
        creator.getPhill().draw(game.batch);
        for (SecondaryCharacter bird : creator.getBirds()) {
            bird.draw(game.batch);
        }
        for (SecondaryCharacter deer : creator.getDeers()) {
            deer.draw(game.batch);
        }
        for (SecondaryCharacter apes : creator.getApes()) {
            apes.draw(game.batch);
        }
        for (Flame flame : staticGraphics.getFlames()) {
            flame.draw(game.batch);
        }
        for (Enemy enemy : creator.getBabyDragons()) {
            enemy.draw(game.batch);
        }
    }

    protected void renderSwords() {
        staticlightiningsword.draw(game.batch);
        lightningsword.draw(game.batch);
        staticfireballsword.draw(game.batch);
        leftfirball.draw(game.batch);
        rightfireball.draw(game.batch);
        staticsonicsword.draw(game.batch);
        sonicsword.leftsonic.draw(game.batch);
        sonicsword.rightsonic.draw(game.batch);
        sonicsword.upsonic.draw(game.batch);
    }

    protected void renderCoins() {
       //Golden Coins
       for(int i=0;i<goldcoin.size();i++){
            goldcoin.get(i).draw(game.batch);
       }
    }
    
    protected void renderFireball(){
       //Cannons fireballs 
        for(int i=0;i<filreball.size();i++){
            filreball.get(i).draw(game.batch);
        }
    }
    
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
