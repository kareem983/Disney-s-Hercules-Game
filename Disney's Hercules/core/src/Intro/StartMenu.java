package Intro;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StartMenu implements Screen {

    private Main game;
    public Stage stage;
    private Skin skin;
    private Texture background;
    private Music music;
    private Viewport viewport;
    public StartMenu(Main game) {
        music = game.manager.get("Audio//Hercules - sounds//IntroMainMenu.mp3", Music.class);
        music.setVolume(Main.vol);        
        music.play();
        this.game = game;
        background = new Texture(Gdx.files.internal("Intros\\0.jpg"));
        viewport = new StretchViewport(Main.WIDTH, Main.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) game).batch);
        Gdx.input.setInputProcessor(stage);   // MAKE THE STAGE ACCEPTS EVENTS

        createBasicSkin();
        createActions();
        
    }

    private void createBasicSkin() {
        BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts\\Menu.fnt"));
        skin = new Skin();
        skin.add("default", font);

        // CREATE A TEXTURE
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.DARK_GRAY);
        pixmap.fill();
        skin.add("Background", new Texture(pixmap));

        // CREATE A BUTTON STYLE
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("Background", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("Background", Color.YELLOW);
        textButtonStyle.checked = skin.newDrawable("Background", Color.GREEN);
        textButtonStyle.over = skin.newDrawable("Background", Color.BLUE);  // HOVER
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
    }

    private void createActions() {
        TextButton startGame = new TextButton("Start Game", skin);
        startGame.setPosition(Gdx.graphics.getWidth() / 2 +150+Main.x, Gdx.graphics.getHeight() / 2 +200+Main.y);
        startGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Username(game, music));
                getThisClass().dispose();
            }
        });
        stage.addActor(startGame);

        TextButton gameInstructions = new TextButton("Game Instructions", skin);
        gameInstructions.setPosition(startGame.getX() , startGame.getY()-100);
        gameInstructions.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameInstruction(game));
                getThisClass().dispose();
            }
        });
        stage.addActor(gameInstructions);

        TextButton levelPassword = new TextButton("Level Password", skin);
        levelPassword.setPosition(gameInstructions.getX() ,gameInstructions.getY()-100);
        levelPassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                music.stop();
                game.setScreen(new LevelPassword(game));
                getThisClass().dispose();
            }
        });
        stage.addActor(levelPassword);

        TextButton scoreBoard = new TextButton("Score Board", skin);
        scoreBoard.setPosition(levelPassword.getX() , levelPassword.getY()-100);
        scoreBoard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new ScoreBoard(game));
                getThisClass().dispose();
            }
        });
        stage.addActor(scoreBoard);

        TextButton settings = new TextButton("Settings", skin);
        settings.setPosition(scoreBoard.getX() ,scoreBoard.getY()-100);
        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Setting(game));
                getThisClass().dispose();
            }
        });
        stage.addActor(settings);

        TextButton exit = new TextButton("Exit", skin);
        exit.setPosition(settings.getX() , settings.getY()-100);
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
                getThisClass().dispose();
            }
        });
        stage.addActor(exit);
    }
    private StartMenu getThisClass(){
        return this;
    }

    @Override
    public void render(float arg0) {
        music.setVolume(Main.vol);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Main.batch.begin();
            game.batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
        Main.batch.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int arg0, int arg1) {
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
        stage.dispose();
    }

}
