package Screens;

import MovingObjects.Vulture;
import HealthAttacker.VultureEgg;
import MovingObjects.*;
import Sprites.Block;
import Sprites.Border;
import Sprites.GoldenCoin;
import Sprites.Sandal;
import Sprites.Word;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

/* LEVEL TWO SCREEN  */
public class Level2 extends PlayScreen{  
    
    public Sandal sandal;
    private Camel camel;
    private ArrayList<Chicken> chicken=new ArrayList<>();
    
    private Word herculesWord ;
    private Wolf wolf;
    
    public Level2(Main game){                                                                                   // Start       // Wagon      // End
        super(game, 1300.00f, "Maps\\Level Two\\HerculesMap.tmx"); // 1300.00f // 9400.00f // 700000
      
        herculesWord = new Word(world,this ,2000,800 , 2200,800 ,2400,800 ,2600,800 ,2800,800 ,3000,800 ,3200,800 ,3400,800  );
        sandal = new Sandal(new Texture("sprites\\Level 1\\Complement\\sandal.png"), 100 / Main.PPM, 100 / Main.PPM, player); // Position Fix
        wolf = new Wolf(this , 1600, 400 , 2500);
        noSwords = true;
        
        camel=new Camel(player,game);
        goldcoin.add(new GoldenCoin (this,2000,288,player,hud));
        chicken.add(new Chicken(1500f,358f));
    }
    
     public void update(float dt){
        handleInput();
        world.step(1/60f, 6, 2);  
        
        for(int i=0;i<chicken.size();i++)
            chicken.get(i).update();
        for(int i=0;i<goldcoin.size();i++)
            goldcoin.get(i).update(player);
        
        player.update(dt);
        for (SecondaryCharacter wagon : creator.getWagons())
            wagon.update(dt);
        for (Vulture vulture : creator.getVultures())
            vulture.update(dt);
 
        herculesWord.update(dt);
        wolf.update(dt);
        camel.update();
        sandal.update();
        
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
       debuger.render(world,gameCam.combined);
       
       game.batch.begin();
         for(int i=0;i<goldcoin.size();i++)
            goldcoin.get(i).draw(game.batch);
         for(int i=0;i<chicken.size();i++)
            chicken.get(i).draw(game.batch);
         for(Border border : creator.getBorders())
            border.draw(game.batch);
         for(Vulture vulture : creator.getVultures()){
            vulture.draw(game.batch); 
            vulture.egg.draw(game.batch);
         }
         
         wolf.draw(game.batch);
         camel.draw(game.batch);
         sandal.draw(game.batch);
         player.draw(game.batch);
        for(SecondaryCharacter wagon : creator.getWagons())
            wagon.draw(game.batch);
       
        herculesWord.draw();
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