package Intro;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ControlerSetting implements Screen {

    private Main game;
    private Texture background;
    public Stage stage;
    private Viewport viewport;
    private Skin skin;
    private BitmapFont FONT;
    private Label.LabelStyle font;
    private TextField[] field;
    
    public ControlerSetting(Main game) {
        this.game = game;
        background = new Texture(Gdx.files.internal("Intros\\0.jpg"));
        viewport = new StretchViewport(Main.WIDTH, Main.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) game).batch);
        Gdx.input.setInputProcessor(stage);
        FONT = new BitmapFont(Gdx.files.internal("Fonts\\HUD.fnt"));
        font = new Label.LabelStyle(FONT, null);// MAKE THE STAGE ACCEPTS EVENTS
        field = new TextField[8];

        createBasicSkin();
        Buttons();
        handleActions();
    }

    void Buttons() {

        Label up = new Label("UP", font);
        up.setPosition(Gdx.graphics.getWidth() / 2 - 500 + Main.x, Gdx.graphics.getHeight() / 2 + 260 + Main.y);
        Label right = new Label("RIGHT", font);
        right.setPosition(up.getX(), up.getY() - 80);
        Label down = new Label("DOWN", font);
        down.setPosition(right.getX(), right.getY() - 80);
        Label left = new Label("LEFT", font);
        left.setPosition(down.getX(), down.getY() - 80);
        Label sword1 = new Label("SWORD 1", font);
        sword1.setPosition(left.getX(), left.getY() - 80);
        Label sword2 = new Label("SWORD 2", font);
        sword2.setPosition(sword1.getX(), sword1.getY() - 80);
        Label punsh = new Label("NORMAL PUNSH", font);
        punsh.setPosition(sword2.getX(), sword2.getY() - 80);
        Label powerPunsh = new Label("POWER PUNSH", font);
        powerPunsh.setPosition(punsh.getX(), punsh.getY() - 80);
        stage.addActor(up);
        stage.addActor(right);
        stage.addActor(down);
        stage.addActor(left);
        stage.addActor(sword1);
        stage.addActor(sword2);
        stage.addActor(punsh);
        stage.addActor(powerPunsh);
        
        field[0] = new TextField("", new Skin(Gdx.files.internal("Fonts\\uiskin.json")));   // UP
        field[0].setPosition(up.getX() + 600, up.getY());
        
        field[1] = new TextField("", new Skin(Gdx.files.internal("Fonts\\uiskin.json")));   //RIGHT
        field[1].setPosition(right.getX() + 600, right.getY());

        
        field[2] = new TextField("", new Skin(Gdx.files.internal("Fonts\\uiskin.json"))); //LEFT
        field[2].setPosition(down.getX() + 600, down.getY());

        
        field[3] = new TextField("", new Skin(Gdx.files.internal("Fonts\\uiskin.json"))); //DOWN
        field[3].setPosition(left.getX() + 600, left.getY());

        
        field[4] = new TextField("", new Skin(Gdx.files.internal("Fonts\\uiskin.json"))); // SWORD 1
        field[4].setPosition(sword1.getX() + 600, sword1.getY());

        
        field[5] = new TextField("", new Skin(Gdx.files.internal("Fonts\\uiskin.json")));  // SWORD 2
        field[5].setPosition(sword2.getX() + 600, sword2.getY());

        field[6] = new TextField("", new Skin(Gdx.files.internal("Fonts\\uiskin.json"))); // NORMAL PUNSH
        field[6].setPosition(punsh.getX() + 600, punsh.getY());

        field[7] = new TextField("", new Skin(Gdx.files.internal("Fonts\\uiskin.json")));  //POWER PUNCH
        field[7].setPosition(powerPunsh.getX() + 600, powerPunsh.getY());
        
        for (int i = 0; i < field.length; ++i){
            field[i].setAlignment(Align.center);
            field[i].setSize(300, 50);
            
            stage.addActor(field[i]);
        }
        
        final TextButton save = new TextButton("Save Changes", skin);
        save.setPosition(Gdx.graphics.getWidth() / 2 + 600 + Main.x, Gdx.graphics.getHeight() / 2 + Main.y);
        
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                
                if ((field[0].getText().isEmpty() || field[1].getText().isEmpty() || field[2].getText().isEmpty() || field[3].getText().isEmpty() || field[4].getText().isEmpty() || field[5].getText().isEmpty() || field[6].getText().isEmpty() || field[7].getText().isEmpty())) {
                   
                } 
                else {
                     
                    game.setScreen(new StartMenu(game));
                    getThisClass().dispose();
                }
            }
        });
        stage.addActor(save);
        
        TextButton back = new TextButton("Back", skin);
        back.setPosition(Gdx.graphics.getWidth() / 2 + 600 + Main.x, Gdx.graphics.getHeight() / 2 - 100 + Main.y);
        
        back.addListener(new ClickListener() {  // RESET DEFAULT
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.up = 19;
                game.right = 22;
                game.down = 20;
                game.left = 21;
                game.sword1 = 52;
                game.sword2 = 54;
                game.normalPunch = 31;
                game.powerPunch = 50;
                
                game.setScreen(new Setting(game));
                getThisClass().dispose();
            }
        });
        stage.addActor(back);
    }
    
    private ControlerSetting getThisClass(){
        return this;
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

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);       
        game.batch.begin();
        game.batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
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
        background.dispose();
        FONT.dispose();
    }

    public void handleActions(){
        
        for (int i =0; i < field.length; ++i)
        {
                final int k = i;
                    field[k].addListener(new InputListener() {
                        @Override
                        public boolean keyDown(InputEvent event, int keycode) {
                            field[k].setText("");
                            field[k].appendText(Keys.toString(keycode));
                            
                            switch (k) {
                                case 0:
                                    game.up = keycode;
                                    break;
                                case 1:
                                    game.right = keycode;
                                    break;
                                case 2:
                                    game.down = keycode;
                                    break;
                                case 3:
                                    game.left = keycode;
                                    break;
                                case 4:
                                    game.sword1 = keycode;
                                    break;
                                case 5:
                                    game.sword2 = keycode;
                                    break;
                                case 6:
                                    game.normalPunch = keycode;
                                    break;
                                case 7:
                                    game.powerPunch = keycode;
                                    break;
                                default:
                                    break;
                            }
                            return super.keyDown(event, keycode);
                        }
                    });
        }
    }
    
}
