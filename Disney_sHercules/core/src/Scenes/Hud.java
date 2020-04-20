
package Scenes;

import Sprites.Hercules;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Hud implements Disposable{
        public int i=0,counter=3;
        public Texture img1,img2;
        public Texture [] healthlevel;
        public TextureRegion[] t;
        public Sprite s;
        public Sprite []s1;
        public Sprite []s2;
        public Hercules herucle; 
        public Stage stage;
        public Viewport viewport;
        public Label scoreText;
        public Label scoreLabel;
        public Integer score;
        public boolean FireDecrease;
        public boolean DecreasePeriod;
                double count =0 ;
                public Label swordtimertext;
    public Label swordtimerlabel;
    public Float swordtimer;

        public Hud (Hercules herucle,SpriteBatch sb){
               this.herucle=herucle;
               this.FireDecrease=false;
               this.DecreasePeriod=true;
               
                img1= new Texture("sprites\\hud\\healthbar.gif");
                img2=new Texture("sprites\\hud\\hercules.png");
                healthlevel=new Texture[4];
                healthlevel[0]=new Texture("sprites\\hud\\health0.png");
                healthlevel[1]=new Texture("sprites\\hud\\health1.png");
                healthlevel[2]=new Texture("sprites\\hud\\health2.png");       
                healthlevel[3]=new Texture("sprites\\hud\\health3.png");
                t=new TextureRegion[6];
                t[0]=new TextureRegion(img2,770,310,110,15);
                t[1]=new TextureRegion(img2,770,310,90,15);
                t[2]=new TextureRegion(img2,770,310,70,15);
                t[3]=new TextureRegion(img2,770,310,50,15);
                t[4]=new TextureRegion(img2,770,310,30,15);
                t[5]=new TextureRegion(img2,770,310,15,15);
               //
                s=new Sprite(img1);
                s.setBounds(0,0,350/Main.PPM,60/Main.PPM);
                //
                s1=new Sprite[4];
                s1[0]=new Sprite(healthlevel[0]);
                s1[0].setBounds(0, 0,100/Main.PPM , 95/Main.PPM);
                s1[1]=new Sprite(healthlevel[1]);
                s1[1].setBounds(0, 0,100/Main.PPM , 95/Main.PPM);
                s1[2]=new Sprite(healthlevel[2]);
                s1[2].setBounds(0, 0,100/Main.PPM , 95/Main.PPM);
                s1[3]=new Sprite(healthlevel[3]);
                s1[3].setBounds(0, 0,100/Main.PPM , 95/Main.PPM);
                //
                s2=new Sprite[6];
                s2[0]=new Sprite(t[0]);
                s2[0].setBounds(0,0,242/Main.PPM,23/Main.PPM);
                s2[1]=new Sprite(t[1]);
                s2[1].setBounds(0,0,195/Main.PPM,23/Main.PPM);
                s2[2]=new Sprite(t[2]);
                s2[2].setBounds(0,0,145/Main.PPM,23/Main.PPM);
                s2[3]=new Sprite(t[3]);
                s2[3].setBounds(0,0,95/Main.PPM,23/Main.PPM);
                s2[4]=new Sprite(t[4]);
                s2[4].setBounds(0,0,55/Main.PPM,23/Main.PPM);
                s2[5]=new Sprite(t[5]);
                s2[5].setBounds(0,0,0/Main.PPM,23/Main.PPM);
               //
               score=0;
               swordtimer=0f;
               viewport=new FitViewport(Main.WIDTH,Main.HEIGHT, new OrthographicCamera());
               stage=new Stage(viewport, sb);
               
               Table table=new Table();
               table.top();
               table.setFillParent(true);
         
               scoreText = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        scoreText.setFontScale(2.5f);
        scoreLabel = new Label(String.format("%3d", score), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        scoreLabel.setFontScale(2.5f);
        swordtimertext = new Label("Sword Timer", new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        swordtimertext.setFontScale(2.5f);
        swordtimerlabel = new Label(String.format("%.0f", swordtimer), new Label.LabelStyle(new BitmapFont(), Color.CYAN));
        swordtimerlabel.setFontScale(2.5f);
          table.add().expandX().padTop(20);
        table.add(scoreText).expandX().padTop(20);
        table.add(swordtimertext).expandX().padTop(20);
        table.row();
        table.add().expandX().padTop(20);
        table.add(scoreLabel).expandX().padTop(20);
        table.add(swordtimerlabel).expandX().padTop(20);
        
        stage.addActor(table);

               
      }
    public void update()
    {
        s.setPosition(herucle.b2body.getPosition().x-600/Main.PPM,700/Main.PPM);
        s1[3].setPosition(herucle.b2body.getPosition().x-270/Main.PPM,680/Main.PPM);
        s2[0].setPosition(herucle.b2body.getPosition().x-500/Main.PPM,705/Main.PPM);
        fire_hit();
        scoreLabel.setText(String.format("%3d",score));
    }
    
    public void hit() {
          count++;
          if ( count > 10000 ) count=0;
        if (count % 15 ==0) {
            if(i<5){
            i++;
            s2[0]=s2[i];
            }    
            else 
            {
                 herucle.hercules_Die=true;
                if(counter>0){
                i=0;
                s2[0]=new Sprite(img2,770,310,110,15);
                s2[0].setBounds(0,0,242/Main.PPM,23/Main.PPM);
                counter--;
                s1[3]=s1[counter];
               
               }
            
            }
        }
    }
    
    public void fire_hit() {
        if (FireDecrease && DecreasePeriod) {
            if(i<5){
            i++;
            s2[0]=s2[i];
            }    
            else 
            {
                 herucle.hercules_Die=true;
                if(counter>0){
                i=0;
                s2[0]=new Sprite(img2,770,310,110,15);
                s2[0].setBounds(0,0,242/Main.PPM,23/Main.PPM);
                counter--;
                s1[3]=s1[counter];
               
               }
            
            }
        }
        FireDecrease=false;
    }
    
    @Override
    public void dispose() {
         stage.dispose();
    }
    
}
