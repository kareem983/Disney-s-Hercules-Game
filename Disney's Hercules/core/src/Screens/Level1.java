package Screens;

import HealthAttacker.*;
import Scenes.*;
import Sprites.*;
import StaticGraphics.ReachPoint;
import Tools.WorldCreator;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.Hercules.game.Main;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* LEVEL ONE SCREEN  */
public class Level1 extends PlayScreen {
    
    public TallPiller piller;
    public Hill hill;
    private ArrayList<SilverCoin> silvercoin=new ArrayList<>();
    private List<FeatherSack> featherList;
    private List<MovingFeather> MovingfeatherList;
    private List<Block> BlockList;
    private ReachPoint reachPoint;
    
    public Level1(Main game) {
        super(game, 750f, "Maps\\Level One\\HerculesMap.tmx"); // 750f
        
        piller = new TallPiller(world, this, player, 6660, 50);
        hill = new Hill(world, this, this.map);
        reachPoint = new ReachPoint(this);
        noSwords=false;
        coins();
        cannons();
        structFeathers();
        adaptSounds();
        
        Game.play();
    }
    
    /*Start Constructor Methods*/ 
    private void coins(){
        goldcoin.add(new GoldenCoin (this,2192,288,player,hud));
        goldcoin.add(new GoldenCoin (this,2240,336,player,hud));
        goldcoin.add(new GoldenCoin (this,2288,384,player,hud));
        goldcoin.add(new GoldenCoin (this,18520,750,player,hud));
        goldcoin.add(new GoldenCoin (this,18670,750,player,hud));
        goldcoin.add(new GoldenCoin (this,18820,750,player,hud));

        silvercoin.add(new SilverCoin (this,13120,272,player,hud));
          
        silvercoin.add(new SilverCoin (this,13120,352,player,hud));
        silvercoin.add(new SilverCoin (this,13120,416,player,hud));
    }
    
    private void cannons(){
        filreball.add(new Cannons(2256,1104,player,hud));
        filreball.add(new Cannons(13120,1050,player,hud));
        filreball.add(new Cannons(20448,680,player,hud));
        filreball.add(new Cannons(20720,700,player,hud));
        filreball.add(new Cannons(20976,730,player,hud));
        filreball.add(new Cannons(21248,750,player,hud));
        filreball.add(new Cannons(21456,790,player,hud));
        filreball.add(new Cannons(21728,810,player,hud));
        filreball.add(new Cannons(21888,890,player,hud));
        filreball.add(new Cannons(22160,920,player,hud));
        filreball.add(new Cannons(21888,980,player,hud));
        filreball.add(new Cannons(22160,1104,player,hud));
    }
    
    private void structFeathers(){
        featherList = new LinkedList<>();
        MovingfeatherList = new LinkedList<>();
        BlockList = new LinkedList<>();
        define_featherSack();
        define_MovingfeatherSack();
        define_Blocks(); 
    }
    
    private void adaptSounds(){
        GameOver = game.manager.get("Audio//Hercules - sounds//Game Over.mp3", Music.class);
        Game = game.manager.get("Audio//Hercules - sounds//Nature Sound.wav", Music.class);
        Game.setLooping(true);
        Game.setVolume(Main.vol);
    } 
    
    private void define_featherSack() {
        FeatherSack feather1, feather2, feather3, feather4, feather5;
        feather1 = new FeatherSack(3160 / Main.PPM, 320 / Main.PPM, world, this);
        feather2 = new FeatherSack(3420 / Main.PPM, 350 / Main.PPM, world, this);
        feather3 = new FeatherSack(3685 / Main.PPM, 350 / Main.PPM, world, this);
        feather4 = new FeatherSack(3950 / Main.PPM, 320 / Main.PPM, world, this);

        featherList.add(feather1);
        featherList.add(feather2);
        featherList.add(feather3);
        featherList.add(feather4);
    }

    private void define_MovingfeatherSack() {
        MovingFeather m1 = new MovingFeather(5200 / Main.PPM, 550 / Main.PPM, world, this, 1);
        MovingFeather m2 = new MovingFeather(5600 / Main.PPM, 550 / Main.PPM, world, this, 1);
        MovingFeather m3 = new MovingFeather(8000 / Main.PPM, 550 / Main.PPM, world, this, 1);
        MovingFeather m4 = new MovingFeather(8400 / Main.PPM, 550 / Main.PPM, world, this, 1);
        MovingFeather m5 = new MovingFeather(9800 / Main.PPM, 320 / Main.PPM, world, this, 2);
        MovingfeatherList.add(m1);
        MovingfeatherList.add(m2);
        MovingfeatherList.add(m3);
        MovingfeatherList.add(m4);
        MovingfeatherList.add(m5);
    }

    private void define_Blocks() {
        Block b1 = new Block(4360, 60, world);
        BlockList.add(b1);
    }

    /*End Constructor Methods*/
    
    /*Start Rendering Objects*/
    private void FeathersAndBlock(float dt) {
        for (int i = 0; i < featherList.size(); i++) {
            featherList.get(i).update(dt);
        }

        for (int i = 0; i < MovingfeatherList.size(); i++) {
            MovingfeatherList.get(i).update(dt);
        }

        for (int i = 0; i < BlockList.size(); i++) {
            BlockList.get(i).update(dt);
            BlockList.get(i).Block_Moving(4);
        }

        for (int i = 0; i < BlockList.size(); i++) {
            if (BlockList.get(i).blockDown == false) {
                if (BlockList.get(i).blockFinish == false) {
                    world.destroyBody(BlockList.get(i).b2body);
                    BlockList.get(i).blockFinish = true;
                }
            }
        }
    }
    
    private void renderFeatherSacks() {

        for (int i = 0; i < featherList.size(); i++) {

            if (featherList.get(i).featherCollsoin(player) == 1) {
                featherList.remove(i);
                HealthAttacker.FeatherSack.Num_of_feather_Destroyed++;
                hud.score+=10;
            } else {
                if (featherList.get(i).featherCollsoin(player) == 2) {
                    hud.featherHit();
                    featherList.get(i).draw(game.batch);
                } else {
                    featherList.get(i).draw(game.batch);
                }
            }
        }

        for (int i = 0; i < MovingfeatherList.size(); i++) {
            if (MovingfeatherList.get(i).order == 1 || MovingfeatherList.get(i).order == 2) {
                MovingfeatherList.get(i).Rope.draw(game.batch);
            }
            MovingfeatherList.get(i).featherMoving(player);
            if (MovingfeatherList.get(i).featherCollsoin(player) == 2) {
                hud.featherHit();
                MovingfeatherList.get(i).draw(game.batch);
            } else {
                if (MovingfeatherList.get(i).featherCollsoin(player) == 1) {
                                    //HerculesActionSound("Audio//Hercules - sounds//featherFinish.wav");
         
             SilverCoin silver3 = new SilverCoin (this,MovingfeatherList.get(i).getX() *Main.PPM -100  ,MovingfeatherList.get(i).getY() *Main.PPM +200 ,player,hud); silver3.move=true;
                silvercoin.add(silver3);
                MovingfeatherList.remove(i);
                                    hud.score+=15;
                } else {
                    MovingfeatherList.get(i).draw(game.batch);
                }
            }
        }
        for (int i = 0; i < BlockList.size(); i++) {
            BlockList.get(i).draw(game.batch);
        }
    }
    
    private void getbaby(WorldCreator creator) {
        for (int i = 0; i < creator.getBabyDragons().size; i++) {
            Rectangle recbaby = creator.getBabyDragons().get(i).getBoundingRectangle();
            if (recbaby.overlaps(lightningsword.getBoundingRectangle())) {
                if (lightningsword.Finish() == false) {
                    creator.getBabyDragons().get(i).Stap();
                }
            } else if (recbaby.overlaps(rightfireball.getBoundingRectangle())) {
                if (rightfireball.Finish() == false) {
                    creator.getBabyDragons().get(i).Stap();
                }

            } else if (recbaby.overlaps(leftfirball.getBoundingRectangle())) {
                if (leftfirball.Finish() == false) {
                    creator.getBabyDragons().get(i).Stap();
                }

            } else if (recbaby.overlaps(sonicsword.rightsonic.getBoundingRectangle()) || recbaby.overlaps(sonicsword.leftsonic.getBoundingRectangle()) || recbaby.overlaps(sonicsword.upsonic.getBoundingRectangle())) {
                if (sonicsword.Finish() == false) {
                    creator.getBabyDragons().get(i).Stap();
                }
            }
        }
    }
    
    @Override
    protected void updateCoins(){
        //golden coins
        for(int i=0;i<goldcoin.size();i++){
            goldcoin.get(i).update(player);
        }
        //silver coins
        for(int i=0;i<silvercoin.size();i++){
            silvercoin.get(i).update(player);
        }
    }
    @Override
    protected void renderCoins(){
        //Golden Coins
          for(int i=0;i<goldcoin.size();i++){
               goldcoin.get(i).draw(game.batch);
          }
        //Silver Coins
        for(int i=0;i<silvercoin.size();i++){
            silvercoin.get(i).draw(game.batch);
        }
}
    
    public void update(float dt) {
        handleInput();
        hud.update(dt);
        world.step(1 / 60f, 6, 2);

        updateCharacters(dt);

        getbaby(creator);
        timer.update();

        player.update(dt);
        updateSwords();
        updateFireBalls();
        piller.update(dt);
        updateCoins();
        juice.update(dt);
        Shield.update(dt);
        reachPoint.update(dt);
        
        FeathersAndBlock(dt);

        gameCam.update();
        renderer.setView(gameCam);

        cameraScoop(1000f, 23000f);
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
            renderCharacters();
            reachPoint.draw(game.batch);
            player.draw(game.batch);
            piller.draw(game.batch);
            juice.draw(game.batch);
            Shield.draw(game.batch);            
            renderSwords();
            renderCoins();
            renderFireball();
            renderFeatherSacks();
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        
        if (gameOver()) {
            Game.stop();
            game.setScreen(new GameOver(game));
            dispose();
        }
    }
    /*End Rendering Objects*/
    
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void show() {

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
        map.dispose();
        hud.dispose();
    }
}
