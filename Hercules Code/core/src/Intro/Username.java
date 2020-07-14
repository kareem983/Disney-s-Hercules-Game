
package Intro;

import com.main.Main;
import Screens.Level1;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Username implements Screen{
    
    private Main game;
    private Stage stage;
    private ImageButton back;
    private Label.LabelStyle FONT;
    private Skin skin;
    private Label enter;
    private Label message;
    private TextField username;
    private TextButton play;
    private Music music;
    private Viewport viewport;
    private Texture background;
    
    public Username( Main game, Music music) {
        this.game = game;
        this.music = music;
        background = new Texture(Gdx.files.internal("Intros\\0000.jpg"));
        viewport = new StretchViewport(game.WIDTH, game.HEIGHT,  new OrthographicCamera());
        stage = new Stage(viewport, ((Main) game).batch);
        backButton();
        createBasicSkin();
        createWidgets();
        Gdx.input.setInputProcessor(stage);
    }

    private void backButton(){
        back = new ImageButton (new TextureRegionDrawable(new TextureRegion(new Texture("Intros\\Back.png"))));
        back.setPosition(80f, Main.HEIGHT/1.2f);
        back.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new StartMenu(game));
                stage.dispose();
            }
        });
        stage.addActor(back);
    }

    private void createBasicSkin() {
        BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts\\Menu.fnt"));
        FONT = new Label.LabelStyle(font, null);
        skin = new Skin();
        skin.add("default", font);

        // CREATE A TEXTURE
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 8, Pixmap.Format.RGB888);
        pixmap.setColor(Color.DARK_GRAY);
        pixmap.fill();
        skin.add("Background", new Texture(pixmap));

        // CREATE A BUTTON STYLE
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("Background", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("Background", Color.GREEN);
        textButtonStyle.checked = skin.newDrawable("Background", Color.RED);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        // CREATE A TEXT FIELD STYLE
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.background = skin.newDrawable("Background", Color.DARK_GRAY);
        textFieldStyle.fontColor =  Color.GOLD;
        //textFieldStyle.cursor
        textFieldStyle.font = skin.getFont("default");
        skin.add("default", textFieldStyle);
    }

    private void createWidgets(){
        enter = new Label("Please Enter Your Name !", FONT);
        enter.setSize(400, 50);
        enter.setPosition(game.WIDTH/2-enter.getWidth()/2 - 50, game.HEIGHT/2 + 100);
        enter.setFontScale(1.4f);
        
        stage.addActor(enter);

        username = new TextField("", skin);
        username.setSize(400, 50);
        username.setAlignment(Align.center);
        username.setPosition(game.WIDTH/2-username.getWidth()/2, game.HEIGHT/2 + 0);
        
        stage.addActor(username);

        play = new TextButton("Play", skin);
        play.setSize(400, 50);
        play.setPosition(game.WIDTH/2-play.getWidth()/2, game.HEIGHT/2 - 60);

        message = new Label("", FONT);
        message.setPosition(game.WIDTH/2 - play.getWidth()/2 + 60, game.HEIGHT/2 - 130);
        message.setSize(400, 50);
        message.setColor(Color.RED);
        message.setFontScale(0.8f);

        play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (username.getText().length() < 3) {
                    message.setText("Are You Even Trying ?!\nPlease Enter A Real Name.");
                }
                else {
                    music.stop();
                    game.username = username.getText();
                    game.setScreen(new Level1(game));
                    getObjectClass().dispose();    
                }
            }
        });
        stage.addActor(play);
        stage.addActor(message);
    }
    
    private Username getObjectClass(){
        return this;
    }
    
    @Override
    public void render(float dt) {
       Gdx.gl.glClearColor(0, 0, 0, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
       if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
           game.setScreen(new StartMenu(game));
       }
       
       game.batch.begin();
        game.batch.draw(background, 0, 0, game.WIDTH, game.HEIGHT);
       game.batch.end();
       
       stage.act();
       stage.draw();
    }

    @Override
    public void show() {
    }
    
    @Override
    public void resize(int width, int height) {
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
        skin.dispose();
    }
    
}
