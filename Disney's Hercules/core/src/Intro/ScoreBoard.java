
package Intro;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class ScoreBoard implements Screen{

    private Main game;
    private Texture background;
    private Stage stage;
    private Table table;
    private BitmapFont font, font1, font2;
    private Label rank, name, score, levels;
    private String NAME, SCORE;
    private boolean nameReaded, scoreReaded, once, empty;
    private int cnt;
    private int PlayersCnt;
    private int level;
    private int ScoreArr1[], ScoreArr2[];
    private String NameArr1[], NameArr2[];
            
    public ScoreBoard(Main game) {
        this.game = game;
        background = new Texture("Intros\\00.jpg");
        stage = new Stage();
        table = new Table();
        
        table.top().padTop(20f);
        table.setFillParent(true);
        
        font1 = new BitmapFont(Gdx.files.internal("Fonts\\score.fnt"));
        font2 = new BitmapFont(Gdx.files.internal("Fonts\\1st.fnt"));
        
        empty = false;
        /************ HEADER ROW ****************/ 
            addHeader();
        /******** READ FROM THE FILE *************/
            readFromFile();
            if (!empty){
        /******** SORT PLAYER SCORES ************/
            sortScores();
        /***** REWRITE THE SORTED FILE DATA ******/
            rewriteFile();
        /********* ADD PLAYERS DATA *************/
            addData();
        /*************************************/
            }
        
        
        stage.addActor(table);
    }
    
    private void addHeader() {
        rank = new Label("Rank", new Label.LabelStyle(font1, null));
        name = new Label("Name", new Label.LabelStyle(font1, null));
        score = new Label("Score", new Label.LabelStyle(font1, null));
        levels = new Label(" LevelsPassed", new Label.LabelStyle(font1, null));
        
        table.add(rank).expandX().padBottom(30f);
        table.add(name).expandX().padBottom(30f);
        table.add(score).expandX().padBottom(30f);
        table.add(levels).expandX().padBottom(30f);
        table.row();
    }

    private void readFromFile() {
        try{
            FileReader reader = new FileReader("..\\core\\assets\\scoresheet.txt");
            int c;
            while((c=reader.read()) != -1){
                PlayersCnt = Integer.valueOf((char)(c-48));
                break;
            }
        }catch(Exception ex){
             JOptionPane.showMessageDialog(null, ex);
        }
        if (PlayersCnt==0){
            empty=true;
            return;
        }
        cnt=0;
        NAME = new String("");
        SCORE = new String("");
        ScoreArr1 = new int [PlayersCnt];
        ScoreArr2 = new int [PlayersCnt];
        NameArr1 = new String[PlayersCnt];
        NameArr2 = new String[PlayersCnt];
        nameReaded = scoreReaded = once = false;
        boolean sizeReaded=false;
        try{
            FileReader reader = new FileReader("..\\core\\assets\\scoresheet.txt");
            int c;
            while((c=reader.read()) != -1){
                if(!sizeReaded){
                    sizeReaded=true;
                    continue;
                }
                if ((char) c == '-' && !nameReaded){
                    nameReaded = true;
                    scoreReaded = false;
                }
                else if (!nameReaded){
                    if (once)
                        NAME+=(char) c;
                    once=true;
                }
                else if ((char) c == '-' && !scoreReaded){
                    scoreReaded = true;
                    nameReaded = false;
                    once = false;
                    NameArr2[cnt] = NAME;
                    ScoreArr1[cnt] = ScoreArr2[cnt] = Integer.valueOf(SCORE);
                    NAME ="";
                    SCORE ="";
                    cnt++;
                }    
                else if (!scoreReaded)
                    SCORE+=(char) c;
            }
            reader.close();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    private void sortScores(){
         Arrays.sort(ScoreArr1);
         for (int i = 0 ; i < PlayersCnt; ++i)
                 for (int j = 0 ; j < PlayersCnt; ++j)
                     if (ScoreArr1[i] == ScoreArr2[j]){
                         NameArr1[i] = NameArr2[j];
                         ScoreArr2[j] = -1;
                         NameArr2[j] = "-1";
                         break;
                     }
    }
    
    private void rewriteFile() {
        
        try {
            FileWriter writer = new FileWriter("..\\core\\assets\\scoresheet.txt", false);
            writer.write(PlayersCnt+48);
            for(int i = PlayersCnt - 1; i >= 0; --i)
                writer.write("\n" + NameArr1[i] +"-" + ScoreArr1[i]+"-");
            writer.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    private void addData() {
        
        for (int i = PlayersCnt - 1; i > -1; --i){
            if (i==PlayersCnt-1)font=font2;
            else font=font1;
            rank = new Label("#" + (PlayersCnt -i), new Label.LabelStyle(font, null));
            name = new Label(NameArr1[i], new Label.LabelStyle(font, null));
            score = new Label(String.valueOf(ScoreArr1[i]), new Label.LabelStyle(font, null));
            level = (ScoreArr1[i] >= 800)? 2 : 1; 
            levels = new Label(String.valueOf(level), new Label.LabelStyle(font, null));

            table.add(rank).expandX();
            table.add(name).expandX();
            table.add(score).expandX();
            table.add(levels).expandX();
            table.row();  
        }
    }
    
    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
                game.setScreen(new StartMenu(game));
                this.dispose();
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
    public void resize(int w, int h) {
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
        if (font!=null)font.dispose();
        font1.dispose();
        font2.dispose();
    }

}
