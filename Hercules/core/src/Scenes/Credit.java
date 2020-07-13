package Scenes;

import com.main.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Credit implements Screen {
    private Sprite sprite[];
    private Sprite theEnd;
    private float alpha, stateTimer;
    private boolean passEnd;
    private int cnt;
    private Music finale;
    
    public Credit () {
        theEnd = new Sprite(new Texture("Intros//The End.jpg"));
        theEnd.setSize(Main.WIDTH, Main.HEIGHT);
        sprite = new Sprite[2];
        sprite[0] = new Sprite(new Texture("Intros//Credit.jpg"), 0, 0, 1365, 768);
        sprite[1] = new Sprite(new Texture("Intros//Credit 2.jpg"), 0, 0, 1365, 768);
        sprite[0].setSize(Main.WIDTH, Main.HEIGHT);
        sprite[1].setSize(Main.WIDTH, Main.HEIGHT);
        alpha=stateTimer=0f;
        cnt=0;
        passEnd=false;
        finale = Main.manager.get("Audio//Hercules - sounds//The End.mp3", Music.class);
        finale.play();
        finale.setLooping(true);
        finale.setVolume(Main.vol);
    }
   
    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);   
        
        /********** TIME CONTROLLER **************/
       stateTimer += dt;
       if(stateTimer>11f&&!passEnd){
           stateTimer=0f;
           passEnd=true;
       }
       if(passEnd){
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
       
       Main.batch.begin();
       if(!passEnd)
           theEnd.draw(Main.batch);
       else if (cnt>-1&&cnt<4)
           sprite[cnt].draw(Main.batch);
       Main.batch.end();
       
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
