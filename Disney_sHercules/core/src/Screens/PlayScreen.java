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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.util.LinkedList;
import java.util.List;

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
   public Swords staticfireballsword,leftfirball,rightfireball;
    public Swords staticsonicsword, sonicsword, lightningsword;
    private Hercules player; 
    public TallPiller piller;
    //Coins
    private GoldenCoin gold1,gold2,gold3,gold4,gold5,gold6;
    private SilverCoin silver1,silver2,silver3;
    //Fireball Cannons
    private Cannons FireBall1,FireBall2,FireBall3,FireBall4;
    //protected Shield
    private ProtectedShield Shield;
    
    
    
        List<FeatherSack> featherList ;
        List<MovingFeather> MovingfeatherList ;
        List<Block> BlockList ;
    Hill hill;
    Juice juice;
    int  normalcounter;
    boolean c ,v  ;
    public Timer timer;
    public Sandal sandal;
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
        leftfirball=rightfireball=staticfireballsword;
        staticsonicsword = new StaticSonicSword(9000f / Main.PPM, 300f / Main.PPM, player);
        sonicsword = new SonicSword(0, 0, player);
                lightningsword =staticsonicsword;
        hud=new Hud(player,game.batch);
        
        piller = new TallPiller(world,this , 6660 , 50 );
      
        /*Coins*/
        gold1=new GoldenCoin (this,1050,250,player,hud); 
        gold2=new GoldenCoin (this,1150,250,player,hud);
        gold3=new GoldenCoin (this,1250,250,player,hud);
        gold4=new GoldenCoin (this,18520,750,player,hud);
        gold5=new GoldenCoin (this,18670,750,player,hud);
        gold6=new GoldenCoin (this,18820,750,player,hud);
        silver1=new SilverCoin (this,4380,400,player,hud);
        silver2=new SilverCoin (this,4530,350,player,hud);
        silver3=new SilverCoin (this,4680,300,player,hud);
       
        //Cannons Fireballs
        FireBall1=new Cannons(800,950,player,hud);
        FireBall2=new Cannons(8000,950,player,hud);
        FireBall3=new Cannons(11000,950,player,hud);
        FireBall4=new Cannons(16500,950,player,hud);
    
        //protected Shield
        Shield=new ProtectedShield(player,hud);
        
        
        
     featherList = new LinkedList<>();
     MovingfeatherList = new LinkedList<>();
     BlockList = new LinkedList<>();
        define_featherSack();
        define_MovingfeatherSack();

        define_Blocks();


        hill =  new Hill(world , this , this.map);
        juice = new Juice(world,this,2500,100);
        normalcounter  = 0;
        c = v = false;
        timer = new Timer(player, hud);
        sandal = new Sandal(new Texture("sprites\\sandal.png"), 6000 / Main.PPM, 300 / Main.PPM, player);
    } 
    public void getbaby(B2WorldCreator creator) {
        for (int i = 0; i < creator.getBabyDragons().size; i++) {
            Rectangle recbaby = creator.getBabyDragons().get(i).getBoundingRectangle();
            if (recbaby.overlaps(lightningsword.getBoundingRectangle())) {
             if(lightningsword.Finish()==false)  creator.getBabyDragons().get(i).Stap();
            }
            else if(recbaby.overlaps(rightfireball.getBoundingRectangle()))
            {
             if(rightfireball.Finish()==false)  creator.getBabyDragons().get(i).Stap();
             
            }
             else if(recbaby.overlaps(leftfirball.getBoundingRectangle()))
            {
             if(leftfirball.Finish()==false)  creator.getBabyDragons().get(i).Stap();
             
            }
            else if (recbaby.overlaps(sonicsword.rightsonic.getBoundingRectangle())||recbaby.overlaps(sonicsword.leftsonic.getBoundingRectangle())||recbaby.overlaps(sonicsword.upsonic.getBoundingRectangle()))
            {
             if(sonicsword.Finish()==false)  creator.getBabyDragons().get(i).Stap();
            }
        }
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
             if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && player.b2body.getPosition().y <= player.HerculesMaxSpeedHigh )
                  player.b2body.applyLinearImpulse(new Vector2(0 ,2.5f), player.b2body.getWorldCenter(), true);
             if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
                   player.b2body.applyLinearImpulse(new Vector2(0 ,-2.5f), player.b2body.getWorldCenter(), true);

             if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= player.HerculesMaxSpeed)
                  player.b2body.applyForceToCenter(new Vector2(3, 0), true);
             if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -1 * player.HerculesMaxSpeed)
                  player.b2body.applyForceToCenter(new Vector2(-3, 0), true);
             if (Gdx.input.isKeyPressed(Input.Keys.C))
             {
                 player.hercules_push = true;
                 c = true;
                 handleTallPillarCrash();
             }
             
             
             if (Gdx.input.isKeyPressed(Input.Keys.V))
             {
                 v = true;
                 player.hercules_Smallpush = true;
                 handleTallPillarCrash();
             }
            
             if (Gdx.input.isKeyPressed(Input.Keys.Z))
                      player.hercules_Drink = true ;
             if (Gdx.input.isKeyPressed(Input.Keys.D))
                      player.hercules_Die = true ;
              if (Gdx.input.isKeyPressed(Input.Keys.X)) 
                     handleSword();
              if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
                  v=true;
                  player.hercules_sword2 = true;
                  handleTallPillarCrash();
              }
             if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
                      System.exit(0) ;

          handleJuice();
          v = c = false;
    }
      private void handleSword(){
         if (player.pickedlightsword == true) {
            if (timer.statetimer < 10) {
                player.hercules_sword = true;
           lightningsword = new LightiningSword(0, 0, player);

            }

        } else if (player.pickedfireballsword == true) {

            if (timer.statetimer < 8) {
                if (player.isRunningRight()) {
                    rightfireball = new RightFireBallSword(0, 0, player);
                } else {
                    leftfirball= new LeftFireBallSword(0, 0, player);
                }
            }

        } else if (player.pickedsonicsword == true) {
            if (timer.statetimer < 6) {
                sonicsword = new SonicSword(0, 0, player);
            }

        } else {
            player.hercules_sword = true;
        }
      }
    private void handleTallPillarCrash(){

        //Allow Crash Animation to start-------------------------------------
        if (piller.crashed == false) {
            if (  piller.getBoundingRectangle().overlaps(player.getBoundingRectangle()) )
            {
                if (c == true || (v == true && normalcounter == 3) ) {
                    piller.STATE = true;

                    world.destroyBody(piller.b2body);
                    piller.crashed = true;
                }

                else if (v)
                {
                    normalcounter++;
                    v=false;
                }

            }

        }
    }
    private  void handleJuice() {
        if (juice.rect.overlaps(player.getBoundingRectangle())) {
            if (!juice.Catch) {
                juice.Catch = true;
                player.hercules_Drink = true;
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
        getbaby(creator);
        sandal.update();
        timer.update();
        player.update(dt); 
        staticlightiningsword.update();
        lightningsword.update();
        staticfireballsword.update();
        leftfirball.update();
        rightfireball.update();
        staticsonicsword.update();
        sonicsword.update();
        
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
         juice.update(dt);
        Shield.update();
        
             for(int i=0 ;i<featherList.size() ; i++)
            featherList.get(i).update(dt);
          
          for(int i=0 ;i<MovingfeatherList.size() ; i++)
            MovingfeatherList.get(i).update(dt);
         
          for(int i=0;i<BlockList.size();i++){
                BlockList.get(i).update(dt);
                BlockList.get(i).Block_Moving(4);
            }
            
      for(int i=0;i<BlockList.size();i++) {
          if (BlockList.get(i).blockDown ==false ){
              if(BlockList.get(i).blockFinish == false){
                  world.destroyBody(BlockList.get(i).b2body);
                  BlockList.get(i).blockFinish =true;
              }
              
          }
      }

     
                
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
        sandal.draw(game.batch);
       hud.s.draw(game.batch);
       hud.s1[3].draw(game.batch);
       hud.s2[0].draw(game.batch);
       player.draw(game.batch);
       piller.draw(game.batch);
       staticlightiningsword.draw(game.batch);
        lightningsword.draw(game.batch);
        staticfireballsword.draw(game.batch);
        leftfirball.draw(game.batch);
        rightfireball.draw(game.batch);
        staticsonicsword.draw(game.batch);
        sonicsword.leftsonic.draw(game.batch);
        sonicsword.rightsonic.draw(game.batch);
        sonicsword.upsonic.draw(game.batch);
       silver1.draw(game.batch);
       silver2.draw(game.batch);
       silver3.draw(game.batch);
       gold1.draw(game.batch);
       gold2.draw(game.batch);
       gold3.draw(game.batch);
       gold4.draw(game.batch);
       gold5.draw(game.batch);
       gold6.draw(game.batch);
        juice.draw(game.batch);
       FireBall1.draw(game.batch);
       FireBall2.draw(game.batch);
       FireBall3.draw(game.batch);
       FireBall4.draw(game.batch);
       
       Shield.draw(game.batch);
       
       for(int i=0 ;i<featherList.size() ; i++){
                  
          if( featherList.get(i).featherCollsoin(player) == 1) {
              featherList.remove(i);
              FeatherSack.Num_of_feather_Destroyed++ ;
          }
          
          
           else{ 
              
              if( featherList.get(i).featherCollsoin(player) == 2) { hud.hit(); featherList.get(i).draw(game.batch);  }
              else    featherList.get(i).draw(game.batch);
              } 
              
              
              }
                     
            for(int i=0 ;i<MovingfeatherList.size() ; i++){
         if( MovingfeatherList.get(i).order==1        ) MovingfeatherList.get(i).Rope.draw(game.batch);
                                MovingfeatherList.get(i).featherMoving(player);
          if( MovingfeatherList.get(i).featherCollsoin(player) == 2) { hud.hit(); MovingfeatherList.get(i).draw(game.batch);  }
          
          else{ 
                if( MovingfeatherList.get(i).featherCollsoin(player) == 1)  MovingfeatherList.remove(i);

              else    MovingfeatherList.get(i).draw(game.batch);
              } 
              
              }
         
              for(int i=0;i<BlockList.size();i++) BlockList.get(i).draw(game.batch);
              
       
       
       game.batch.end();
       
       game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
       hud.stage.draw();
    }
    
    public void define_featherSack(){
            FeatherSack feather1 , feather2 , feather3 ,feather4 ,feather5   ;
               feather1 =new FeatherSack( 1900/Main.PPM , 320/Main.PPM ,world ,this);
                feather2 =new FeatherSack( 2160/Main.PPM , 350/Main.PPM ,world ,this);
                feather3 =new FeatherSack( 2690/Main.PPM , 350/Main.PPM ,world ,this);
                feather4 =new FeatherSack( 2425/Main.PPM , 320/Main.PPM ,world ,this);

                featherList.add(feather1);
                featherList.add(feather2);
                featherList.add(feather3);
                featherList.add(feather4);
    }
    public void define_MovingfeatherSack(){
        MovingFeather m1 =new MovingFeather(5200/Main.PPM, 550/Main.PPM, world, this,1);
        MovingFeather m2 =new MovingFeather(5600/Main.PPM, 550/Main.PPM, world, this,1);
        MovingFeather m3 =new MovingFeather(8000/Main.PPM, 550/Main.PPM, world, this,1);
        MovingFeather m4 =new MovingFeather(8400/Main.PPM, 550/Main.PPM, world, this,1);
        MovingFeather m5 =new MovingFeather(9800/Main.PPM, 320/Main.PPM, world, this,2);
        MovingfeatherList.add(m1);
        MovingfeatherList.add(m2);
        MovingfeatherList.add(m3);
        MovingfeatherList.add(m4);
        MovingfeatherList.add(m5);
    }

    public void define_Blocks(){
        Block b1 = new Block(3100 ,60 , world); 
        BlockList.add(b1);
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