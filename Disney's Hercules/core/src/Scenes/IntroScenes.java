package Scenes;

import Intro.StartMenu;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class IntroScenes implements Screen{
    private Main game;
    private Texture texture[];
    private Sprite sprite[];
    private float alpha;
    private float stateTimer;
    private int cnt;
    
    public IntroScenes(Main game) {
        this.game = game;
        alpha = stateTimer = 0;
        cnt = -1;
        // LOADING ALL INTORS IMAGES
        loadImages();
        /******************/
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
        
        sprite[0].setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite[1].setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite[2].setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite[3].setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    
    @Override
    public void render(float dt) {
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
       /********** TIME CONTROLLER **************/
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
       
       game.batch.begin();
        if (cnt>-1&&cnt<4)
           sprite[cnt].draw(game.batch);
       game.batch.end();
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
    }
 
}
