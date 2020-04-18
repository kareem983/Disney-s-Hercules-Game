package Screens;

import Sprites.*;
import Scenes.Hud;
import HealthAttacker.*;
import StaticGraphics.*;
import Tools.*;
import com.badlogic.gdx.*;
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

/* LEVEL ONE SCREEN  */
public class PlayScreen implements Screen{  
    //  Some Essential Variables
    private Main game;
    public float swordTimer=0;
    
    // Basic PlayScreen Variables
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    
    //Tiled Map Variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    //Atlas
    private TextureAtlas FlameAtlas;
    private TextureAtlas TotalAtlas;
    private TextureAtlas atlas_run; 
    private TextureAtlas atlas_jumb;
    private TextureAtlas atlas_pillar;
    
    //Box2d Variables
    private World world;
    private Box2DDebugRenderer debuger;
    private B2WorldCreator creator;
    public  WorldContactListener worldContactListener;
    
    //Sprites
    private DrawClass staticGraphics;
    public Swords staticlightiningsword;
    public Swords staticfireballsword;
    private Hercules player; 
    public TallPiller piller;
    //Coins
    private GoldenCoin gold1,gold2,gold3,gold4,gold5,gold6;
    private SilverCoin silver1,silver2,silver3;
    //Fireball Cannons
    private Cannons FireBall1,FireBall2,FireBall3,FireBall4;
    //protected Shield
    private ProtectedShield Shield;
    
    public PlayScreen(Main game){
        
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(game.WIDTH / Main.PPM, game.HEIGHT / Main.PPM,  gameCam);    // 800, 480
      
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Map\\HerculesMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 /  Main.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2 , 0);
        
        FlameAtlas = new TextureAtlas("Sprites\\Main\\Flame.atlas");
        TotalAtlas = new TextureAtlas("Sprites\\Main\\Total.atlas");
        staticGraphics = new DrawClass(this);
        
        //create our Box2D world, setting no gravity in X, -80 gravity in Y, and allow bodies to sleep
        world = new World(new Vector2(0, -10f), true);
        world.setContactListener(new WorldContactListener());
        //allows for debug lines of our box2d world.
        debuger = new Box2DDebugRenderer();
        creator = new B2WorldCreator(this);
        atlas_run = new TextureAtlas("Sprites\\Run600_75.pack") ;
        atlas_jumb = new TextureAtlas("Sprites\\H_Jump.pack") ;
        atlas_pillar = new TextureAtlas("Sprites\\tallpillar.pack");
        
        player= new Hercules(world , this);
        staticlightiningsword = new StaticLightSword(15555f / Main.PPM, 300f / Main.PPM, player);
        staticfireballsword = new StaticFireBallSword(10555f / Main.PPM, 300f / Main.PPM, player); 
        hud=new Hud(player,game.batch);
        
        piller = new TallPiller(world,this , 6660 , 50 );
      
        /*Coins*/
        gold1=new GoldenCoin (this,3050,300,player,hud); 
        gold2=new GoldenCoin (this,3150,300,player,hud);
        gold3=new GoldenCoin (this,3250,300,player,hud);
        gold4=new GoldenCoin (this,18520,690,player,hud);
        gold5=new GoldenCoin (this,18670,690,player,hud);
        gold6=new GoldenCoin (this,18820,690,player,hud);
        silver1=new SilverCoin (this,4380,400,player,hud);
        silver2=new SilverCoin (this,4530,350,player,hud);
        silver3=new SilverCoin (this,4680,300,player,hud);
       
        //Cannons Fireballs
        FireBall1=new Cannons(1000,950,player,hud);
        FireBall2=new Cannons(8000,950,player,hud);
        FireBall3=new Cannons(11000,950,player,hud);
        FireBall4=new Cannons(16500,950,player,hud);
    
        //protected Shield
        Shield=new ProtectedShield(player,hud);
        
    }
    public TextureAtlas getAtlas(){
        return FlameAtlas;
    }
    public TextureAtlas getTotalAtlas(){
        return TotalAtlas;
    }
    public TextureAtlas getAtlas_pillar(){return atlas_pillar;}
    
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
                      handleTallPillarCrash();
             if (Gdx.input.isKeyPressed(Input.Keys.Z))
                      player.hercules_Drink = true ;
             if (Gdx.input.isKeyPressed(Input.Keys.D))
                      player.hercules_Die = true ;
              if (Gdx.input.isKeyPressed(Input.Keys.X)) 
                     handleSword();
             if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
                      System.exit(0) ;
    }
      private void handleSword(){
          swordTimer += Gdx.graphics.getDeltaTime();
                    if (player.pickedlightsword==true) {
                       if(swordTimer < 2){
                        player.hercules_sword = true;
                        staticlightiningsword = new LightiningSword(0, 0, player);
                       } 
                       else
                       {
                           player.pickedlightsword=false;
                           swordTimer=0;
                       }
                    }
                    else if(player.pickedfireballsword==true)
                    {

                        if(swordTimer < 2){
                        if (player.isRunningRight()) {
                            staticfireballsword= new RightFireBallSword(0, 0, player);
                        } else {
                            staticfireballsword = new LeftFireBallSword(0, 0, player);
                        }}
                        else{
                            player.pickedfireballsword=false;
                            swordTimer=0;
                        }
                    }
                    else{
                        player.hercules_sword = true;
                    }
      }
      private void handleTallPillarCrash(){
          player.hercules_push = true;
                   //Allow Crash Animation to start-------------------------------------
            if (piller.crashed == false) {
                System.out.println((piller.b2body.getPosition().x-player.b2body.getPosition().x));
            if ( /*player.b2body.getPosition().x == piller.pposx*/  (piller.b2body.getPosition().x-player.b2body.getPosition().x) <= 0.18631172)
            {
                    piller.STATE = true;

                    world.destroyBody(piller.b2body);
                    piller.crashed = true;
             }
         }
      }
      
      
      
      
     public void update(float dt){
         handleInput(dt);
         hud.update();
         world.step(1/60f, 6, 2);  
        
        for (Flame flame : staticGraphics.getFlames())
            flame.update(dt);
        for(Enemy enemy : creator.getBabyDragons())
            enemy.update(dt);
        
        player.update(dt); 
        staticlightiningsword.update();
        staticfireballsword.update();
        
        if (player.b2body.getPosition().x>1000/Main.PPM && player.b2body.getPosition().x<23000/Main.PPM)
            gameCam.position.x = player .b2body.getPosition().x ;
        if (player.b2body.getPosition().y<470/Main.PPM )
            gameCam.position.y = player .b2body.getPosition().y+255/Main.PPM ;
        
        piller.update(dt);
        
        silver1.update();
        silver2.update();
        silver3.update();
        gold1.update();
        gold2.update();
        gold3.update();
        gold4.update();
        gold5.update();
        gold6.update(); 
        
        FireBall1.update();
        FireBall2.update();
        FireBall3.update();
        FireBall4.update();
        
        Shield.update();
                
        gameCam.update();
        renderer.setView(gameCam);
    }
    
    @Override
    public void render(float delta) {
       update(delta);
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
       renderer.render();
       //debuger.render(world,gameCam.combined);
       
       game.batch.setProjectionMatrix(gameCam.combined);
       game.batch.begin();
       for (Flame flame : staticGraphics.getFlames())
            flame.draw(game.batch);
        for(Enemy enemy : creator.getBabyDragons())
            enemy.draw(game.batch);
        
       hud.s.draw(game.batch);
       hud.s1[3].draw(game.batch);
       hud.s2[0].draw(game.batch);
       player.draw(game.batch);
       piller.draw(game.batch);
       staticlightiningsword.draw(game.batch);
       staticfireballsword.draw(game.batch);
       
       silver1.draw(game.batch);
       silver2.draw(game.batch);
       silver3.draw(game.batch);
       gold1.draw(game.batch);
       gold2.draw(game.batch);
       gold3.draw(game.batch);
       gold4.draw(game.batch);
       gold5.draw(game.batch);
       gold6.draw(game.batch);
      
       FireBall1.draw(game.batch);
       FireBall2.draw(game.batch);
       FireBall3.draw(game.batch);
       FireBall4.draw(game.batch);
       
       Shield.draw(game.batch);
       
       game.batch.end();
       
       game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
       hud.stage.draw();
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
       debuger.dispose();
       hud.dispose();
    }
    
}
