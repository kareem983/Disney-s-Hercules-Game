package Intro;

import com.main.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

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
    private int LabelCount;
    private ImageButton backBtn;
    private boolean backBtnIsClicked;
    
    public GameInstruction( Main game) {
        this.game = game;
        texture=new Texture[18];
        sprite=new Sprite[18];
        Text=new Label[68];
        LabelCount=0;
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
        backBtnIsClicked=false;
        
        backBtn = new ImageButton (new TextureRegionDrawable(new TextureRegion(new Texture("Intros\\Back.png"))));

        LoadingImages();
        LoadingText();
        
        SO();
        Gdx.input.setInputProcessor(stage);
    }
    
    private void SO(){
        BackBtn();
    }
    
    public void BackBtn(){
        backBtn.setPosition( gameCam.position.x/150.0f , gameCam.position.y+250);
        backBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new StartMenu(game));
                getThisClass().dispose();
            }
        });
        stage.addActor(backBtn);
    }
    
    private GameInstruction getThisClass(){return this;}
    
    private void LoadingImages(){
        //***************************Textures***********************************
        texture[0]=new Texture("Intros\\Game Instructions\\Background2.jpg");
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
        texture[13]=new Texture("Intros\\Game Instructions\\Wall.png");
        texture[14]=new Texture("Intros\\Game Instructions\\Wagon.png");
        texture[15]=new Texture("Intros\\Game Instructions\\Vulture.png");
        texture[16]=new Texture("Intros\\Game Instructions\\Letters.png");
        texture[17]=new Texture("Intros\\Game Instructions\\Camel.png");
        //**********************************************************************
        
        //****************************SPRITES***********************************
        //sprite[0] to [5] Backgrounds
        sprite[0]=new Sprite(texture[0]);
        sprite[0].setPosition(0, 0);
        sprite[0].setSize( game.WIDTH-620, game.HEIGHT-50);
        
        //characters
        sprite[1]=new Sprite(texture[1]); // Hercules
        sprite[1].setPosition(-20, 410);
        sprite[1].setSize(220, 180);
        
        sprite[2]=new Sprite(texture[2]); // Feather Sack
        sprite[2].setPosition(30, -390);
        sprite[2].setSize(90, 170);
        
        sprite[3]=new Sprite(texture[3]); // Tall Pillar
        sprite[3].setPosition(-10, -750);
        sprite[3].setSize(180,380);
        
        sprite[4]=new Sprite(texture[4]); // Baby Dragon
        sprite[4].setPosition(10, -880);
        sprite[4].setSize(150,150);
        
        sprite[5]=new Sprite(texture[5]); // Fireball
        sprite[5].setPosition(30, -1050);
        sprite[5].setSize(85,200);
        
        sprite[6]=new Sprite(texture[6]); // Shield
        sprite[6].setPosition(25, -1200);
        sprite[6].setSize(100,104);
        
        sprite[7]=new Sprite(texture[7]); // Herculad
        sprite[7].setPosition(45, -1350);
        sprite[7].setSize(60,100);
        
        sprite[8]=new Sprite(texture[8]); // Coin
        sprite[8].setPosition(20, -1500);
        sprite[8].setSize(150,75);
        
        sprite[9]=new Sprite(texture[9]); // Lightening Sword
        sprite[9].setPosition(20, -1720);
        sprite[9].setSize(140,80);
        
        sprite[10]=new Sprite(texture[10]); // Fireball Sword
        sprite[10].setPosition(20, -1940);
        sprite[10].setSize(150,100);
        
        sprite[11]=new Sprite(texture[11]); // Sonic Sword
        sprite[11].setPosition(-40, -2140);
        sprite[11].setSize(220,160);
        
        sprite[12]=new Sprite(texture[12]); // Sandal
        sprite[12].setPosition(30, -2340);
        sprite[12].setSize(120,150);
        
        sprite[13]=new Sprite(texture[13]); // Wall 
        sprite[13].setPosition(10, -2800);
        sprite[13].setSize(140,150);
        
        sprite[14]=new Sprite(texture[14]); // Wagon
        sprite[14].setPosition(30, -2965);
        sprite[14].setSize(100,100);
        
        sprite[15]=new Sprite(texture[15]); // Vulture
        sprite[15].setPosition(20, -3200);
        sprite[15].setSize(120,150);
        
        sprite[16]=new Sprite(texture[16]); // Word
        sprite[16].setPosition(40, -3320);
        sprite[16].setSize(150,50);
        
        sprite[17]=new Sprite(texture[17]); // Camel
        sprite[17].setPosition(30, -3560);
        sprite[17].setSize(120,150);
        //**********************************************************************
    }
    
    private void LoadingText(){
       
        //*****************************Title************************************
        Text[LabelCount]=new Label("Game Instruction", font1);
        Text[LabelCount].setScale(0.2f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(50);
        table.row(); LabelCount++;
        //**********************************************************************
        
        //******************************Hercules********************************
        Text[LabelCount]=new Label("Hercules, son of Zeus,he is a true hero in order to regain his immortality and must pass several tasks", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(50).padLeft(80);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("and defeat many villains, and at the end, face Hades,The player's health can be increased", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(100);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("by picking up hercules Action Figures(also known as Herculad)and drinking Herculade cups", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(100);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("found throughout the game, The player's main weapon is the sword, Hercules can also punch,", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(90);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("But this is a much more difficult way to defeat enemies. Weapon powerups, known as Gifts", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(120);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("of the Gods, Can be found throughout the game consisting of ", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(100);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Lightning Sword (shoots lightning any direction Hercules aims when selected and fired)", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(100);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Fireball Sword (shoots fireballs that seek out onscreen enemies when selected and fired)", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(100);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Sonic Sword (affects enemy targets at close range with a circular sonic blast when selected and fired)", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(60);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Thereis also a helmet which grants the player a few seconds of invulnerability when activated.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(30);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Powerups have limited action, and once their energy has depleted, they can no longer be used.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(-50);
        table.row();LabelCount++; 
        
        Text[LabelCount]=new Label("He also Has a health bar that can increase or decrease.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(-50);
        table.row();LabelCount++;
        //**********************************************************************
        
        //*************************Level 1 Description**************************
        Text[LabelCount]=new Label("Level 1 Description", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.9f);
        table.add(Text[LabelCount]).expandX().padTop(50).padLeft(40);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("You are the Mighty Hercules, Here Your Main Target is to Practise Before Facing Cyclops ", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(100);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("In Achieving High Scores By Collecting Coins, Destroying Feather Sacks or even by", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(-20);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Killing Baby Dragons, And Offcourse Avoid Dieing as You Have Only 3 Lifes.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(-20);
        table.row();LabelCount++;
        //**********************************************************************
        
        //******************************Feather sack****************************
        Text[LabelCount]=new Label("Feather Sack", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(80).padLeft(150);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label(" (Punch Bag) that can be stable, moving along ropes (vertical /horizontal), or falling", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("suddenly from the sky. Some sacks can be destroyed with 1 hit, others may be destroyed with more", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(85);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("than one hit. They may produce coins or life shields and increases score.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(-250);
        table.row();LabelCount++;
        //**********************************************************************
        
        //***************************Tall pilar*********************************
        Text[LabelCount]=new Label("Tall Pillar", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(120).padLeft(150);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label(" That needs to be hit many times by Hercules.whene hercules hit it then", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]);
        table.row(); LabelCount++;

        Text[LabelCount]=new Label(" The Helmet appears and hercules can move to continue.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(-350);
        table.row();LabelCount++;
        //**********************************************************************
        
        //*************************baby dragons*********************************
        Text[LabelCount]=new Label("Baby Dragon", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(140).padLeft(160);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Flying baby dragons that explode when destroyed by a single hit from Hercules.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(150);
        table.row();LabelCount++;
        //**********************************************************************
        
        //****************************Fireball***********************************
        Text[LabelCount]=new Label("Fireball", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(80).padLeft(160);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Fire ball shooting cannons that automatically shoots a falling fire ball every 5 seconds.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(120);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("These cannons should be avoided by Hercules.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padLeft(20).padTop(10).padLeft(-500);
        table.row();LabelCount++;
        //**********************************************************************
        
        //*****************************Shield***********************************
        Text[LabelCount]=new Label("Shield", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(80).padLeft(160);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Which grants the player a few seconds of invulnerability when activated.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(-20);
        table.row();LabelCount++;
        //**********************************************************************
        
        //*****************************Herculad***********************************
        Text[LabelCount]=new Label("Herculad", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(120).padLeft(160);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Hercules juice that increases Hercules health.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(-350);
        table.row();LabelCount++;
        //**********************************************************************
        
        //*****************************Coins***********************************
        Text[LabelCount]=new Label("Coin", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(80).padLeft(180);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Silver and Golden coins. Silver are smaller than golden ones,", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(-200);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Golden coin increase Hercules's score by 10 and silver coin by 5", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(-200);
        table.row();LabelCount++;
        //**********************************************************************
        
        //**************************Lightning sword*****************************
        Text[LabelCount]=new Label("Lightening Sword", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(80).padLeft(180);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Lightning sword: A sword with initial lightening effect that has a certain charge limit for usage", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(120);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("This Sword has a more powerful damaging effect than the Normal Sword (shoots lightning any", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(5).padLeft(110);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("direction Hercules aims when selected and fired).", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(5).padLeft(-430);
        table.row();LabelCount++;
        //**********************************************************************
        
        //***************************fireball sword*****************************
        Text[LabelCount]=new Label("Fireball Sword", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(80).padLeft(180);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Shoots fireballs that seek out onscreen enemies when selected and fired.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(100);
        table.row();LabelCount++;
        //**********************************************************************
        
        //***************************Sonic sword*****************************
        Text[LabelCount]=new Label("Sonic Sword", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(80).padLeft(180);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Affects enemy targets at close range with a circular sonic blast", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(-120);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("when selected and fired.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(5).padLeft(-600);
        table.row();LabelCount++;
        //**********************************************************************
        
        //***************************Sandal*****************************
        Text[LabelCount]=new Label("Sandal", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(100).padLeft(180);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Speed Boosting for speeding up Hercules run.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(-350);
        table.row();LabelCount++;
        
        //*************************Description Level 2**************************
        Text[LabelCount]=new Label("Level 2 Description", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.9f);
        table.add(Text[LabelCount]).expandX().padTop(120).padLeft(80);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Mighty Hercules, In Continueing Your Series of Practicing, In this Level You Have To Face Time Rushing", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(20).padLeft(20);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("You Have To Reach The Camel who Exists in The End Of Your Road By The End of Time, But Keep in Mind ", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(50);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("You Have To Collect All (HERCULES) Letters.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(-80);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("And After That You Are Now Ready For Facing Cyclops The Monster.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(-100);
        table.row();LabelCount++;
        //**********************************************************************
     
        //********************************Wall**********************************
        Text[LabelCount]=new Label("Wall", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(80).padLeft(140);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Needs to be hit many times by Hercules and hercules is able to jumb on it to get tokens", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(60);
        table.row();LabelCount++;
      
        Text[LabelCount]=new Label("or anything that increase the score. Whene hercules hit it then he can to continue his Series of risk.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(80);
        table.row();LabelCount++;  
        //**********************************************************************
    
        //*******************************Wagon**********************************
        Text[LabelCount]=new Label("Wagon", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(70).padLeft(140);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Heavy four-wheeled vehicle pulled automatically when hercules jumb on it,this wagon moves", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(90);
        table.row();LabelCount++;
      
        Text[LabelCount]=new Label("faster than hercules's movment so this wagon help hercules to reach the end of the level before the", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(5).padLeft(60);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("timer reach to the end and hercules can't do any thing when he is on it but only can jumb up.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(5).padLeft(-10);
        table.row();LabelCount++;
        //**********************************************************************
     
        //******************************Vulture*********************************
        Text[LabelCount]=new Label("Vulture", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(60).padLeft(140);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("A large scavenging bird that The eggs fall from it and if hercules make a collision", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(20);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("with these eggs the level timer will decress with ten seconds.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).expandX().padTop(10).padLeft(-370);
        table.row();LabelCount++;
        //**********************************************************************
        
        //******************************Characters******************************
        Text[LabelCount]=new Label("Hercules Word", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(70).padLeft(220);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Eight Characters that hercules should collect it to enable him to win this level,", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(200);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Without collecting all these characters hercules hasn't the capability to win this level.", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(-150);
        table.row();LabelCount++;
        //**********************************************************************
        
        //******************************Camel***********************************
        Text[LabelCount]=new Label("Camel", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).left().padTop(80).padLeft(150);
        table.row();LabelCount++;
        
        Text[LabelCount]=new Label("Moves when Hercules jumb on it, Heading down to Level 3...", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.GOLD);
        Text[LabelCount].setFontScale(0.8f);
        table.add(Text[LabelCount]).padLeft(-200);
        table.row();LabelCount++;
        //**********************************************************************
         
        //***************************Return to Menu*****************************
        Text[LabelCount]=new Label("Press Escape To Return To Main Menu", font2);
        Text[LabelCount].setScale(0.02f);
        Text[LabelCount].setColor(com.badlogic.gdx.graphics.Color.RED);
        Text[LabelCount].setFontScale(1.2f);
        table.add(Text[LabelCount]).expandX().padTop(100).padLeft(50);
        table.row(); 
        //**********************************************************************
       
        stage.addActor(table);
    }
    
    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        backBtn.setPosition( gameCam.position.x/20.0f , gameCam.position.y+250);
        
        //Handle to Exit
        if (backBtnIsClicked || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            gameCam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2 , 0);
            game.setScreen(new StartMenu(game));
           // System.exit(0);
            this.dispose();
        }
        
        //handle camera positions
         if (Gdx.input.isKeyPressed(Input.Keys.UP) &&gameCam.position.y<375)
              gameCam.position.y+=5;
         else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) &&gameCam.position.y>-3360)
                  gameCam.position.y-=5;
        
        gameCam.update();
        game.batch.setProjectionMatrix(gameCam.combined);
        
        game.batch.begin();
        //******************************background******************************
        sprite[0].draw(game.batch);
        sprite[0].setPosition(0, gameCam.position.y-375);
        LabelCount=0;
        //******************************characters******************************
        sprite[1].draw(game.batch);
        sprite[2].draw(game.batch);
        sprite[3].draw(game.batch);
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
        sprite[16].draw(game.batch);
        sprite[17].draw(game.batch);
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
         FONT1.dispose();
         FONT2.dispose();
         for (int i = 0; i < 18; ++i)
             texture[i].dispose();
         
    }
}
