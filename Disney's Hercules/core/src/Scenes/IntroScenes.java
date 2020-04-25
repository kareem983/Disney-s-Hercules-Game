
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
    private boolean safe;
    
    public IntroScenes(Main game) {
        this.game = game;
        safe = false;
        // LOADING ALL INTORS IMAGES
        loadImages();
        /******************/
    }
    private void loadImages(){
        texture = new Texture[6];
        texture[0] = new Texture (Gdx.files.internal("Intros\\0.jpg"));
        texture[1] = new Texture (Gdx.files.internal("Intros\\1.jpg"));
        texture[2] = new Texture (Gdx.files.internal("Intros\\2.jpg"));
        texture[3] = new Texture (Gdx.files.internal("Intros\\3.jpg"));
        texture[4] = new Texture (Gdx.files.internal("Intros\\4.jpg"));
        texture[5] = new Texture (Gdx.files.internal("Intros\\5.jpg"));
        sprite = new Sprite[6];
        sprite[0] = new Sprite(texture[0], 0, 0, 1349, 1055);
        sprite[1] = new Sprite(texture[1], 0, 0, 1000, 562);
        sprite[2] = new Sprite(texture[2], 0, 0, 1432, 864);
        sprite[3] = new Sprite(texture[3], 0, 0, 1920, 1080);
        sprite[4] = new Sprite(texture[4], 0, 0, 413, 310);
        sprite[5] = new Sprite(texture[5], 0, 0, 728, 546);
        
        sprite[0].setSize(Main.WIDTH-620, Main.HEIGHT-50);
        sprite[1].setSize(Main.WIDTH-620, Main.HEIGHT-50);
        sprite[2].setSize(Main.WIDTH-620, Main.HEIGHT-50);
        sprite[3].setSize(Main.WIDTH-620, Main.HEIGHT-50);
        sprite[4].setSize(Main.WIDTH-620, Main.HEIGHT-50);
        sprite[5].setSize(Main.WIDTH-620, Main.HEIGHT-50);
    }
    
    @Override
    public void render(float dt) {
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
       /********** TIME CONTROLLER **************/
       stateTimer += dt;
       
        if (stateTimer < 3.95f)   //FADE IN
            alpha += (1f / 60f) / 2;
       else if (stateTimer > 6){ //FADE OUT
           alpha -= (1f / 60f) /2;
           if(stateTimer>9.5f){
            stateTimer=0;
            alpha=0;
            cnt++;
           }
       }
        /*****************************************/
        if (cnt==6){     // INTROS FINISHED 
            game.setScreen(new StartMenu(game));
            this.dispose();
            safe = true;
        }
        if (safe)cnt=7;
        if (cnt<6)
            sprite[cnt].setAlpha(alpha);
       
       game.batch.begin();
       if (cnt<6)
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
        texture[0].dispose();
        texture[1].dispose();
        texture[2].dispose();
        texture[3].dispose();
        texture[4].dispose();
        texture[5].dispose();
    }
    
    
}
