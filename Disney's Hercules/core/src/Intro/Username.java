
package Intro;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Username implements Screen{
    
    private Main game;
    private Stage stage;
    private Skin skin;
    private Label enter;
    private TextField username;
    private TextButton play;
    private Label back;
    private Music music;
    
    public Username( Main game, Music music) {
        this.game = game;
        this.music = music;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        createWidgets();
    }
    
    private void createWidgets(){
        skin = new Skin(Gdx.files.internal("Fonts\\uiskin.json"));
        
        enter = new Label("Please Enter Your Name !", skin);
        enter.setPosition(Gdx.graphics.getWidth()/2-enter.getWidth()/2+15, 400);
        enter.setSize(300, 50);
        stage.addActor(enter);
        
        username = new TextField("", skin);
        username.setPosition(Gdx.graphics.getWidth()/2-username.getWidth()/2-55, 350);
        username.setSize(300, 50);
        stage.addActor(username);
        
        play = new TextButton("Play", skin);
        play.setPosition(Gdx.graphics.getWidth()/2-play.getWidth()/2 - 110, Gdx.graphics.getHeight()/2 -100);
        play.setSize(300, 50);
        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                music.stop();
                game.setScreen(new PlayScreen(game));
                getObjectClass().dispose();
            }
        });
        stage.addActor(play);
        
        back = new Label("Press Escape To Return To Main Menu...", skin);
        back.setPosition(Gdx.graphics.getWidth()/2-enter.getWidth()/2+25, Gdx.graphics.getHeight()/2 - 200);
        back.setSize(300, 50);
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
