package Scenes;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;



public class Credit implements Screen {
    private Main game;
    private Sprite sprite[];  
    private float alpha, stateTimer;
    private int cnt;
    
    public Credit (Main game) {
        this.game = game;
        sprite = new Sprite[2];
        sprite[0] = new Sprite(new Texture("Intros//Credit.jpg"), 0, 0, 1365, 768);
        sprite[1] = new Sprite(new Texture("Intros//Credit 2.jpg"), 0, 0, 1365, 768);
        sprite[0].setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite[1].setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        alpha=stateTimer=0f;
        cnt=0;
    }
   
    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);   
        
        /********** TIME CONTROLLER **************/
       stateTimer += dt;
       
        if (stateTimer < 2f)  //FADE IN
           alpha += (1f / 60f) / 2;
        else if (stateTimer > 6) { //FADE OUT
           alpha -= (1f / 60f) / 2;
           if(stateTimer>8f){
            stateTimer=0;
            alpha=0;
            cnt++;
           }
       }
        /*****************************************/
        if (cnt==2){     // Repeate 
            cnt=0;
            stateTimer=alpha=0;
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
       
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) 
            System.exit(0);
        
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
