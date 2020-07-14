package Scenes;

import com.main.Main;
import Intro.StartMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class IntroScenes implements Screen{
    private Main game;
    private Texture texture[];
    private Sprite sprite[];
    private Stage stage;
    private Label skip;
    private float alpha, lblAlpha;
    private float stateTimer, lblStateTimer;
    private int cnt;
    
    public IntroScenes(Main game) {
        this.game = game;
        alpha = stateTimer = lblAlpha = lblStateTimer = 0;
        cnt = 0;
        stage = new Stage(new FitViewport(Main.WIDTH,Main.HEIGHT, new OrthographicCamera()), game.batch);
        skip = new Label("Press Escape To Skip...", new Label.LabelStyle(new BitmapFont(Gdx.files.internal("Fonts//HUD2.fnt")),null));
        skip.setPosition(game.WIDTH/2 - skip.getWidth()/2, 100);
        // LOADING ALL INTORS IMAGES
        loadImages();
        /******************/
        stage.addActor(skip);
    }
    private void loadImages(){
        texture = new Texture[4];
        texture[0] = new Texture (Gdx.files.internal("Intros\\1.jpg"));
        texture[1] = new Texture (Gdx.files.internal("Intros\\2.jpg"));
        texture[2] = new Texture (Gdx.files.internal("Intros\\3.jpg"));
        texture[3] = new Texture (Gdx.files.internal("Intros\\4.jpg"));
        sprite = new Sprite[4];
        sprite[0] = new Sprite(texture[0], 0, 0, 1000, 562);
        sprite[1] = new Sprite(texture[1], 0, 0, 1432, 864);
        sprite[2] = new Sprite(texture[2], 0, 0, 1920, 1080);
        sprite[3] = new Sprite(texture[3], 0, 0, 728, 546);
        
        sprite[0].setSize(game.WIDTH, game.HEIGHT);
        sprite[1].setSize(game.WIDTH, game.HEIGHT);
        sprite[2].setSize(game.WIDTH, game.HEIGHT);
        sprite[3].setSize(game.WIDTH, game.HEIGHT);
    }
    
    @Override
    public void render(float dt) {
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
       /********** SPRITES TIME CONTROLLER **************/
       stateTimer += dt; 
       
        if (stateTimer < 2f)  //FADE IN
           alpha += (1f / 60f) / 2;
        else if (stateTimer > 4) { //FADE OUT
           alpha -= (1f / 60f) / 2;
           if(stateTimer>6f){
            stateTimer=0;
            alpha=0;
            cnt++;
           }
       }
        /*****************************************/
        if (cnt==4){     // INTROS FINISHED 
            game.setScreen(new StartMenu(game));
            this.dispose();
        }

        if (cnt>-1&&cnt<4){
            if(alpha<0)alpha=0;
            else if (alpha>1)alpha=1;
            sprite[cnt].setAlpha(alpha);
        }
       /*****************************/
       lblStateTimer += dt;
       if(lblStateTimer > 0.1){
           lblAlpha+=0.1f;
           lblStateTimer=0;
       }
       if(lblAlpha>1)lblAlpha=0;
       skip.setColor(1, 1, 1, lblAlpha);
       /*****************************/
       game.batch.begin();
        if (cnt>-1&&cnt<4)
           sprite[cnt].draw(game.batch);
       game.batch.end();
       stage.draw();
       
       if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
           game.setScreen(new StartMenu(game));
           this.dispose();
       }
    }

    @Override
    public void resize(int width, int height) {
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
        for(int i = 0; i<4 ;++i)
            texture[i].dispose();
        stage.dispose();
    }
 
}
