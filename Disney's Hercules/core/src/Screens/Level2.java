package Screens;

import MovingObjects.*;
import Sprites.Border;
import Sprites.Sandal;
import Sprites.Wall;
import Sprites.Letter;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;

/* LEVEL TWO SCREEN  */
public class Level2 extends PlayScreen{  
    
    private Sandal sandal;
    private Camel camel;
    
    public Level2(Main game){                                                                                   // Start       // Wagon       // Slider        // End
        super(game, 1300.00f, "Maps\\Level Two\\HerculesMap.tmx"); // 1300.00f // 9400.00f // 42500.00f // 700000
        
        sandal = new Sandal(new Texture("Sprites\\Level 1\\Complement\\Sandal.png"), 100 / Main.PPM, 100 / Main.PPM, player); // Position Fix
        camel=new Camel(player,game);
        noSwords = true;
    }
    
     public void update(float dt){
        handleInput();
        world.step(1/60f, 6, 2);
        player.update(dt);
        hud2.update(dt);
        updateCoins();
        
        for(Chicken chicken : creator.getChickens())
            chicken.update(); 
        for (Wolf wolf : creator.getWolfs())
            wolf.update(dt);
        for (Vulture vulture : creator.getVultures())
            vulture.update(dt);
        for (SecondaryCharacter wagon : creator.getWagons())
            wagon.update(dt);
        for (Letter letter : creator.getLetters())
              letter.update(dt);
        
        camel.update();
        sandal.update();
        
        timer.update();
        gameCam.update();
        renderer.setView(gameCam);
        
        cameraScoop(1000f, 71000f);
        
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) 
            System.exit(0);
    }
    
    @Override
    public void render(float delta) {
       update(delta);
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       renderer.render();
       game.batch.setProjectionMatrix(gameCam.combined);
       //debuger.render(world,gameCam.combined);
       
       game.batch.begin();
         for(Chicken chicken : creator.getChickens())
            chicken.draw(game.batch);
         for(Wolf wolf : creator.getWolfs())
             wolf.draw(game.batch);
         for(Wall wall : creator.getWalls())
             wall.draw();
         for(Border border : creator.getBorders())
            border.draw(game.batch);
         for(Vulture vulture : creator.getVultures()){
            vulture.draw(game.batch); 
            vulture.egg.draw(game.batch);
         }
         renderCoins();
         camel.draw(game.batch);
         sandal.draw(game.batch);
         player.draw(game.batch);
         for(Wall wall : creator.getWalls())
             wall.drawExtra();
         for(SecondaryCharacter wagon : creator.getWagons())
             wagon.draw(game.batch);
         for(Letter letter : creator.getLetters())
             letter.draw();
       game.batch.end();
       
       game.batch.setProjectionMatrix(hud2.stage.getCamera().combined);
       hud2.stage.draw();
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