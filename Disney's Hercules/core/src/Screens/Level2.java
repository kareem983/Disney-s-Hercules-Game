package Screens;

import MovingObjects.Hercules;
import MovingObjects.wolf;
import Sprites.FinalCamelMoving;
import Sprites.GoldenCoin;
import Sprites.MovingChicken;
import Sprites.character;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

/* LEVEL TWO SCREEN  */
public class Level2 extends PlayScreen{  
    private FinalCamelMoving FinalCamel; 
    private ArrayList<GoldenCoin> goldcoin=new ArrayList<>();
    private ArrayList<MovingChicken> movingchicken=new ArrayList<>();
    
    character c ;
    wolf wolf;
    
    
    public Level2(Main game){
        super(game, "Maps\\Level Two\\HerculesMap.tmx");
      
        player= new Hercules(world , this, 1300.00f); // 1300f
        c = new character(world,this ,2000,800 , 2200,800 ,2400,800 ,2600,800 ,2800,800 ,3000,800 ,3200,800 ,3400,800  );
        wolf = new wolf(this , 1600, 400 , 2500);
        FinalCamel=new FinalCamelMoving(player,game);
        goldcoin.add(new GoldenCoin (this,2000,288,player,hud));
        movingchicken.add(new MovingChicken(1500f,358f));
        
        
    }
     public void update(float dt){
        handleInput();
        world.step(1/60f, 6, 2);  
        wolf.update(dt);
        player.update(dt);
        if (player.b2body.getPosition().x>1000/Main.PPM  && player.b2body.getPosition().x<71000/Main.PPM) //
            gameCam.position.x = player .b2body.getPosition().x ;
        if (player.b2body.getPosition().y<470/Main.PPM )
        gameCam.position.y = player .b2body.getPosition().y+255/Main.PPM ;

        FinalCamel.update();
         for(int i=0;i<goldcoin.size();i++){
            goldcoin.get(i).update(player);
        }
        for(int i=0;i<movingchicken.size();i++){
            movingchicken.get(i).update();
        }
        
        gameCam.update();
        renderer.setView(gameCam);
        c.update();
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
       wolf.draw(game.batch);

        player.draw(game.batch);
        
        FinalCamel.draw(game.batch);
         for(int i=0;i<goldcoin.size();i++){
            goldcoin.get(i).draw(game.batch);
         }
        
         for(int i=0;i<movingchicken.size();i++){
            movingchicken.get(i).draw(game.batch);
         }
         
         
        for (int i=0;i<8;i++){
           if (c.GET[i]==false) {
               c.sprites[i].draw(game.batch);
           }
          else {
              if ( c.sprites[i].getX() + (500/Main.PPM) > getPlayer().b2body.getPosition().x&& c.sprites[i].getX() - (500/Main.PPM) <getPlayer().b2body.getPosition().x && c.getty2[i] == false ) {
                  c.sprite1.draw(game.batch);
                 if (i == 0){c.sprites[i].setPosition(c.sprite1.getX() - (5 / (Main.PPM)), c.sprite1.getY());}
                  else if (i <= 1) {

                      c.sprites[i].setPosition(c.sprite1.getX() + ((((i * 186) + (5 * i))) / (Main.PPM)), c.sprite1.getY());

                  }
                  else if (i <= 5){    c.sprites[i].setPosition(c.sprite1.getX() + ((((i * 190) + (5 * i))) / (Main.PPM)), c.sprite1.getY());}
                  else {
                      c.sprites[i].setPosition(c.sprite1.getX() + ((i *193) / (Main.PPM)), c.sprite1.getY());
                  }

                  c.sprites[i].setSize(150 / Main.PPM, c.sprite1.getHeight());
                  c.sprites[i].draw(game.batch);

                  for (int j = 0; j < i; j++) {
                     if (c.GET[j]==true)
                      {
                          if (j == 0){c.sprites[j].setPosition(c.sprite1.getX() - (5 / (Main.PPM)), c.sprite1.getY());}
                          else if (j <= 1) {

                          c.sprites[j].setPosition(c.sprite1.getX() + ((((j * 186) + (5 * j))) / (Main.PPM)), c.sprite1.getY());

                      }
                      else if (j <= 5){    c.sprites[j].setPosition(c.sprite1.getX() + ((((j * 190) + (5 * j))) / (Main.PPM)), c.sprite1.getY());}
                      else {
                          c.sprites[j].setPosition(c.sprite1.getX() + ((j *193) / (Main.PPM)), c.sprite1.getY());
                      }

                      c.sprites[j].setSize(150 / Main.PPM, c.sprite1.getHeight());
                      c.sprites[j].draw(game.batch);
                  }
              }
              }
              else {c.getty2[i]=true;}

           }
        }

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