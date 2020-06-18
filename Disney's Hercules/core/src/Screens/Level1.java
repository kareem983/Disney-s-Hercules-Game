package Screens;

import Scenes.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.Hercules.game.Main;

/* LEVEL ONE SCREEN  */
public class Level1 extends PlayScreen {
    
    public Level1(Main game) {
        super(game, "Maps\\Level One\\HerculesMap.tmx");
        
        Game.play();
    }

    public void update(float dt) {
        handleInput();
        hud.update(dt);
        world.step(1 / 60f, 6, 2);

        updateCharacters(dt);

        getbaby(creator);
        sandal.update();
        timer.update();

        player.update(dt);
        updateSwords();
        updateFireBalls();
        piller.update(dt);
        updateCoins();
        juice.update(dt);
        Shield.update(dt);

        FeathersAndBlock(dt);

        gameCam.update();
        renderer.setView(gameCam);

        if (player.b2body.getPosition().x > 1000 / Main.PPM && player.b2body.getPosition().x < 23000 / Main.PPM) 
            gameCam.position.x = player.b2body.getPosition().x;
        
        if (player.b2body.getPosition().y < 470 / Main.PPM) 
            gameCam.position.y = player.b2body.getPosition().y + 255 / Main.PPM;
        
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
        player.draw(game.batch);
        piller.draw(game.batch);
        juice.draw(game.batch);
        sandal.draw(game.batch);
        Shield.draw(game.batch);
        renderSwords();
        rederCoinsAndFire();
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
        renderer.dispose();
        world.dispose();
        debuger.dispose();
        hud.dispose();
    }
        
}
