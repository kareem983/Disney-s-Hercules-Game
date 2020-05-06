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
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SoundAndMusicSetting implements Screen {

    private Main game;
    private Texture background;
    public Stage stage;
    private Skin skin;
    private Viewport viewport;
    private BitmapFont FONT;
    private Label.LabelStyle font;
    public CheckBox cb;

    public SoundAndMusicSetting(Main game) {
        cb = new CheckBox("Mute", new Skin(Gdx.files.internal("Fonts\\uiskin.json")));
        this.game = game;
        background = new Texture(Gdx.files.internal("Intros\\000.jpg"));
        viewport = new StretchViewport(Main.WIDTH, Main.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Main) game).batch);
        Gdx.input.setInputProcessor(stage);   // MAKE THE STAGE ACCEPTS EVENTS
        FONT = new BitmapFont(Gdx.files.internal("Fonts\\HUD.fnt"));
        font = new Label.LabelStyle(FONT, null);
        if (Main.vol == 0) {
            cb.setChecked(true);
        } else {
            cb.setChecked(false);
        }
        createBasicSkin();
        Buttons();
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

    void Buttons() {
        Label MusicAndSound = new Label("Music And Sound Volume", font);
        MusicAndSound.setPosition(Gdx.graphics.getWidth() / 2 -50+ Main.x, Gdx.graphics.getHeight() / 2 + 180 + Main.y);
        stage.addActor(MusicAndSound);

        final TextButton volumeup = new TextButton("volume up", skin);
        final TextButton volumedown = new TextButton("volume down", skin);
        volumeup.setPosition(MusicAndSound.getX(), MusicAndSound.getY() - 100);
        volumeup.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Main.vol < 1) {
                    Main.vol += 0.1f;
                    volumedown.setText("volume down");
                    volumedown.setTouchable(Touchable.enabled);
                } else {
                    volumeup.setText("Maximum volume");
                    volumeup.setTouchable(Touchable.disabled);
                }

            }
        });
        stage.addActor(volumeup);

        volumedown.setPosition(MusicAndSound.getX() + 400, MusicAndSound.getY() - 100);
        volumedown.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Main.vol > 0.2f) {
                    Main.vol -= 0.1f;
                    volumeup.setText("volume up");
                    volumeup.setTouchable(Touchable.enabled);
                } else {
                    volumedown.setText("Minimum volume");
                    volumedown.setTouchable(Touchable.disabled);
                }

            }
        });
        stage.addActor(volumedown);

        cb.setPosition(volumeup.getX() + 220, volumeup.getY() - 100);
        cb.setSize(300, 50);
        cb.scaleBy(2f);
        cb.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (cb.isChecked()) {
                    Main.vol = 0;
                } else {
                    Main.vol = 1;
                }
            }
        });

        stage.addActor(cb);
        TextButton save = new TextButton("Save", skin);
        save.setPosition(MusicAndSound.getX(), cb.getY() - 100);
        save.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new StartMenu(game));
            }
        });
        stage.addActor(save);
        TextButton back = new TextButton("Back", skin);
        back.setPosition(MusicAndSound.getX() + 400,  cb.getY() - 100);

        back.addListener(new ClickListener() {  // RESET DEFAULT
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Setting(game));
            }
        });
        stage.addActor(back);
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
    }

}
