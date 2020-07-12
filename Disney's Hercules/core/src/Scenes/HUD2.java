package Scenes;

import MovingObjects.Hercules;
import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD2 implements Disposable{

    private Image sandGlass, herculesIcon;
    private TextureRegion[] region;
    private Table table;
    public Stage stage;
    public Skin skin;
    private Viewport viewport;
    private Label Timer, TimerLabel, DR, DrLabel, scoreText, scoreLabel, trials, trialsNo;
    private Label.LabelStyle font;
    private BitmapFont FONT;
    public static int NumberOfTrials = 2, maxTime=200;
    public int i;
    public float statetimer, DrCounter, timeSpent;
    public static int timer; 
    public static int score;
    private Hercules herucle;
    private PlayScreen screen; 
    private Music ohBoy, countDown10;
    
    public HUD2(PlayScreen screen) {
        this.screen = screen;
        this.herucle = screen.getPlayer();
        i = 0;
        timer = maxTime;
        score = 0;
        DrCounter = 88.5f;
        statetimer = timeSpent = 0;
        FONT = new BitmapFont(Gdx.files.internal("Fonts\\HUD.fnt"));
        font = new Label.LabelStyle(FONT, null);
        scoreText = new Label("SCORE", font);
        scoreText.setFontScale(0.7f);
        scoreLabel = new Label(String.format("%3d", score), font);
        scoreLabel.setFontScale(0.7f);
        Timer = new Label("Timer", font);
        Timer.setFontScale(0.7f);
        trials = new Label("Trials", font);
        trials.setFontScale(0.7f);
        trialsNo = new Label(String.format("%3d", NumberOfTrials), font);
        trialsNo.setFontScale(0.7f);
        DR = new Label("Distance Remained", font);
        DR.setFontScale(0.7f);
        DrLabel = new Label(String.format("%3f%s", DrCounter, " M"), font);
        DrLabel.setFontScale(0.7f);
        TimerLabel = new Label(String.format("%3d", timer), font);
        TimerLabel.setFontScale(0.7f);
        viewport = new FitViewport(Main.WIDTH, Main.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, Main.batch);
        skin = new Skin(Gdx.files.internal("Fonts/uiskin.json"));
        table = new Table();
        region = new TextureRegion[6];
        region[0] = new TextureRegion(new Texture("Sprites//Level 2//HUD//Sand Glass.png"), 15, 10, 32, 56);
        region[1] = new TextureRegion(new Texture("Sprites//Level 2//HUD//Sand Glass.png"), 57, 10, 32, 56);
        region[2] = new TextureRegion(new Texture("Sprites//Level 2//HUD//Sand Glass.png"), 100, 10, 32, 56);
        region[3] = new TextureRegion(new Texture("Sprites//Level 2//HUD//Sand Glass.png"), 15, 79, 32, 56);
        region[4] = new TextureRegion(new Texture("Sprites//Level 2//HUD//Sand Glass.png"), 57, 79, 32, 56);
        region[5] = new TextureRegion(new Texture("Sprites//Level 2//HUD//Sand Glass.png"), 100, 79, 32, 56);
        sandGlass = new Image();
        herculesIcon = new Image(new Texture("Sprites//Level 2//HUD//Hercules2.png"));
        herculesIcon.setBounds(0, 650, 540f, 150f);
        
        table.add().spaceRight(500f);
        table.add(Timer).padRight(-75f);
        table.add().padRight(200f);
        table.add(DR).padRight(200f);
        table.add(scoreText).padRight(200f);
        table.add(trials);
        table.row();
        table.add().spaceRight(500f);
        table.add(sandGlass).padLeft(-1f);
        table.add(TimerLabel).padRight(250f);
        table.add(DrLabel).padRight(200f);
        table.add(scoreLabel).padRight(200f);
        table.add(trialsNo);
        
        table.setBackground(skin.getDrawable("dialogDim"));
        table.setSize(stage.getWidth(), 150);
        table.setPosition(0, stage.getHeight()-150);
        
        stage.addActor(table);
        stage.addActor(herculesIcon);
        
        ohBoy = Main.manager.get("Audio//Hercules - Voices//Hercules/Oh boy.wav",Music.class);
        countDown10 = Main.manager.get("Audio//Hercules - Voices//Cyclops//Count Down 10.mp3", Music.class);
    }

    public void update(float dt) {
        try {
            if (i == 0)
                sandGlass.setDrawable(new TextureRegionDrawable(region[0]));
            statetimer += dt;
            if (statetimer > 0.15f) {
                sandGlass.setDrawable(new TextureRegionDrawable(region[i]));
                i++;
                if (i > 6) {
                    i = 1;
                    sandGlass.setDrawable(new TextureRegionDrawable(region[0]));
                }
                statetimer = 0;
            }
        } catch (IndexOutOfBoundsException e) {
            sandGlass.setDrawable(new TextureRegionDrawable(region[0]));
            i = 0;
        }
        
        timeSpent += dt;
        if(timeSpent > 1){
            if(timer!=0 &&!screen.stopHercAction)timer--;
            timeSpent=0;
        }
        
        if (timer>-1)TimerLabel.setText(timer);
        else TimerLabel.setText(0);
        if (!screen.stopHercAction)DrCounter = 88.5f - herucle.body.getPosition().x;
        if (DrCounter<=0)DrCounter=0f;
        if (NumberOfTrials>-1)trialsNo.setText(NumberOfTrials);
        DrLabel.setText(String.format("%.0f%s", DrCounter, " M"));
        scoreLabel.setText(score);
        /********************************************/
        //  RESTART & GAME OVER & APPROACHING TO END OF TIME
        if (timer <= 0) {
                LooseTrial();
        }
        if (NumberOfTrials == -1) {
                GameOver();
        }
        if (timer == 10 && !countDown10.isPlaying()){
            countDown10.play();
            countDown10.setVolume(Main.vol);
        }
        /****************************************/
    }
    
    public void LooseTrial(){
        timer=0;
        if(NumberOfTrials>0) {
            NumberOfTrials--;
            screen.restart=true;
        }
        else if(NumberOfTrials==0) NumberOfTrials--;
        
        countDown10.stop();
    }
    
    public void GameOver(){
        herucle.hercules_Die=true;
        ohBoy.setVolume(Main.vol); 
        ohBoy.play();
        NumberOfTrials--;
        
        countDown10.stop();
    }
    
    @Override
    public void dispose(){
        stage.dispose();
        skin.dispose();
        FONT.dispose();
    }
}
