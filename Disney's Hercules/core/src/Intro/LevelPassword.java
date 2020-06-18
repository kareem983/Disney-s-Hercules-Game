package Intro;

import Screens.Level1;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LevelPassword implements Screen {

    private Main game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    Meg meg;
    Vases vases;
    private World world;
    private Box2DDebugRenderer b2dr;
    private TextureAtlas atlas , vasatlas;
    Texture stand1, stand2, stand3, stand4;
    public Sprite sprite1, sprite2, sprite3, sprite4;
    boolean p1 =true , p2=true , p3=true , p4=true ;
    public boolean up=false,down=false,success=false;

    Music wrongpassword , pass , morewrongpass , time,start,vaseup,vasedown ;
int counter=0 , prevcounter = 0,tmpCounter=0,wrongCounter=0;
    public LevelPassword(Main game) {

        LoadMusic();
        this.game = game;
        gamecam = new OrthographicCamera();
        gameport = new StretchViewport(1600, 1600, gamecam);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("Intros\\Level Password\\pass pic\\Password_map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);
        atlas = new TextureAtlas("Intros\\Level Password\\meg.pack");
        vasatlas = new TextureAtlas("Intros\\Level Password\\vases.pack");
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();
        meg = new Meg(world, this);
        vases = new Vases(world,this);
        for (MapObject object : map.getLayers().get("Ground").getObjects().getByType(RectangleMapObject.class)) {
            BodyDef bdef = new BodyDef();
            FixtureDef fdef = new FixtureDef();
            PolygonShape shape = new PolygonShape();
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rectangle.getX() + rectangle.getWidth() / 2), (rectangle.getY() + rectangle.getHeight() / 2));
            Body body = world.createBody(bdef);
            shape.setAsBox((rectangle.getWidth() / 2), rectangle.getHeight() / 2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        stand1 = new Texture("Intros\\Level Password\\pass pic//6.png");
        sprite1 = new Sprite(stand1);
        stand2 = new Texture("Intros\\Level Password\\pass pic//6.png");
        sprite2 = new Sprite(stand2);
        stand3 = new Texture("Intros\\Level Password\\pass pic//6.png");
        sprite3 = new Sprite(stand3);
        stand4 = new Texture("Intros\\Level Password\\pass pic//6.png");
        sprite4 = new Sprite(stand4);
        //---------------------------
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public TextureAtlas getVasatlas(){return vasatlas;}
    private void LoadMusic(){

        start = game.manager.get("Audio//Hercules - Voices//Meg//So u have a password with you.wav" ,Music.class);
        start.setLooping(false);
        start.setVolume(0.5f);
        start.play();
        
        vaseup=game.manager.get("Audio//Hercules - Voices//Meg//vase up.mp3" ,Music.class);
        vaseup.setLooping(false);
        vaseup.setVolume(0.5f);
            
        vasedown=game.manager.get("Audio//Hercules - Voices//Meg//vase down.mp3",Music.class);
        vasedown.setLooping(false);
        vasedown.setVolume(0.5f);
            
        pass = game.manager.get("Audio//Hercules - Voices//Meg//Oh we got a winner.wav",Music.class);
        pass.setLooping(false);
        pass.setVolume(0.5f);
                
        wrongpassword = game.manager.get("Audio//Hercules - Voices//Meg//Wrong password.wav",Music.class);
        wrongpassword.setLooping(false);
        wrongpassword.setVolume(0.5f);
        
         time =game.manager.get("Audio//Hercules - Voices//Meg//time to play vases.wav",Music.class);
         time.setLooping(false);
         time.setVolume(0.5f);
    }
    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
        meg.body.setLinearVelocity(100, 0);
                System.out.println(meg.body.getPosition().x);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
                meg.body.setLinearVelocity(-100, 0);

                System.out.println(meg.body.getPosition().x);
                  prevcounter = counter;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            meg.v = true;
            meg.Edit = true;
            meg.definePos();
            if (meg.st1 == true)vases.Counterv1++;
            else if (meg.st2 == true)vases.Counterv2++;
            else if (meg.st3 == true)vases.Counterv3++;
            else if (meg.st4 == true)vases.Counterv4++;
            tmpCounter++;
            vaseup.play();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
        {
            meg.    v = true;
            meg.Edit = true;
            meg.definePos();
            if (meg.st1 == true)vases.Counterv1--;
            if (meg.st2 == true)vases.Counterv2--;
            if (meg.st3 == true)vases.Counterv3--;
            if (meg.st4 == true)vases.Counterv4--;
            tmpCounter++;
            
            vasedown.play();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            if (vases.Counterv1 == 3 && vases.Counterv2 ==2 && vases.Counterv3 == 0 && vases.Counterv4==3 ) {
                success = true;
                
                pass.play();
            }
            else{
                  wrongCounter++;
                  if (wrongCounter< 3) {
                      
                      wrongpassword.play();
                  }
                  else if (wrongCounter<6)
                  {
                      wrongpassword = game.manager.get("Audio//Hercules - Voices//Meg//More Wrong Password.wav",Music.class);

                      wrongpassword.setLooping(false);
                      wrongpassword.setVolume(0.5f);
                      wrongpassword.play();
                  }
                     else {
                         wrongCounter=0;
                      wrongpassword = game.manager.get("Audio//Hercules - Voices//Meg//Dont waste my time.wav",Music.class);
                      wrongpassword.setLooping(false);
                      wrongpassword.setVolume(0.5f);
                      wrongpassword.play();
                  }
            }
        }
    }

    public void update(float dt) {
     if (tmpCounter == 10) {
         tmpCounter = 0;
        
         time.play();
     }
        if (!sprite4.getBoundingRectangle().overlaps(meg.getBoundingRectangle()))p4=true;
        if ( !sprite3.getBoundingRectangle().overlaps(meg.getBoundingRectangle()))p3=true;
        if( !sprite2.getBoundingRectangle().overlaps(meg.getBoundingRectangle()))p2=true;
        if( !sprite1.getBoundingRectangle().overlaps(meg.getBoundingRectangle()) ) {p1 =true;}
        handleInput(dt);
        world.step(1 / 60f, 6, 2);
        gamecam.update();
        renderer.setView(gamecam);
        meg.update(dt);
       counter +=  Gdx.graphics.getDeltaTime();
        if (new Rectangle( 570-140 , 40, 1, sprite2.getHeight()).overlaps(new Rectangle(meg.body.getPosition().x , meg.body.getPosition().y , 1,200))){
            meg.body.setLinearVelocity(0, 0);

            p1 = false;
        }
        else  if (new Rectangle( 570+120 , 40, 1, sprite2.getHeight()).overlaps(new Rectangle(meg.body.getPosition().x , meg.body.getPosition().y , 1,200))){

            meg.body.setLinearVelocity(0, 0);
            p1 = false;


        }
        else  if (new Rectangle( 190-140 , 40, 1, sprite1.getHeight()).overlaps(new Rectangle(meg.body.getPosition().x , meg.body.getPosition().y , 1,200))){

            meg.body.setLinearVelocity(0, 0);
            p1 = false;


        }
        else  if (new Rectangle( 190+120 , 40, 1, sprite1.getHeight()).overlaps(new Rectangle(meg.body.getPosition().x , meg.body.getPosition().y , 1,200))){

            meg.body.setLinearVelocity(0, 0);
            p1 = false;


        }
        else  if (new Rectangle( 910-140 , 40, 1, sprite3.getHeight()).overlaps(new Rectangle(meg.body.getPosition().x , meg.body.getPosition().y , 1,200))){

            meg.body.setLinearVelocity(0, 0);
            p1 = false;


        }
        else  if (new Rectangle( 910+120 , 40, 1, sprite3.getHeight()).overlaps(new Rectangle(meg.body.getPosition().x , meg.body.getPosition().y , 1,200))){

            meg.body.setLinearVelocity(0, 0);
            p1 = false;


        }
        else  if (new Rectangle( 1240-140 , 40, 1, sprite4.getHeight()).overlaps(new Rectangle(meg.body.getPosition().x , meg.body.getPosition().y , 1,200))){

            meg.body.setLinearVelocity(0, 0);
            p1 = false;


        }
        else if (new Rectangle( 1240+150 , 40, 1, sprite4.getHeight()).overlaps(new Rectangle(meg.body.getPosition().x , meg.body.getPosition().y , 1,200))){

            meg.body.setLinearVelocity(0, 0);
            p1 = false;


        }

        vases.update(dt);
}
    @Override
    public void render(float delta) {
        update(delta);
     Gdx.gl.glClearColor(0,0,0,1);
     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
     renderer.render();
     game.batch.setProjectionMatrix(gamecam.combined);
     game.batch.begin();
     game.batch.draw(stand1 , 190 , 70);
        game.batch.draw(stand2 , 570 , 70);
        game.batch.draw(stand3 ,  910, 70);
        game.batch.draw(stand4 , 1260 , 70);

        vases.s1.draw(game.batch);
        vases.s2.draw(game.batch);
        vases.s3.draw(game.batch);
        vases.s4.draw(game.batch);
        meg.draw(game.batch );
     game.batch.end();
     
     if (!pass.isPlaying()  && success){
         game.setScreen(new Level1(game));
         this.dispose();
     }
     
     if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
         start.stop();vaseup.stop();vasedown.stop();
         pass.stop();wrongpassword.stop();time.stop();
         game.setScreen(new StartMenu(game));
         this.dispose();
     }
    }

    @Override
    public void resize(int width, int height) {
          gameport.update(width,height);
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
        stand1.dispose();
        stand2.dispose();
        stand3.dispose();
        stand4.dispose();
    }
}
