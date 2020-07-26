package Scenes;

import MovingObjects.Hercules;
import Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
import com.main.Main;

public class HUD3 implements Disposable{
    public static int i,j;
    public Stage stage;
    public Skin skin;
    private static Image image, image2, image3, image4;
    private TextureRegion region, region2, region3, region4;
    private static String healthPath_herucle, healthPath_cyco, herucle_path, cyco_path;
    private static int X[], Y[], X2[], Y2[];
    public Label scoreText,scoreLabel;
    private BitmapFont FONT;
    private Label.LabelStyle font;
    public static Integer score;
    public static PlayScreen screen;
    public static boolean FireDecrease,CyclopsDecrease,CyclopsDie;
    public static Hercules herucle;
    public static Music music, x;
    
    
    public HUD3(PlayScreen screen) {
        this.screen=screen;
        herucle=screen.getPlayer();
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("Fonts/uiskin.json"));
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        FireDecrease=CyclopsDecrease=CyclopsDie=false;
        i=1;
        j=1;
        score=0;
        image = new Image();
        image2 = new Image();
        image3 = new Image();
        image4 = new Image();
        healthPath_herucle = "Sprites\\Level 3\\HUD\\Healthbar.png";
        healthPath_cyco = "Sprites\\Level 3\\HUD\\Healthbar2.png";
        herucle_path = "Sprites\\Level 3\\HUD\\herucle.png";
        cyco_path = "Sprites\\Level 3\\HUD\\cyco.png";
        X = new int[]{1, 1, 361, 1, 361, 361};
        Y = new int[]{271, 136, 271, 1, 136, 1};
        X2 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Y2 = new int[]{855, 760, 665, 570, 475, 380, 285, 190, 95, 0};
        region = new TextureRegion(new Texture(healthPath_herucle), X[0], Y[0], 358, 133);
        region2 = new TextureRegion(new Texture(healthPath_cyco), X2[0], Y2[0], 642, 89);
        region3 = new TextureRegion(new Texture(herucle_path), 0, 0, 80, 120);
        region4 = new TextureRegion(new Texture(cyco_path), 0, 0, 75, 75);
        image.setDrawable(new TextureRegionDrawable(region));
        image2.setDrawable(new TextureRegionDrawable(region2));
        image3.setDrawable(new TextureRegionDrawable(region3));
        image4.setDrawable(new TextureRegionDrawable(region4));
        
        FONT= new BitmapFont(Gdx.files.internal("Fonts\\HUD.fnt"));
        font = new Label.LabelStyle(FONT, null);
        scoreText = new Label("SCORE", font);
        scoreText.setFontScale(0.7f);
        scoreLabel = new Label(String.format("%3d", score),font);
        scoreLabel.setFontScale(0.7f);
        
        
        music = Main.manager.get("Audio//Hercules - sounds//Hercules_Atacked.wav",Music.class);
        x = Main.manager.get("Audio//Hercules - Voices//Cyclops//Run run magets.wav",Music.class);

        
        table.add(image).padTop(stage.getHeight()-140f);
        table.add(image3).padTop(stage.getHeight()-140f).padRight(600f);
        table.add(scoreText).expandX().padTop(stage.getHeight()-165f).padLeft(-500);
        table.add(image4).padTop(stage.getHeight()-140f);
        table.add(image2).padTop(stage.getHeight()-140f);
        table.add(scoreLabel).expandX().padTop(stage.getHeight()-60).padLeft(-1480);
      
        table.setBackground(skin.getDrawable("dialogDim"));
        table.setSize(stage.getWidth(), 150);
        table.setPosition(0, stage.getHeight()-150);
     
        
        stage.addActor(table);
        
    }

    public void update(float dt) {
        fire_hit();
        Cyco_hit();
        scoreLabel.setText(String.format("%3d",score));
        
    }

    
    public void fire_hit() {
         if (FireDecrease) hercules_hit();
         FireDecrease=false;
    }
    
    public void Cyco_hit() {
         if (CyclopsDecrease) Cycolps_hit();
         CyclopsDecrease=false;
    }

    public static void hercules_hit() {
           if(i<6){
                image.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(healthPath_herucle), X[i], Y[i++], 358, 133)));
                music.setVolume(Main.vol); 
                music.play();
            }
            
            else {
                herucle.hercules_Die=true;
                i=0;
                screen.stopHercAction=true;
            }
    }

    
    
    public static void Cycolps_hit() {
        if(j==3||j==8){
             x.setVolume(Main.vol); 
             x.play();
            
        }
        if(j<10){
                image2.setDrawable(new TextureRegionDrawable(new TextureRegion(new Texture(healthPath_cyco), X2[0], Y2[j++], 642, 89)));
            }
            
            else {
                CyclopsDie=true;
            }
    
    }

    @Override
    public void dispose() {
       stage.dispose();
       skin.dispose();

    }
 
    
}
