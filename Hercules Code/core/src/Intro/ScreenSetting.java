    package Intro;

import com.main.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
public class ScreenSetting implements Screen {

    private Main game;
    private Texture background;
    public Stage stage;
    private Skin skin1, skin2;
    private Viewport viewport;
    public ScreenSetting(Main game) {
        this.game = game;
        background = new Texture(Gdx.files.internal("Intros\\000.jpg"));
         viewport = new StretchViewport(Main.WIDTH, Main.HEIGHT,  new OrthographicCamera());
        stage = new Stage(viewport, ((Main) game).batch);
        Gdx.input.setInputProcessor(stage);   // MAKE THE STAGE ACCEPTS EVENTS
        createBasicSkin();
        Buttons();
    }

    private void createBasicSkin() {
        skin1 = new Skin(Gdx.files.internal("Fonts/uiskin.json"));

        BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts\\Menu.fnt"));
        skin2 = new Skin();
        skin2.add("default", font);

        // CREATE A TEXTURE
        Pixmap pixmap = new Pixmap((int) Gdx.graphics.getWidth() / 4, (int) Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.DARK_GRAY);
        pixmap.fill();
        skin2.add("Background", new Texture(pixmap));

        // CREATE A BUTTON STYLE
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin2.newDrawable("Background", Color.DARK_GRAY);
        textButtonStyle.down = skin2.newDrawable("Background", Color.YELLOW);
        textButtonStyle.checked = skin2.newDrawable("Background", Color.GREEN);
        textButtonStyle.over = skin2.newDrawable("Background", Color.BLUE);  // HOVER
        textButtonStyle.font = skin2.getFont("default");
        skin2.add("default", textButtonStyle);
    }
    void Buttons() {
        final SelectBox<String> selectBox = new SelectBox<String>(skin1);
        selectBox.setSize(340, 80);
        selectBox.setPosition(Gdx.graphics.getWidth() / 2 +150+Main.x, Gdx.graphics.getHeight() / 2 +120+Main.y );
        selectBox.setItems("1280x768", "1024x600", "800x340","Default","Full Screen");
        stage.addActor(selectBox);
        TextButton save = new TextButton("Save", skin2);
        save.setPosition(selectBox.getX() , selectBox.getY()-100);
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectBox.getSelectedIndex() == 0) {
                    Main.x=50;Main.y=50; 
                      Gdx.graphics.setWindowedMode(1280, 768);
                      
                }
                 else if (selectBox.getSelectedIndex() == 1) {
                     Main.x=100;Main.y=100; 
               Gdx.graphics.setWindowedMode(1024, 600);
                } else if (selectBox.getSelectedIndex() == 2) {
                    Main.x=300;Main.y=300;
                      Gdx.graphics.setWindowedMode(800, 340);
                      
                }
                 else if (selectBox.getSelectedIndex() == 3) {
                       Main.x=0;Main.y=0; 
                       Gdx.graphics.setWindowedMode(1366, 768);                                         
                }
                 else if(selectBox.getSelectedIndex() == 4)
                         {
                              Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                         }
                game.setScreen(new StartMenu(game));
                getThisClass().dispose();
            }
        });
        stage.addActor(save);
TextButton back = new TextButton("Back", skin2);
        back.setPosition(save.getX(),save.getY()-100);
        
        back.addListener(new ClickListener() {  // RESET DEFAULT
            @Override
            public void clicked(InputEvent event, float x, float y) { 
                game.setScreen(new Setting(game));
                getThisClass().dispose();
            }
        });
        stage.addActor(back);
    }
    
    
    private ScreenSetting getThisClass(){
        return this;
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Main.batch.begin();
        Main.batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
        Main.batch.end();

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
        skin1.dispose();
        skin2.dispose();
        background.dispose();
    }

}
