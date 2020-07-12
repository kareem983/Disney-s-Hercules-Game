package Scenes;

import Intro.StartMenu;
import Screens.Level2;
import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Transition implements Screen {
    
    private Sprite sprite;
    private PlayScreen screen;
    private Main game;
    private Stage stage;
    private float time,alpha;
    private int score;
    private int timer;
    private static int totalScore;
    
    public  Transition(PlayScreen screen, int score){
        sprite = new Sprite(new Texture("Intros//Transition1.jpeg"));
        this.screen = screen;
        this.game = screen.game;
        this.score = score;
        this.timer=-1;
        this.totalScore = score;
        construct();
}
    
    public  Transition(PlayScreen screen, int score, int timer){
        sprite = new Sprite(new Texture("Intros//Transition2.jpeg"));
        this.screen = screen;
        this.game = screen.game;
        this.score = score;
        this.timer = timer;
        this.totalScore += (score + timer * 2);
        construct();
}
    
    private void construct(){
         StretchViewport viewport = new StretchViewport(Main.WIDTH, Main.HEIGHT,  new OrthographicCamera());
         stage = new Stage(viewport, ((Main) game).batch);
         BitmapFont FONT= new BitmapFont(Gdx.files.internal("Fonts\\PlayAgain.fnt"));
         BitmapFont FONT2= new BitmapFont(Gdx.files.internal("Fonts\\HUD2.fnt"));
         BitmapFont FONT3= new BitmapFont(Gdx.files.internal("Fonts\\menu.fnt"));
         
         Label.LabelStyle font = new Label.LabelStyle(FONT, null);
         Label.LabelStyle font2 = new Label.LabelStyle(FONT2, null);
         Label.LabelStyle font3 = new Label.LabelStyle(FONT3, null);
         
         //Skin skin = new Skin(Gdx.files.internal("Fonts/uiskin.json"));
         Table table = new Table();
         table.center();
         //table.setBackground(skin.getDrawable("dialogDim"));
         table.setSize(game.WIDTH - 200f, game.HEIGHT/2 - 200f);
         table.setPosition(game.WIDTH/16 , game.HEIGHT/4 - 20f);
         
         Label rowOneC2=null, rowTwoC2=null;
         Label rowOneC1 = new Label("LEVEL" + ( (timer!=-1)?" 2 ":" 1 " ) + "SCORE", font2);
         if(timer!=-1){ rowOneC2 = new Label("SAVED TIME", font2);}
         Label rowOneC3 = new Label("TOTAL SCORE", font);
         Label rowTwoC1 = new Label(String.format("%4d", score), font2);
         if(timer!=-1){rowTwoC2 = new Label(String.format("%4d", timer), font2);}
         Label rowTwoC3 = new Label(String.format("%6d", totalScore), font);
         String declaration="";
         if(timer!=-1)declaration="(Level 1 score + Level 2 score + 2xSaved Time)";
         Label rowThreeC3 = new Label(declaration, font3);
         
         table.add(rowOneC1).expandX().center();
         if(timer!=-1){table.add(rowOneC2).expandX();}
         table.add(rowOneC3).expand().padTop(0f).center();
         table.row(); // ROW ONE ^
         table.add(rowTwoC1).expandX().padLeft(-40f).center();
         if(timer!=-1){table.add(rowTwoC2).expandX().padTop(0f);}
         table.add(rowTwoC3).expand().padLeft(-80f).center();
         table.row(); // ROW TWO ^
         table.add().expandX().padTop(0f).center();
         if(timer!=-1){table.add().expandX();}
         table.add(rowThreeC3).maxWidth(0).padTop(-20f).center();
                                // ROW THREE ^
         
        stage.addActor(table);
        Buttons();
    }
    
    private void Buttons(){
        ImageButton backAndroid = new ImageButton (new TextureRegionDrawable(new TextureRegion(new Texture("Intros\\Back.png"))));
        backAndroid.setPosition(40f, game.HEIGHT/1.2f);
        backAndroid.addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y){
               screen.Victory.stop();
               game.setScreen(new StartMenu(game));
               stage.dispose();
           }
        });
        ImageButton nextAndroid = new ImageButton (new TextureRegionDrawable(new TextureRegion(new Texture("Intros\\Next.png"))));
        nextAndroid.setPosition(game.WIDTH - 120, game.HEIGHT/1.2f);
        nextAndroid.addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y){
              if(timer==-1) {screen.Victory.stop();game.setScreen(new Level2(game));}
              else {screen.Victory.stop();game.setScreen(new Credit());}
              stage.dispose();
           }
        });
        
        stage.addActor(backAndroid);
        stage.addActor(nextAndroid);
        Gdx.input.setInputProcessor(stage);
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        time+= delta;
        
        if (!Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.batch.begin();
                if (time < 2)  //FADE IN
                    alpha += (1f / 60f) / 2;
                else if (time > 5) { //FADE OUT
                    alpha -= (1f / 60f) / 2;
                    if(time>10f){
                        time=0;
                        alpha=0;
                    }
                }
                if(alpha<0)alpha=0;
                else if (alpha>1)alpha=1;
                sprite.setAlpha(alpha);
                sprite.setSize(game.WIDTH,game.HEIGHT);
                sprite.rotate(0);
                sprite.setPosition(0,0);
                sprite.setRegion(sprite.getTexture());

                sprite.draw(game.batch);
            game.batch.end();
            
            stage.draw();
        }
    }

    @Override
    public void show() {}
    
    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

}