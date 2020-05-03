
package Intro;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Setting  implements Screen{
    
    private Main game;

     public Stage stage;
    private Skin skin;
    private Texture background;
    private Viewport viewport;
    public Setting( Main game) {
        this.game = game;
        background = new Texture(Gdx.files.internal("Intros\\0.jpg"));
         viewport = new StretchViewport(Main.WIDTH, Main.HEIGHT,  new OrthographicCamera());
        stage = new Stage(viewport, ((Main) game).batch);
         Gdx.input.setInputProcessor(stage);   
          createBasicSkin(); 
          Buttons();
       
    }
    private void createBasicSkin() {
        BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts\\Menu.fnt"));
        skin = new Skin();
        skin.add("default", font);
        
        // CREATE A TEXTURE
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.DARK_GRAY);
        pixmap.fill();
        skin.add("Background", new Texture(pixmap));
        
        // CREATE A BUTTON STYLE
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("Background", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("Background", Color.YELLOW);
        textButtonStyle.checked = skin.newDrawable("Background", Color.GREEN);
        textButtonStyle.over = skin.newDrawable("Background", Color.BLUE);  // HOVER
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }
    void Buttons()
    {
        
        TextButton controlerkeys  = new TextButton("Controller keys",skin);
        controlerkeys.setPosition(Gdx.graphics.getWidth() / 2 +150+Main.x, Gdx.graphics.getHeight() / 2 +150+Main.y);
        controlerkeys.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new ControlerSetting(game));
            }
        });
        stage.addActor(controlerkeys);
        TextButton MusicandSound = new TextButton("Music and Sound", skin);
        MusicandSound.setPosition(controlerkeys.getX() , controlerkeys.getY()-100);
        MusicandSound.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new SoundAndMusicSetting(game));
            }
        });
        stage.addActor(MusicandSound);
        
        TextButton screendisplay  = new TextButton("Screen Display", skin);
        screendisplay.setPosition(MusicandSound.getX() ,MusicandSound.getY()-100);
        screendisplay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new ScreenSetting(game));
            }
        });
        stage.addActor(screendisplay);
        TextButton back = new TextButton("Back", skin);
        back.setPosition(screendisplay.getX(),screendisplay.getY()-100);
        
        back.addListener(new ClickListener() {  // RESET DEFAULT
            @Override
            public void clicked(InputEvent event, float x, float y) { 
                game.setScreen(new StartMenu(game));
            }
        });
        stage.addActor(back);
        
    }
    @Override
    public void render(float dt) {
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       Main.batch.begin();
        Main.batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
        Main.batch.end(); 
       stage.act();
       stage.draw();
       
       if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
           game.setScreen(new StartMenu(game));
       }
       
    }

    @Override
    public void show() {
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
