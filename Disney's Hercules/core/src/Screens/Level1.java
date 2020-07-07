package Screens;

import Sprites.Block;
import Sprites.TallPiller;
import Sprites.Hill;
import Sprites.Swords;
import Scenes.GameOver;
import StaticGraphics.ReachPoint;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.Hercules.game.Main;
import com.badlogic.gdx.audio.Music;

/* LEVEL ONE SCREEN  */
public class Level1 extends PlayScreen {
    
    private Block block;
    public TallPiller piller;
    public Hill hill;
    private ReachPoint reachPoint;
    
    public Level1(Main game) {
        super(game, 750f, "Maps\\Level One\\HerculesMap.tmx"); // 750f
        
        block = new Block(4360, 60, world, 4);
        piller = new TallPiller(world, this, player, 6660, 50);
        hill = new Hill(world, this, this.map);
        reachPoint = new ReachPoint(this);
        noSwords=false;
        adaptSounds();
        
        Game.play();
    }
    
    /*Start Constructor Methods*/ 
     private void adaptSounds(){
        GameOver = game.manager.get("Audio//Hercules - sounds//Game Over.mp3", Music.class);
        Game = game.manager.get("Audio//Hercules - sounds//Nature Sound.wav", Music.class);
        Game.setLooping(true);
        Game.setVolume(Main.vol);
    } 
    /*End Constructor Methods*/
    
    /*Start Rendering Objects*/  
    public void update(float dt) {
        handleInput();
        hud.update(dt);
        world.step(1 / 60f, 6, 2);

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
            Shield.draw(game.batch);
            block.draw(game.batch);
            piller.draw(game.batch);
            juice.draw(game.batch);
            renderSwords();
            renderCoins();
            renderFireballs();
            renderFeathers();
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
