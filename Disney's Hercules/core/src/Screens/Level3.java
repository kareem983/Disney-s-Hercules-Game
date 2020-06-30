package Screens;

import MovingObjects.Hercules;
import MovingObjects.SecondaryCharacter;
import StaticGraphics.Sea;
import StaticGraphics.Waterfall;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/* LEVEL TWO SCREEN  */
public class Level3 extends PlayScreen{  
    
    private Sea sea;
    
    public Level3(Main game){
        super(game, 100.00f, "C:\\Users\\Google\\Desktop\\Level Three\\Map.tmx");// 1300f
      
        sea = new Sea(this);
    }
    
     public void update(float dt){
        handleInput();
        world.step(1/60f, 6, 2);  
        
        player.update(dt);
        sea.update(dt);
        
        gameCam.update();
        renderer.setView(gameCam);
        
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) 
            System.exit(0);
    }
    
    @Override
    public void render(float delta) {
       update(delta);
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
       renderer.render();
        //debuger.render(world,gameCam.combined);
       // if (player.b2body.getPosition().x>1000/Main.PPM  && player.b2body.getPosition().x<71000/Main.PPM) //
            gameCam.position.x = player .body.getPosition().x ;
      //  if (player.b2body.getPosition().y<470/Main.PPM )
        gameCam.position.y = player .body.getPosition().y+255/Main.PPM ;
       game.batch.setProjectionMatrix(gameCam.combined);
       
       game.batch.begin();
        sea.draw(game.batch);
        player.draw(game.batch);
       game.batch.end();
       
    }
    
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }
    
    @Override
    public void dispose() {
       map.dispose();
       renderer.dispose();
       world.dispose();
       //debuger.dispose();
       //hud.dispose();
    }
    
}