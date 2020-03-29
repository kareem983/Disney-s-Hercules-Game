
package Screens;

import Scenes.Hud;
import Sprites.Hercules;
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
import com.badlogic.gdx.utils.viewport.FitViewport;

/* LEVEL ONE SCREEN  */
public class PlayScreen implements Screen{  
    
    private Main game;
    
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    
    private TextureAtlas atlas;
    private Flame flame;
    
    
    private Hercules player;
    
    public PlayScreen(Main game){
        
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(game.WIDTH / Main.PPM, game.HEIGHT / Main.PPM,  gameCam);    // 800, 480
      
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Map\\HerculesMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        
        atlas = new TextureAtlas("Sprites\\Main\\Flame.atlas");
        flame = new Flame(this);
    }
    public TextureAtlas getAtlas(){
        return atlas;
    }
    @Override
    public void show() {
        
    }
    public void handleInput(float dt){
        if (Gdx.input.isTouched()){
            gameCam.position.x += 400 * dt;
        }
        else if (Gdx.input.isKeyPressed(Keys.SPACE)){
            gameCam.position.x -= 400 * dt;
        }
    }
     public void update(float dt){
         handleInput(dt);
//         world.step(1/60f, 6, 10);        //     neccessary For GameSpeed Issues
         flame.update(dt);
         
        
        gameCam.update();
       renderer.setView(gameCam);
    }
    
    @Override
    public void render(float delta) {
       update(delta);
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
       renderer.render();
   
       game.batch.setProjectionMatrix(gameCam.combined);
       game.batch.begin();
       game.batch.draw(flame.getFrame(delta), 350/Main.PPM, 80/Main.PPM);
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
       /*
        map.dispose();
       renderer.dispose();
       world.dispose();
       b2dr.dispose();
       hud.dispose();
        */
    }
    
}
