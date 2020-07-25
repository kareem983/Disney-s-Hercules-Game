package Screens;

import HealthAttacker.Cyclops;
import HealthAttacker.CyclopsDie;
import HealthAttacker.CyclopsFire;
import HealthAttacker.Fireball;
import Intro.StartMenu;
import Scenes.GameOver;
import Scenes.HUD3;
import Scenes.Transition;
import com.main.Main;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import java.util.ArrayList;

/* LEVEL Three SCREEN  */
public class Level3 extends PlayScreen{  
    private ArrayList<Fireball> fireAttack=new ArrayList<>();
    private Cyclops cyclops;
    private CyclopsFire cyclopsfire;
    private CyclopsDie cyclopsDie;
    private int VectoryTime; 
    private Music x;
   
    
    public Level3(Main game){
        super(game, 700.00f, "Maps\\Level Three\\HerculesMap.tmx");// 1300f
       
        cyclops=new Cyclops(this);
        cyclopsfire=new CyclopsFire(this);
        cyclopsDie=new CyclopsDie(this);
        VectoryTime=0;
                
        hud3=new HUD3(this);
        createPauseMenu(hud3.stage, hud3.skin);
        AddFireAttack();
        noSwords = false;
        adaptSounds();
        Game = game.manager.get("Audio//Hercules - Voices//Cyclops//Level3 Sound.mp3", Music.class);
        x= game.manager.get("Audio//Hercules - Voices//Cyclops//Hercules Where are you.wav", Music.class);
        x.setVolume(Main.vol);
        x.play();
        
        Game.setLooping(true);
        Game.setVolume(Main.vol);
        Game.play();
    }

    
    
    @Override
    public void restart() {
        x.stop();
        Victory.stop();
            
    }
    
    
    private void AddFireAttack(){
        fireAttack.add(new Fireball(1500,650,player,"Level3"));
        fireAttack.add(new Fireball(1900,690,player,"Level3"));
        fireAttack.add(new Fireball(2200,720,player,"Level3"));
        fireAttack.add(new Fireball(2600,660,player,"Level3"));
    }
    
     public void update(float dt){
        handleInput();
        world.step(1/60f, 6, 2);  
        
        if(restart || CyclopsDie.FinalDie )restart();
        
        
        if(player.body.getPosition().x> (4667 /Main.PPM) && player.body.getPosition().y < (250 /Main.PPM)){
            VectoryTime++;
            stopHercAction=true;
            Game.stop();
            Victory.setVolume(Main.vol);
            Victory.play();
            if(VectoryTime >100)game.setScreen(new Transition(this));
        }
        
        player.update(dt);
        hud3.update(dt);
        gameCam.update();
        
       if(!HUD3.CyclopsDie)cyclops.update();
       else{
         if(!CyclopsDie.FinalDie)
         cyclopsDie.update();
       }
        cyclopsfire.update();
        for(int i=0;i<fireAttack.size();i++)fireAttack.get(i).update();
        renderer.setView(gameCam);
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            pauseWindow.setVisible(true);
            paused=true;
        }
        
        if (player.hercules_Die) {
            Game.stop();
            GameOver.setVolume(Main.vol);
            GameOver.play();
            game.setScreen(new GameOver(game));
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
       
        //debuger.render(world,gameCam.combined);
        if (player.body.getPosition().x>1000/Main.PPM  && player.body.getPosition().x<3750/Main.PPM) //
            gameCam.position.x = player.body.getPosition().x+20/Main.PPM ;
        if (player.body.getPosition().y<100/Main.PPM )
            gameCam.position.y = player .body.getPosition().y+310/Main.PPM ;
      
       game.batch.setProjectionMatrix(gameCam.combined);
       
       game.batch.begin();
      if(!HUD3.CyclopsDie)cyclops.draw(game.batch);
      else {
        if(!CyclopsDie.FinalDie)
        cyclopsDie.draw(game.batch);
      }
       cyclopsfire.draw(game.batch);
       for(int i=0;i<fireAttack.size();i++)fireAttack.get(i).draw(game.batch);;
        
       player.draw(game.batch);
       game.batch.end();
       hud3.stage.draw();
    }
 
    
    
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height+20);
    }
    
    @Override
    public void dispose() {
       map.dispose();
       renderer.dispose();
       world.dispose();
       hud3.dispose();
       //debuger.dispose();
    }
   
}