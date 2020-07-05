package Screens;

import Sprites.Slider;
import MovingObjects.*;
import Sprites.Border;
import Sprites.GoldenCoin;
import Sprites.Sandal;
import Sprites.Wall;
import Sprites.Word;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

/* LEVEL TWO SCREEN  */
public class Level2 extends PlayScreen {

    public Sandal sandal;
    private Camel camel;
    private ArrayList<Chicken> chicken = new ArrayList<>();

    private Word herculesWord;
    private Wolf wolf;

// YOUSSEF WALL
    private Wall wall;
    //

    public Level2(Main game) {                                                                                   // Start       // Wagon       // Slider        // End
        super(game, 1300.00f, "Maps\\Level Two\\HerculesMap.tmx"); // 1300.00f // 9400.00f // 42500.00f // 700000

//YOUSSEF WALL
        wall = new Wall(world, player, game, 2000, 30);
        //

        herculesWord = new Word(world, this, 2000, 800, 2200, 800, 2400, 800, 2600, 800, 2800, 800, 3000, 800, 3200, 800, 3400, 800);
        sandal = new Sandal(new Texture("sprites\\Level 1\\Complement\\sandal.png"), 100 / Main.PPM, 100 / Main.PPM, player); // Position Fix
//        wolf = new Wolf(this , 1600, 400 , 2500);
        noSwords = true;
        camel = new Camel(player, game);
        goldcoin.add(new GoldenCoin(this, 2000, 288, player, hud));
        chicken.add(new Chicken(1500f, 358f));

    }

    public void update(float dt) {
        handleInput();

        //YOUSSEF HUD2
        hud2.update(dt);
        //

        world.step(1 / 60f, 6, 2);

        for (int i = 0; i < chicken.size(); i++) {
            chicken.get(i).update();
        }
        for (int i = 0; i < goldcoin.size(); i++) {
            goldcoin.get(i).update(player);
        }

        player.update(dt);
        for (SecondaryCharacter wagon : creator.getWagons()) {
            wagon.update(dt);
        }
        for (Vulture vulture : creator.getVultures()) {
            vulture.update(dt);
        }

        herculesWord.update(dt);
//        wolf.update(dt);
        camel.update();
        sandal.update();

        //YOUSSEF WALL
        wall.HercuelesHit();
        //

        gameCam.update();
        renderer.setView(gameCam);

        //YOUSSEF Timer
        timer.update();
        //

        cameraScoop(1000f, 71000f);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.exit(0);
        }
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        game.batch.setProjectionMatrix(gameCam.combined);
        debuger.render(world, gameCam.combined);

        game.batch.begin();
        for (int i = 0; i < goldcoin.size(); i++) {
            goldcoin.get(i).draw(game.batch);
        }
        for (int i = 0; i < chicken.size(); i++) {
            chicken.get(i).draw(game.batch);
        }
        for (Border border : creator.getBorders()) {
            border.draw(game.batch);
        }
        for (Vulture vulture : creator.getVultures()) {
            vulture.draw(game.batch);
            vulture.egg.draw(game.batch);
        }

        //YOUSSEF WALL
        wall.draw();
        //

//         wolf.draw(game.batch);
        camel.draw(game.batch);
        sandal.draw(game.batch);

        //YOUSSEF WALL
        wall.drawCrushes();
        //

        player.draw(game.batch);

        //YOUSSEF WALL
        wall.drawExtra();
        //

        for (SecondaryCharacter wagon : creator.getWagons()) {
            wagon.draw(game.batch);
        }
        herculesWord.draw();
        game.batch.end();

        //YOUSSEF HUD2
        game.batch.setProjectionMatrix(hud2.stage.getCamera().combined);
        hud2.stage.draw();
        //

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
