package Screens;

import com.main.Main;
import MovingObjects.*;
import Scenes.GameOver;
import Scenes.HUD2;
import Sprites.Border;
import Sprites.Sandal;
import Sprites.Wall;
import Sprites.Letter;
import StaticGraphics.ArabBoy;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;

/* LEVEL TWO SCREEN  */
public class Level2 extends PlayScreen{  
    
    private ArabBoy boy;
    private Sandal sandal;
    private Camel camel;
    
    public Level2(Main game){                                                                                   // Start       // Wagon       // Slider        // End
        super(game, 1300.00f, "Maps\\Level Two\\HerculesMap.tmx"); // 1300.00f // 9400.00f // 42500.00f // 68000
        
        hud2 = new HUD2(this);
        createPauseMenu(hud2.stage, hud2.skin);
        sandal = new Sandal(48000 / Main.PPM, 700 / Main.PPM, player);
        camel=new Camel(this);
        boy = new ArabBoy(this);
        noSwords = true;
        adaptSounds();
        Victory.stop();
        Game.play();
    }
     
    @Override
    public void restart() {
        player.body.setTransform(1300f / Main.PPM, 190f / Main.PPM, 0f);
        creator.restart2();
        hud2.timer=hud2.maxTime;
        hud2.DrCounter=88.5f;
        hud2.score=hud2.i=0;
        hud2.statetimer = hud2.timeSpent = 0f;
        sandal.draw=true;
        camel.once = false;
        camel.losed=false;
        if(!concentrate.isPlaying()){
            concentrate.play();
            concentrate.setVolume(Main.vol);
        }
    }
    
     /*Start Rendering Objects*/
     public void update(float dt){
        handleInput();
        world.step(1/60f, 6, 2);
        if(restart)restart();
        
        player.update(dt);
        hud2.update(dt);
        updateCoins();
        
        for(Chicken chicken : creator.getChickens())
              chicken.update(dt); 
        for(Wall wall : creator.getWalls())
             wall.HercuelesHit(dt);
        for (Wolf wolf : creator.getWolfs())
              wolf.update(dt);
        for (Vulture vulture : creator.getVultures())
              vulture.update(dt);
        for (Wagon wagon : creator.getWagons())
              wagon.update(dt);
        for (Letter letter : creator.getLetters())
              letter.update(dt);
        
        boy.update(dt);
        camel.update();
        sandal.update();
        gameCam.update();
        renderer.setView(gameCam);
        
        cameraScoop(1000f, 71000f);
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            paused=true;
            pauseWindow.setVisible(true);
        }
            if (gameOver()) {
                Game.stop();
                player.danger.stop();
                game.setScreen(new GameOver(game));
                dispose();
        }
    }
    
    @Override
    public void render(float delta) {
       if(!paused){update(delta);}
       else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
           paused=false;
           pauseWindow.setVisible(false);
       }
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       renderer.render();
       game.batch.setProjectionMatrix(gameCam.combined);
       //debuger.render(world,gameCam.combined);
       
       game.batch.begin();
        boy.draw(game.batch);
         for(Chicken chicken : creator.getChickens())
            chicken.draw(game.batch);
         for(Wall wall : creator.getWalls())
             wall.draw();
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
         if(sandal.draw)sandal.draw(game.batch);
         player.draw(game.batch);
         for(Wall wall : creator.getWalls())
             wall.drawExtra();
         for(Wagon wagon : creator.getWagons())
             wagon.draw(game.batch);
         for(Letter letter : creator.getLetters())
             letter.draw();
       game.batch.end();
       
       game.batch.setProjectionMatrix(hud2.stage.getCamera().combined);
       hud2.stage.draw();
    }
    /*End Rendering Objects*/
    
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }
    
    @Override
    public void dispose() {
       map.dispose();
       hud2.dispose();
    }
    
}