package Scenes;

import MovingObjects.Hercules;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD2 {

    private static Image image;
    private TextureRegion[] region;
    public Table table;
    public Stage stage;
    public Viewport viewport;
    public int i;
    public float statetimer, DrCounter;
    public Label Timer, TimerLabel, DR, DrLabel, scoreText, scoreLabel;
    private Label.LabelStyle font;
    private BitmapFont FONT;
    public int timer; 
    public static int score;
    public Hercules herucle;

    public HUD2(Hercules herucle, SpriteBatch sb) {
        this.herucle = herucle;
        i = 0;
        timer = 150;
        score = 0;
        DrCounter = 88.5f;
        statetimer = 0;
        FONT = new BitmapFont(Gdx.files.internal("Fonts\\HUD.fnt"));
        font = new Label.LabelStyle(FONT, null);
        scoreText = new Label("SCORE", font);
        scoreText.setFontScale(0.7f);
        scoreLabel = new Label(String.format("%3d", score), font);
        scoreLabel.setFontScale(0.7f);
        Timer = new Label("Timer", font);
        Timer.setFontScale(0.7f);
        DR = new Label("Distance Remained", font);
        DR.setFontScale(0.7f);
        DrLabel = new Label(String.format("%3f%s", DrCounter, " M"), font);
        DrLabel.setFontScale(0.7f);
        TimerLabel = new Label(String.format("%3d", timer), font);
        TimerLabel.setFontScale(0.7f);
        viewport = new FitViewport(Main.WIDTH, Main.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        table = new Table();
        table.top();
        table.setFillParent(true);
        region = new TextureRegion[6];
        region[0] = new TextureRegion(new Texture("Sprites//Level 2//HUD//Sand Glass.png"), 15, 10, 32, 56);
        region[1] = new TextureRegion(new Texture("Sprites//Level 2//HUD//Sand Glass.png"), 57, 10, 32, 56);
        region[2] = new TextureRegion(new Texture("Sprites//Level 2//HUD//Sand Glass.png"), 100, 10, 32, 56);
        region[3] = new TextureRegion(new Texture("Sprites//Level 2//HUD//Sand Glass.png"), 15, 79, 32, 56);
        region[4] = new TextureRegion(new Texture("Sprites//Level 2//HUD//Sand Glass.png"), 57, 79, 32, 56);
        region[5] = new TextureRegion(new Texture("Sprites//Level 2//HUD//Sand Glass.png"), 100, 79, 32, 56);
        image = new Image();
        table.add(Timer).padRight(-75f);
        table.add().padRight(200f);
        table.add(DR).padRight(200f);
        table.add(scoreText).padRight(200f);
        table.row();
        table.add(image).padLeft(-1f);
        table.add(TimerLabel).padRight(250f);
        table.add(DrLabel).padRight(200f);
        table.add(scoreLabel).padRight(200f);
        stage.addActor(table);
    }

    public void update(float dt) {
        try {
            if (i == 0)
                image.setDrawable(new TextureRegionDrawable(region[0]));
            
            statetimer += Gdx.graphics.getDeltaTime();
            if (statetimer > 0.15f) {
                image.setDrawable(new TextureRegionDrawable(region[i]));
                i++;
                if (i > 6) {
                    i = 1;
                    image.setDrawable(new TextureRegionDrawable(region[0]));
                }
                statetimer = 0;
            }
        } catch (IndexOutOfBoundsException e) {
            image.setDrawable(new TextureRegionDrawable(region[0]));
            i = 0;
        }
        
        DrCounter = 88.5f - herucle.body.getPosition().x;
        DrLabel.setText(String.format("%.0f%s", DrCounter, " M"));
        scoreLabel.setText(score);
    }
}
