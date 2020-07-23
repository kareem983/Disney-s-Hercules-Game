package Screens;

import com.main.Main;
import Sprites.Block;
import Sprites.TallPiller;
import Sprites.Swords;
import Scenes.GameOver;
import Scenes.HUD;
import StaticGraphics.ReachPoint;
import Tools.Timer;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;

/* LEVEL ONE SCREEN  */
public class Level1 extends PlayScreen {
    
    public Block block;
    public TallPiller piller;
    private ReachPoint reachPoint;
    
    public Level1(Main game) {
        super(game, 750f, "Maps\\Level One\\HerculesMap.tmx"); // 750f
        
        hud = new HUD(this);
        createPauseMenu(hud.stage, hud.skin);
        timer = new Timer(player, hud);
        block = new Block(4360, 60, world, 4);
        piller = new TallPiller(this, 6660, 50);
        reachPoint = new ReachPoint(this);
        noSwords=false;
        adaptSounds();
        Game.play();
    }
    
    @Override
    public void restart() {
        player.body.setTransform(1100f/Main.PPM, 0.184f, 0f);
        stopHercAction=false;
        creator.restart();
        
        hud.score=0;
        hud.swordtimer=0f;
        piller.resetData();
        block.resetData();
        staticfireballsword.resetData();
        staticlightiningsword.resetData();
        staticsonicsword.resetData();
        leftfirball.resetData(); 
        rightfireball.resetData();
        lightningsword.resetData();
        sonicsword.resetData();
        timer.resetData();
        Shield.resetData();
        juice.resetData();
        restart =false;
    }
    
    /*Start Rendering Objects*/  
    public void update(float dt) {
        handleInput();
        hud.update(dt);
        world.step(1 / 60f, 6, 2);
        if(restart)restart();
        updateCharacters(dt);

        Swords.getbaby(this);
        timer.update();

        player.update(dt);
        updateSwords();
        updateFireBalls();
        updateFeathers(dt);
        updateCoins();
        block.update(dt);
        piller.update(dt);
        juice.update(dt);
        Shield.update(dt);
        reachPoint.update(dt);

        gameCam.update();
        renderer.setView(gameCam);
         
        if (checkGameOver() && hud.lifeCounter==0) {
            Game.stop();
            GameOver.setVolume(Main.vol);
            GameOver.play();
            creator.destroyBodies(this);
            game.setScreen(new GameOver(game));
            dispose();
        }
        cameraScoop(1000f, 23000f);
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            pauseWindow.setVisible(true);
            paused=true;
        }
        
    }

    @Override
    public void render(float delta) {
        if(!paused)update(delta);
        else if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
           paused=false;
           pauseWindow.setVisible(false);
       }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
        //debuger.render(world,gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();
            renderCharacters();
            reachPoint.draw(game.batch);
            player.draw(game.batch);
            if(Shield.draw)Shield.draw(game.batch);
            block.draw(game.batch);
            piller.draw(game.batch);
            if(juice.draw)juice.draw(game.batch);
            renderSwords();
            renderCoins();
            renderFireballs();
            renderFeathers();
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }
    /*End Rendering Objects*/
    
    protected boolean checkGameOver() {
        return ((player.getStateTimer() > 1.4f) && player.hercules_Die);
    }
    
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void show() {

    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        map.dispose();
        hud.dispose();
        FlameAtlas.dispose();
        TotalAtlas.dispose();
    }
    
}
