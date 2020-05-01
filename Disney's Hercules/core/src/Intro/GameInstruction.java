package Intro;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


//import com.sun.prism.paint.Color;



public class GameInstruction implements Screen{
    
    private Main game;
    private Texture texture[];
    private Sprite sprite[];
    private Viewport viewport;
    private OrthographicCamera gameCam;
    private Stage stage;
    private Table table;
    private Label.LabelStyle font1,font2;
    private BitmapFont FONT1,FONT2;
    private Label Text[];
   
    
    public GameInstruction( Main game) {
        this.game = game;
        texture=new Texture[15];
        sprite=new Sprite[20];
        Text=new Label[50];
        gameCam = new OrthographicCamera();
        viewport=new FitViewport(Main.WIDTH-620,Main.HEIGHT-50,gameCam);
        gameCam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2 , 0);
        stage=new Stage(viewport);
        table=new Table();
        table.top();
        table.setFillParent(true);
        FONT1= new BitmapFont(Gdx.files.internal("Fonts\\HUD.fnt"));
        font1 = new Label.LabelStyle(FONT1, null);
        FONT2= new BitmapFont(Gdx.files.internal("Fonts\\Menu.fnt"));
        font2 = new Label.LabelStyle(FONT2, null);
        
        LoadingImages();
        LoadingText();
    }
    
    
    private void LoadingImages(){
        //***************************Textures***********************************
        texture[0]=new Texture("Intros\\Game Instructions\\background.jpg");
        texture[1]=new Texture("Intros\\Game Instructions\\Hercules.png");
        texture[2]=new Texture("Intros\\Game Instructions\\Feather_Sack.png");
        texture[3]=new Texture("Intros\\Game Instructions\\tallPilar.png");
        texture[4]=new Texture("Intros\\Game Instructions\\babyDragons.png");
        texture[5]=new Texture("Intros\\Game Instructions\\FireballCannon.png");
        texture[6]=new Texture("Intros\\Game Instructions\\Shield.png");
        texture[7]=new Texture("Intros\\Game Instructions\\Juice.png");
        texture[8]=new Texture("Intros\\Game Instructions\\Coins.png");
        texture[9]=new Texture("Intros\\Game Instructions\\lightsword.png");
        texture[10]=new Texture("Intros\\Game Instructions\\fireball.png");
        texture[11]=new Texture("Intros\\Game Instructions\\SonicSword.png");
        texture[12]=new Texture("Intros\\Game Instructions\\sandal.png");
        //**********************************************************************
        

        //****************************Sprites***********************************
        //sprite[0] and [1] and [2] and [3] Backgrounds
        sprite[0]=new Sprite(texture[0]);
        sprite[0].setPosition(0, 0);
        sprite[0].setSize( game.WIDTH-620, game.HEIGHT-50);
        
        sprite[1]=new Sprite(texture[0]);
        sprite[1].setPosition(0, -750);
        sprite[1].setSize( game.WIDTH-620, game.HEIGHT-50);
        
        sprite[2]=new Sprite(texture[0]);
        sprite[2].setPosition(0, -1500);
        sprite[2].setSize( game.WIDTH-620, game.HEIGHT-50);
        
        sprite[3]=new Sprite(texture[0]);
          sprite[3].setPosition(0, -2520);
        sprite[3].setSize( game.WIDTH-620, 1039);
        
        //characters
        sprite[4]=new Sprite(texture[1]);
        sprite[4].setPosition(-50, 490);
        sprite[4].setSize(220, 180);
        
        sprite[5]=new Sprite(texture[2]);
        sprite[5].setPosition(15, -150);
        sprite[5].setSize(106, 186);
        
        sprite[6]=new Sprite(texture[3]);
        sprite[6].setPosition(-25, -550);
        sprite[6].setSize(200,400);
        
        sprite[7]=new Sprite(texture[4]);
        sprite[7].setPosition(-10, -700);
        sprite[7].setSize(206,120);
        
        sprite[8]=new Sprite(texture[5]);
        sprite[8].setPosition(30, -900);
        sprite[8].setSize(85,200);
        
        sprite[9]=new Sprite(texture[6]);
        sprite[9].setPosition(25, -1050);
        sprite[9].setSize(100,104);
        
        sprite[10]=new Sprite(texture[7]);
        sprite[10].setPosition(40, -1200);
        sprite[10].setSize(60,100);
        
        sprite[11]=new Sprite(texture[8]);
        sprite[11].setPosition(-20, -1350);
        sprite[11].setSize(200,100);
        
        sprite[12]=new Sprite(texture[9]);
        sprite[12].setPosition(-20, -1490);
        sprite[12].setSize(200,74);
        
        sprite[13]=new Sprite(texture[10]);
        sprite[13].setPosition(-20, -1700);
        sprite[13].setSize(150,100);
        
        sprite[14]=new Sprite(texture[11]);
        sprite[14].setPosition(-40, -1900);
        sprite[14].setSize(170,160);
        
        sprite[15]=new Sprite(texture[12]);
        sprite[15].setPosition(10, -2100);
        sprite[15].setSize(120,150);
        //**********************************************************************
    }
    
    
    
    private void LoadingText(){
       
        //*****************************Title************************************
        Text[0]=new Label("Game Instruction", font1);
        Text[0].setScale(0.2f);
        table.add(Text[0]).expandX().padTop(10).padLeft(50);
        table.row();
        //**********************************************************************
        
        
        //******************************Hercules********************************
        Text[1]=new Label("Hercules, son of Zeus,he is a true hero in order to regain his immortality and must pass several tasks", font2);
        Text[1].setScale(0.02f);
        Text[1].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[1].setFontScale(0.8f);
        table.add(Text[1]).expandX().padTop(50).padLeft(80);
        table.row();
        
        Text[2]=new Label("and defeat many villains, and at the end, face Hades,The player's health can be increased by picking up", font2);
        Text[2].setScale(0.02f);
        Text[2].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[2].setFontScale(0.8f);
        table.add(Text[2]).expandX().padTop(10).padLeft(100);
        table.row();
        
        Text[3]=new Label("hercules Action Figures(also known as Herculad)and drinking Herculade cups found throughout the game", font2);
        Text[3].setScale(0.02f);
        Text[3].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[3].setFontScale(0.8f);
        table.add(Text[3]).expandX().padTop(10).padLeft(100);
        table.row();
        
        Text[4]=new Label("the player's main weapon is the sword, Hercules can also punch, but this is a much more difficult way to", font2);
        Text[4].setScale(0.02f);
        Text[4].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[4].setFontScale(0.8f);
        table.add(Text[4]).expandX().padTop(10).padLeft(90);
        table.row();
        
        Text[5]=new Label("defeat enemies. Weapon powerups, known as Gifts of the Gods, can be found throughout the game            ", font2);
        Text[5].setScale(0.02f);
        Text[5].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[5].setFontScale(0.8f);
        table.add(Text[5]).expandX().padTop(10).padLeft(120);
        table.row();
        
        Text[6]=new Label("consisting of the Lightning Sword (shoots lightning any direction Hercules aims when selected and fired)", font2);
        Text[6].setScale(0.02f);
        Text[6].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[6].setFontScale(0.8f);
        table.add(Text[6]).expandX().padTop(10).padLeft(100);
        table.row();
        
        Text[7]=new Label("Fireball Sword (shoots fireballs that seek out onscreen enemies when selected and fired), and the Sonic", font2);
        Text[7].setScale(0.02f);
        Text[7].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[7].setFontScale(0.8f);
        table.add(Text[7]).expandX().padTop(10).padLeft(100);
        table.row();
        
        Text[8]=new Label("Sword (affects enemy targets at close range with a circular sonic blast when selected and fired). There", font2);
        Text[8].setScale(0.02f);
        Text[8].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[8].setFontScale(0.8f);
        table.add(Text[8]).expandX().padTop(10).padLeft(100);
        table.row();
        
        Text[9]=new Label("is also a helmet which grants the player a few seconds of invulnerability when activated. Powerups have", font2);
        Text[9].setScale(0.02f);
        Text[9].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[9].setFontScale(0.8f);
        table.add(Text[9]).expandX().padTop(10).padLeft(90);
        table.row();
        
        Text[10]=new Label("limited action, and once their energy has depleted, they can no longer be used.he also Has a health", font2);
        Text[10].setScale(0.02f);
        Text[10].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[10].setFontScale(0.8f);
        table.add(Text[10]).expandX().padTop(10).padLeft(30);
        table.row();
        
        Text[11]=new Label("bar that can increase or decrease.", font2);
        Text[11].setScale(0.02f);
        Text[11].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[11].setFontScale(0.8f);
        table.add(Text[11]).expandX().padTop(10).padLeft(-750);
        table.row();
        //**********************************************************************
        
        
        
        //******************************Feather sack****************************
        Text[12]=new Label("A feathers Sack (Punch Bag) that can be stable, moving along ropes (vertical /horizontal), or falling", font2);
        Text[12].setScale(0.02f);
        Text[12].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[12].setFontScale(0.8f);
        table.add(Text[12]).expandX().padTop(120).padLeft(80);
        table.row();
        
        Text[13]=new Label("suddenly from the sky. Some sacks can be destroyed with 1 hit, others may be destroyed with more", font2);
        Text[13].setScale(0.02f);
        Text[13].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[13].setFontScale(0.8f);
        table.add(Text[13]).expandX().padTop(10).padLeft(85);
        table.row();
        
        Text[14]=new Label("than one hit. They may produce coins or life shields and increases score.", font2);
        Text[14].setScale(0.02f);
        Text[14].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[14].setFontScale(0.8f);
        table.add(Text[14]).expandX().padTop(10).padLeft(-250);
        table.row();
        //**********************************************************************
        
        
        //***************************Tall pilar*********************************
        Text[15]=new Label("A tall Pillar that needs to be hit many times by Hercules.whene hercules hit it then", font2);
        Text[15].setScale(0.02f);
        Text[15].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[15].setFontScale(0.8f);
        table.add(Text[15]).expandX().padTop(150).padLeft(10);
        table.row(); 

        Text[16]=new Label(" the Helmet appears and hercules can move to continue.", font2);
        Text[16].setScale(0.02f);
        Text[16].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[16].setFontScale(0.8f);
        table.add(Text[16]).expandX().padTop(10).padLeft(-350);
        table.row();
        //**********************************************************************
        
        //*************************baby dragons*********************************
        Text[17]=new Label("Flying baby dragons that explode when destroyed by a single hit from Hercules.", font2);
        Text[17].setScale(0.02f);
        Text[17].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[17].setFontScale(0.8f);
        table.add(Text[17]).expandX().padTop(270).padLeft(-50);
        table.row();
        //**********************************************************************
        
        //****************************Cannons***********************************
        Text[18]=new Label("Fire ball shooting cannons that automatically shoots a fire ball every 5 seconds. These cannons", font2);
        Text[18].setScale(0.02f);
        Text[18].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[18].setFontScale(0.8f);
        table.add(Text[18]).expandX().padTop(120).padLeft(110);
        table.row();
        
        Text[19]=new Label("should be avoided by Hercules.", font2);
        Text[19].setScale(0.02f);
        Text[19].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[19].setFontScale(0.8f);
        table.add(Text[19]).expandX().padTop(10).padLeft(-650);
        table.row();
        //**********************************************************************
        
        //*****************************Helmet***********************************
        Text[20]=new Label("There is also a helmet which grants the player a few seconds of invulnerability when activated.", font2);
        Text[20].setScale(0.02f);
        Text[20].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[20].setFontScale(0.8f);
        table.add(Text[20]).expandX().padTop(130).padLeft(110);
        table.row();
        //**********************************************************************
        
        //*****************************juice***********************************
        Text[21]=new Label("Hercules juice that increases Hercules health.", font2);
        Text[21].setScale(0.02f);
        Text[21].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[21].setFontScale(0.8f);
        table.add(Text[21]).expandX().padTop(130).padLeft(-480);
        table.row();
        //**********************************************************************
        
        //*****************************Coins***********************************
        Text[22]=new Label("Silver and Golden coins. Silver are smaller than golden ones, Golden coin increase Hercules's", font2);
        Text[22].setScale(0.02f);
        Text[22].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[22].setFontScale(0.8f);
        table.add(Text[22]).expandX().padTop(90).padLeft(80);
        table.row();
        
        Text[23]=new Label("score by 10 and silver coin by 5", font2);
        Text[23].setScale(0.02f);
        Text[23].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[23].setFontScale(0.8f);
        table.add(Text[23]).expandX().padTop(5).padLeft(-650);
        table.row();
        //**********************************************************************
        
        //**************************Lightning sword*****************************
        Text[24]=new Label("Lightning sword: A sword with initial lightening effect that has a certain charge limit for usage", font2);
        Text[24].setScale(0.02f);
        Text[24].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[24].setFontScale(0.8f);
        table.add(Text[24]).expandX().padTop(70).padLeft(120);
        table.row();
        
        Text[25]=new Label("This Sword has a more powerful damaging effect than the Normal Sword (shoots lightning any", font2);
        Text[25].setScale(0.02f);
        Text[25].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[25].setFontScale(0.8f);
        table.add(Text[25]).expandX().padTop(5).padLeft(110);
        table.row();
        
        Text[26]=new Label("direction Hercules aims when selected and fired).", font2);
        Text[26].setScale(0.02f);
        Text[26].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[26].setFontScale(0.8f);
        table.add(Text[26]).expandX().padTop(5).padLeft(-430);
        table.row();
        //**********************************************************************
        
        //***************************fireball sword*****************************
        Text[27]=new Label("Fireball Sword (shoots fireballs that seek out onscreen enemies when selected and fired).", font2);
        Text[27].setScale(0.02f);
        Text[27].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[27].setFontScale(0.8f);
        table.add(Text[27]).expandX().padTop(120).padLeft(40);
        table.row();
        //**********************************************************************
        
        //***************************Sonic sword*****************************
        Text[28]=new Label("Sonic Sword (affects enemy targets at close range with a circular sonic blast", font2);
        Text[28].setScale(0.02f);
        Text[28].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[28].setFontScale(0.8f);
        table.add(Text[28]).expandX().padTop(120).padLeft(-120);
        table.row();
        
        Text[29]=new Label("when selected and fired).", font2);
        Text[29].setScale(0.02f);
        Text[29].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[29].setFontScale(0.8f);
        table.add(Text[29]).expandX().padTop(5).padLeft(-740);
        table.row();
        //**********************************************************************
        
        //***************************Sonic sword*****************************
        Text[30]=new Label("Speed Boosting for smashing Huge rock obstacles.", font2);
        Text[30].setScale(0.02f);
        Text[30].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[30].setFontScale(0.8f);
        table.add(Text[30]).expandX().padTop(140).padLeft(-440);
        table.row();
        //**********************************************************************
        
        //***************************Return to Menu*****************************
        Text[31]=new Label("Press Escape To Return To Main Menu", font2);
        Text[31].setScale(0.02f);
        Text[31].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[31].setFontScale(1.2f);
        table.add(Text[31]).expandX().padTop(110).padLeft(50);
        table.row();
        //**********************************************************************
        
        
        stage.addActor(table);
    }
    
    
    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //handle camera positions
         if (Gdx.input.isKeyPressed(Input.Keys.UP) &&gameCam.position.y<375){
              gameCam.position.y+=5;}
         else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) &&gameCam.position.y>-1900){
                  gameCam.position.y-=5;}

        //Handle to Exit
         else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
             gameCam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2 , 0);
             game.setScreen(new StartMenu(game));
             this.dispose();
       }
        
        gameCam.update();
        game.batch.setProjectionMatrix(gameCam.combined);
        
        game.batch.begin();
        //******************************background******************************
        sprite[0].draw(game.batch);
        sprite[1].draw(game.batch);
        sprite[2].draw(game.batch);
        sprite[3].draw(game.batch);
        //**********************************************************************

        //******************************characters******************************
        sprite[4].draw(game.batch);
        sprite[5].draw(game.batch);
        sprite[6].draw(game.batch);
        sprite[7].draw(game.batch);
        sprite[8].draw(game.batch);
        sprite[9].draw(game.batch);
        sprite[10].draw(game.batch);
        sprite[11].draw(game.batch);
        sprite[12].draw(game.batch);
        sprite[13].draw(game.batch);
        sprite[14].draw(game.batch);
        sprite[15].draw(game.batch);
        //**********************************************************************

        game.batch.end();
        game.batch.setProjectionMatrix(stage.getCamera().combined);
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
         for (int i = 0; i < 13; ++i)
             texture[i].dispose();
    }
    
}
