
package Scenes;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameOver implements Screen{
    private Viewport viewport;
    private Stage stage;
    private Game game;
    private Texture background;
   
    public GameOver(Game game) {
        this.game = game;
        viewport = new StretchViewport(Main.WIDTH, Main.HEIGHT,  new OrthographicCamera());
        background = new Texture(Gdx.files.internal("Intros\\GameOver.jpg"));

        stage = new Stage(viewport, ((Main) game).batch);
        BitmapFont FONT= new BitmapFont(Gdx.files.internal("Fonts\\GameOver.fnt"));
        BitmapFont FONT2= new BitmapFont(Gdx.files.internal("Fonts\\playAgain.fnt"));
        Label.LabelStyle font = new LabelStyle(FONT, null);
        Label.LabelStyle font2 = new LabelStyle(FONT2, null);
        
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        
        Label gameOverLabel = new Label("GAME OVER", font);
        Label toMainMenuLabel = new Label("Press Any Key To Go To Main Menu", font2);
        
        table.add(gameOverLabel).expandX().padTop(300f);
        table.row();
        table.add(toMainMenuLabel).expandX().padTop(-50f);;
        
        stage.addActor(table);
        //playVideo();
    }
    /*
    private void playVideo(){
        try {
            Desktop.getDesktop().open(new File("Intros\\OpeningScene.mp4"));
            
        } catch (IOException ex) {
            Logger.getLogger(GameOver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    @Override
    public void render(float dt) {
       Gdx.gl.glClearColor(1, 1, 1, 1);
       Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.justTouched() || Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            game.setScreen(new PlayScreen((Main)game));
            dispose();
        }
        Main.batch.begin();
            Main.batch.draw(background, 0, 0, Main.WIDTH, Main.HEIGHT);
        Main.batch.end();
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
        background.dispose();
    }
    
}
