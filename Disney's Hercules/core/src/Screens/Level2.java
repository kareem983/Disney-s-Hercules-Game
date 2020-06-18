package Screens;

import MovingObjects.Hercules;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.Hercules.game.Main;
import com.badlogic.gdx.math.Vector2;

/* LEVEL TWO SCREEN  */
public class Level2 extends PlayScreen{  
    
    public Level2(Main game){
        super(game, "Maps\\Level Two\\HerculesMap.tmx");
      
        player= new Hercules(world , this, 1300f);
       
    }
     public void update(float dt){
        handleInput();
        world.step(1/60f, 6, 2);  
        player.update(dt); 
       
        if (player.b2body.getPosition().x>1000/Main.PPM && player.b2body.getPosition().x<23000/Main.PPM)
            gameCam.position.x = player .b2body.getPosition().x ;
        if (player.b2body.getPosition().y<470/Main.PPM )
        gameCam.position.y = player .b2body.getPosition().y+255/Main.PPM ;

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
       
       game.batch.setProjectionMatrix(gameCam.combined);
       
       game.batch.begin();
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