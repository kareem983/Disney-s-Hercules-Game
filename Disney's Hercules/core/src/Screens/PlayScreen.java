
package Screens;

import Scenes.Hud;
import Sprites.GoldenCoin;
import Sprites.Hercules;
import Sprites.SilverCoin;
import StaticGraphics.DrawClass;
import StaticGraphics.Flame;
import Tools.B2WorldCreator;
import Tools.WorldContactListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
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
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/* LEVEL ONE SCREEN  */
public class PlayScreen implements Screen{  
    
    private Main game;
    
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    private TextureAtlas FlameAtlas;
    
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;
    
    private DrawClass staticGraphics;
    private Hercules player;
    private TextureAtlas atlas_run; 
    private TextureAtlas atlas_jumb;
    
    private GoldenCoin gold1,gold2,gold3,gold4,gold5,gold6;
    private SilverCoin silver1,silver2,silver3;
    
    
    
    public PlayScreen(Main game){
        
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(game.WIDTH / Main.PPM, game.HEIGHT / Main.PPM,  gameCam);    // 800, 480
      
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Map\\HerculesMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 /  Main.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2 , 0);
        
        FlameAtlas = new TextureAtlas("Sprites\\Main\\Flame.atlas");
        staticGraphics = new DrawClass(this);
        
        //create our Box2D world, setting no gravity in X, -80 gravity in Y, and allow bodies to sleep
        world = new World(new Vector2(0, -10f), true);
        //allows for debug lines of our box2d world.
        b2dr = new Box2DDebugRenderer();
        creator = new B2WorldCreator(this);
            atlas_run = new TextureAtlas("Sprites\\Run600_75.pack") ;
            atlas_jumb = new TextureAtlas("Sprites\\H_Jump.pack") ;
            
        player= new Hercules(world , this);
        
        /*Coins*/
        gold1=new GoldenCoin (world,this,3050,300); 
        gold2=new GoldenCoin (world,this,3150,300);
        gold3=new GoldenCoin (world,this,3250,300);
        gold4=new GoldenCoin (world,this,18520,690);
        gold5=new GoldenCoin (world,this,18670,690);
        gold6=new GoldenCoin (world,this,18820,690);
        
        silver1=new SilverCoin (world,this,4380,500);
        silver2=new SilverCoin (world,this,4530,450);
        silver3=new SilverCoin (world,this,4680,400);
    }
    public TextureAtlas getAtlas(){
        return FlameAtlas;
    }
    public TextureAtlas getAtlas_Run(){
        return atlas_run;
    }
    public TextureAtlas getAtlas_jumb(){
        return atlas_jumb;
    }
   
    public World getWorld() {
        return world;
    }

    public TiledMap getMap() {
        return map;
    }
    @Override
    public void show() {
        
    }
      public void handleInput(float dt){
      
        //control our player using immediate impulses
             if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.b2body.getLinearVelocity().y <= player.HerculesMaxSpeedHigh )
                  player.b2body.applyLinearImpulse(new Vector2(0 ,2.5f), player.b2body.getWorldCenter(), true);
             if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
                   player.b2body.applyLinearImpulse(new Vector2(0 ,-2.5f), player.b2body.getWorldCenter(), true);

             if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= player.HerculesMaxSpeed)
                  player.b2body.applyForceToCenter(new Vector2(3, 0), true);
             if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -1 * player.HerculesMaxSpeed)
                  player.b2body.applyForceToCenter(new Vector2(-3, 0), true);
             if (Gdx.input.isKeyPressed(Input.Keys.C))
                      player.hercules_push = true ;
             if (Gdx.input.isKeyPressed(Input.Keys.X))
                      player.hercules_sword = true ;
             if (Gdx.input.isKeyPressed(Input.Keys.Z))
                      player.hercules_Drink = true ;
             if (Gdx.input.isKeyPressed(Input.Keys.D))
                      player.hercules_Die = true ;
                     
    }
     public void update(float dt){
         handleInput(dt); 
        world.step(1/60f, 6, 2);        //     neccessary For GameSpeed Issues
        for (Flame flame : staticGraphics.getFlames())
            flame.update(dt);
        
        player.update(dt);
        gameCam.position.x = player .b2body.getPosition().x ;
       // gameCam.position.y = player .b2body.getPosition().y+210/Main.PPM ;
       silver1.update(dt);
         silver2.update(dt);
         silver3.update(dt);
        
        
         gold1.update(dt);
         gold2.update(dt);
         gold3.update(dt);
         gold4.update(dt);
         gold5.update(dt);
         gold6.update(dt); 
       
       
        gameCam.update();
       renderer.setView(gameCam);
    }
    
    @Override
    public void render(float delta) {
       update(delta);
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
       renderer.render();
       //b2dr.render(world,gameCam.combined);
       
        game.batch.setProjectionMatrix(gameCam.combined);
       game.batch.begin();
       player.draw(game.batch);
      for (Flame flame : staticGraphics.getFlames())
            flame.draw(game.batch);
      silver1.draw(game.batch);
       silver2.draw(game.batch);
       silver3.draw(game.batch);

       gold1.draw(game.batch);
       gold2.draw(game.batch);
       gold3.draw(game.batch);
       gold4.draw(game.batch);
       gold5.draw(game.batch);
       gold6.draw(game.batch);
       game.batch.end();
    }
    
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
       
       map.dispose();
       renderer.dispose();
       world.dispose();
       b2dr.dispose();
   //    hud.dispose();
       
    }
    
}