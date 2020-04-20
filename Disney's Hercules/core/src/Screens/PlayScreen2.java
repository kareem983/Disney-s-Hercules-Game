package Screens;

import MovingObjects.Hercules;
import Sprites.*;
import Scenes.*;
import HealthAttacker.*;
import MovingObjects.Phill;
import MovingObjects.SecondaryCharacter;
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
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/* LEVEL TWO SCREEN  */
public class PlayScreen2 implements Screen{  
    //  Some Essential Variables
    private Main game;
    
    //Basic PlayScreen Variables
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    
     //Atlas
    private TextureAtlas FlameAtlas;
    private TextureAtlas TotalAtlas;
    private TextureAtlas atlas_run; 
    private TextureAtlas atlas_jumb;
    private TextureAtlas atlas_pillar;
    
    //Tiled Map Variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    //Box2d Variables
    private World world;
    private Box2DDebugRenderer debuger;
    private B2WorldCreator creator;
    public  WorldContactListener worldContactListener;
    
    //Sprites
    private DrawClass staticGraphics;
    private Hercules player;
    
    public PlayScreen2(Main game){
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(game.WIDTH / Main.PPM, game.HEIGHT / Main.PPM,  gameCam);
      
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Maps\\Level Two\\HerculesMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 /  Main.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2 , 0);
        
        FlameAtlas = new TextureAtlas("Sprites\\Main\\Flame.atlas");
        
        world = new World(new Vector2(0, -10f), true);
        debuger = new Box2DDebugRenderer();
        HerculesGround();
        atlas_run = new TextureAtlas("Sprites\\Run600_75.pack") ;
        atlas_jumb = new TextureAtlas("Sprites\\H_Jump.pack") ;
        atlas_pillar = new TextureAtlas("Sprites\\tallpillar.pack");
//        player= new Hercules(world , this);
        
        //allows for debug lines of our box2d world.
        debuger = new Box2DDebugRenderer();
       
    }
    public TextureAtlas getFlameAtlas(){
        return FlameAtlas;
    }
    public TextureAtlas getAtlas_Run(){
        return atlas_run;
    }
    public TextureAtlas getAtlas_jumb(){
        return atlas_jumb;
    }
    public TiledMap getMap() {
        return map;
    }
    public World getWorld() {
        return world;
    }
    private void HerculesGround(){
        Body body;
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape() ;
        FixtureDef fdef = new FixtureDef();
        for(MapObject object : map.getLayers().get("Hercules Ground").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle() ;
            
                bdef.type = BodyDef.BodyType.StaticBody ;
                bdef.position.set((rec.getX() + rec.getWidth() /2) / Main.PPM  , (rec.getY()+ rec.getHeight()/2) / Main.PPM  ) ;
                body =world.createBody(bdef) ;
                shape.setAsBox( (rec.getWidth()/2 ) / Main.PPM , (rec.getHeight() /2) / Main.PPM );
                fdef.filter.categoryBits = Main.GROUND_BIT;
                fdef.shape =shape ;
                body.createFixture(fdef ) ;
             }   
         }
    
      private void handleInput(){
             if (Gdx.input.isKeyPressed(Input.Keys.UP))
                  gameCam.position.y += .03 ;
             if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
                  gameCam.position.y -= .03;
             if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                  gameCam.position.x += .03 ;
             if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                  gameCam.position.x -= .03 ;

             if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
                      System.exit(0) ;
      
             if (Gdx.input.isKeyJustPressed(Input.Keys.W) && player.b2body.getLinearVelocity().y <= player.HerculesMaxSpeedHigh )
                  player.b2body.applyLinearImpulse(new Vector2(0 ,2.5f), player.b2body.getWorldCenter(), true);
             if (Gdx.input.isKeyJustPressed(Input.Keys.S))
                   player.b2body.applyLinearImpulse(new Vector2(0 ,-2.5f), player.b2body.getWorldCenter(), true);

             if (Gdx.input.isKeyPressed(Input.Keys.D) && player.b2body.getLinearVelocity().x <= player.HerculesMaxSpeed)
                  player.b2body.applyForceToCenter(new Vector2(3, 0), true);
             if (Gdx.input.isKeyPressed(Input.Keys.A) && player.b2body.getLinearVelocity().x >= -1 * player.HerculesMaxSpeed)
                  player.b2body.applyForceToCenter(new Vector2(-3, 0), true);
    
    }
      
     public void update(float dt){
        handleInput();
        world.step(1/60f, 6, 2);  
//        player.update(dt); 
/*        
        if (player.b2body.getPosition().x>1000/Main.PPM && player.b2body.getPosition().x<23000/Main.PPM)
            gameCam.position.x = player .b2body.getPosition().x ;
        if (player.b2body.getPosition().y<470/Main.PPM )
        gameCam.position.y = player .b2body.getPosition().y+255/Main.PPM ;
    */    
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
//       player.draw(game.batch);
       game.batch.end();
        
    }
    
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }
    
    @Override
    public void show() {
        
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
       /*map.dispose();
       renderer.dispose();
       world.dispose();
       debuger.dispose();
       hud.dispose();*/
    }
    
}