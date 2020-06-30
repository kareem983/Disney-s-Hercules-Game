package Sprites;
import Screens.Level2;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class Word extends Sprite {
     World world;
     Level2 screen;
     int posx, posy ;
     private float displayTime;
     public boolean GET[], taken;
     public  Sprite Letters[] , toBeCollected;
     int x[], y[];
    
     public Word(World world, Level2 screen, int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4,int x5,int y5,int x6,int y6,int x7,int y7,int x8,int y8) {
          this.world = world;
          this.screen = screen;
          GET = new boolean[8]; taken = false; displayTime=0f;
          x = new int[8]; y = new int[8];
          this.x[0] = x1; this.y[0] = y1;
          this.x[1] = x2; this.y[1] = y2;
          this.x[2] = x3; this.y[2] = y3;
          this.x[3] = x4; this.y[3] = y4;
          this.x[4] = x5; this.y[4] = y5;
          this.x[5] = x6; this.y[5] = y6;
          this.x[6] = x7; this.y[6] = y7;
          this.x[7] = x8; this.y[7] = y8;
          
          Letters = new Sprite[8];
          Letters[0] = new Sprite(new Texture("Sprites/Level 2/Name/H.png"));
          Letters[1] = new Sprite(new Texture("Sprites/Level 2/Name/E.png"));
          Letters[2] = new Sprite(new Texture("Sprites/Level 2/Name/R.png"));
          Letters[3] = new Sprite(new Texture("Sprites/Level 2/Name/C.png"));
          Letters[4] = new Sprite(new Texture("Sprites/Level 2/Name/U.png"));
          Letters[5] = new Sprite(new Texture("Sprites/Level 2/Name/L.png"));
          Letters[6] = new Sprite(new Texture("Sprites/Level 2/Name/E.png"));
          Letters[7] = new Sprite(new Texture("Sprites/Level 2/Name/S.png"));

          for(int i=0;i<8; ++i){
            Letters[i].setPosition(x[i] / Main.PPM, y[i] / Main.PPM);
            Letters[i].setBounds(0, 0, 80 / Main.PPM, 80 / Main.PPM);
            Letters[i].setRegion(Letters[i].getTexture());
          }
          
          toBeCollected = new Sprite(new Texture("Sprites/Level 2/Name/Remaining Letters.png"));
          toBeCollected.setBounds(0, 0, 1500 / Main.PPM, 80 / Main.PPM);
          toBeCollected.setRegion(toBeCollected.getTexture());
     }

    private void Collision(){
        for (int i=0;i<8;i++){
             if(Letters[i].getBoundingRectangle().overlaps(screen.getPlayer().getBoundingRectangle())){
                 GET[i] = taken = true;
                 Letters[i].setBounds(0, 0, 140f/Main.PPM, 80/Main.PPM);
             }
        }
    }
    
    private void displayWord(){
        toBeCollected.draw(Main.batch);
        for (int i = 0; i < 8 ; ++i)
            if (GET[i]){
                Letters[i].setPosition(toBeCollected.getX() + ( (i * 195) / (Main.PPM) ), toBeCollected.getY());        
                Letters[i].draw(Main.batch);
            }
    }
    
     public void update(float dt) {
         Collision();
         for(int i = 0 ; i < 8; ++i){
             Letters[i].setPosition(x[i] / Main.PPM, y[i] / Main.PPM);
             Letters[i].setRegion(Letters[i].getTexture());
         }
         toBeCollected.setPosition(screen.getPlayer().body.getPosition().x -(750/Main.PPM) ,600/Main.PPM );
         
         if(taken){ // display the word for 3 seconds 
             if(displayTime>3){
                 taken=false;
                 displayTime=0;
             }
             else displayTime+=dt;
         }
     }
     
     public void draw(){
          for (int i=0;i<8;i++)
           if (GET[i]==false) 
                    Letters[i].draw(Main.batch); 
          
          if (taken)displayWord();
     }
}
