
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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Username implements Screen{
    
    private Main game;
    private Stage stage;
    private Skin skin;
    private Label enter;
    private TextField username;
    private TextButton play;
    private Label back;
    private Music music;
    private Viewport viewport;
    private Texture background;
    
    public Username( Main game, Music music) {
        this.game = game;
        this.music = music;
        background = new Texture(Gdx.files.internal("Intros\\0000.jpg"));
        viewport = new StretchViewport(game.WIDTH, game.HEIGHT,  new OrthographicCamera());
        stage = new Stage(viewport, ((Main) game).batch);
        Gdx.input.setInputProcessor(stage);
        createWidgets();
    }
    
    private void createWidgets(){
        skin = new Skin(Gdx.files.internal("Fonts\\uiskin.json"));
        
        enter = new Label("Please Enter Your Name !", skin);
        enter.setSize(400, 50);
        enter.setPosition(game.WIDTH/2-enter.getWidth()/2 + 90, game.HEIGHT/2+60);
        enter.setFontScale(1.4f);
        
        stage.addActor(enter);
        
        username = new TextField("", skin);
        username.setSize(400, 50);
        username.setAlignment(Align.center);
        username.setPosition(game.WIDTH/2-username.getWidth()/2 + 20, game.HEIGHT/2 + 0);
        
        stage.addActor(username);
        
        play = new TextButton("Play", skin);
        play.setSize(400, 50);
        play.setPosition(game.WIDTH/2-play.getWidth()/2 + 20, game.HEIGHT/2 - 60);
        
        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                if (username.getText().length() < 3)
                    JOptionPane.showInternalConfirmDialog(null, "Are You Even Trying ?!\nPlease Enter A Real Name.");
                    
                else {
                    music.stop();
                    game.username = username.getText();
                    game.setScreen(new Level1(game));
                    getObjectClass().dispose();    
                }
            }
        });
        stage.addActor(play);
        
        back = new Label("Press Escape To Return To Main Menu...", skin);
        
        back.setPosition(game.WIDTH/2-enter.getWidth()/2 + 70, game.HEIGHT/2 - 120);
        back.setSize(400, 50);
        stage.addActor(back);
    }
    
    private Username getObjectClass(){
        return this;
    }
    
    @Override
    public void render(float dt) {
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
       if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
           game.setScreen(new StartMenu(game));
       }
       
       game.batch.begin();
        game.batch.draw(background, 0, 0, game.WIDTH, game.HEIGHT);
       game.batch.end();
       
       stage.act();
       stage.draw();
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
        stage.dispose();
    }
    
}
