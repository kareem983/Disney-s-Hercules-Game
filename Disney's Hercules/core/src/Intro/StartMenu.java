
package Intro;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class StartMenu implements Screen{
    
    private Main game;
    private int buttonOffset;
    private int verticalPos;
    public Stage stage;
    private Skin skin;
    private Texture background;
    private Music music;
    
    public StartMenu(Main game) {
        music = game.manager.get("Audio//Hercules - sounds//IntroMainMenu.mp3", Music.class);
        music.setLooping(true);
        music.play();
        this.game = game;
        background = new Texture(Gdx.files.internal("Intros\\0.jpg"));
        
        buttonOffset = 20;
        verticalPos = 40;
        stage = new Stage();
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
        startGame.setPosition(Gdx.graphics.getWidth() / 2 - startGame.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 2*(startGame.getHeight() + buttonOffset) -verticalPos);
        startGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new Username(game, music));
                
            }
            
        });
        stage.addActor(startGame);
        
        TextButton gameInstructions = new TextButton("Game Instructions", skin);
        gameInstructions.setPosition(Gdx.graphics.getWidth() / 2 - startGame.getWidth() / 2, Gdx.graphics.getHeight() / 2 + (startGame.getHeight() + buttonOffset) -verticalPos);
        gameInstructions.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new GameInstruction(game));
            }
        });
        stage.addActor(gameInstructions);
        
        TextButton scoreBoard = new TextButton("Score Board", skin);
        scoreBoard.setPosition(Gdx.graphics.getWidth() / 2 - startGame.getWidth() / 2, Gdx.graphics.getHeight() / 2 -verticalPos);
        scoreBoard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new ScoreBoard(game));
            }
        });
        stage.addActor(scoreBoard);
        
        TextButton levelPassword = new TextButton("Level Password", skin);
        levelPassword.setPosition(Gdx.graphics.getWidth() / 2 - startGame.getWidth() / 2, Gdx.graphics.getHeight() / 2 - (startGame.getHeight() + buttonOffset) -verticalPos);
        levelPassword.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new LevelPassword(game));
            }
        });
        stage.addActor(levelPassword);
        
        TextButton  settings= new TextButton("Settings", skin);
        settings.setPosition(Gdx.graphics.getWidth() / 2 - startGame.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 2*(startGame.getHeight() + buttonOffset) -verticalPos);
        settings.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new Setting(game, music));
            }
        });
        stage.addActor(settings);
        
        TextButton exit = new TextButton("Exit", skin);
        exit.setPosition(Gdx.graphics.getWidth() / 2 - startGame.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 3*(startGame.getHeight() + buttonOffset) -verticalPos);
        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                System.exit(0);
            }
        });
        stage.addActor(exit);
    }
    
    @Override
    public void render(float arg0) {
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       game.batch.begin();
            game.batch.draw(background, 0, 0, game.WIDTH-620, game.HEIGHT-50);
       game.batch.end();
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
