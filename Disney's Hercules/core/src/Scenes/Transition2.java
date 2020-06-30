package Scenes;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;



public class Transition2 implements Screen {
    private Main game;
    
    
    public Transition2(Main game) {
        this.game = game;
   
    }

   
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);       
        game.batch.begin();
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) 
            System.exit(0);
        game.batch.end();
        
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
