package Sprites;
import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

public class Letter extends Sprite {
     private World world;
     private PlayScreen screen;
     int posx, posy ;
     private static float displayTime=0;
     public static boolean GET[] = new boolean[8], LetterTaken;
     public  Sprite Letter , toBeCollected;
     private float x, y;
     private static int next=0, i=0;
     public int ID;
    
     public Letter(PlayScreen screen, float x,float y) {
          this.screen = screen;
          this.world = screen.getWorld();
          this.x=x; this.y=y;
          ID = next; next++; 
          
          char[] s = {'H', 'E', 'R', 'C', 'U', 'L', 'E', 'S'};
          
            Letter = new Sprite(new Texture("Sprites/Level 2/Name/"+s[ID]+".png"));            
            Letter.setPosition(x, y);
            Letter.setBounds(0, 0, 80 / Main.PPM, 80 / Main.PPM);
            Letter.setRegion(Letter.getTexture());
          
          toBeCollected = new Sprite(new Texture("Sprites/Level 2/Name/Remaining Letters.png"));
          toBeCollected.setBounds(0, 0, 1500 / Main.PPM, 80 / Main.PPM);
          toBeCollected.setRegion(toBeCollected.getTexture());
     }

    private void Collision(){
             if(Letter.getBoundingRectangle().overlaps(screen.getPlayer().getBoundingRectangle()) && !GET[ID]){
                 GET[ID] = LetterTaken = true;
                 Letter.setBounds(0, 0, 140f/Main.PPM, 80/Main.PPM);
             }
    }
    
    private void displayWord(){
         if(i==8)i=0;
        if(i==0)toBeCollected.draw(Main.batch);
        if(GET[ID]){
            Letter.setPosition(toBeCollected.getX() + ( (ID * 195) / (Main.PPM) ), toBeCollected.getY());        
            Letter.draw(Main.batch);
        }
        i++;
    }
    
     public void update(float dt) {
         Collision();
        
         Letter.setPosition(x, y);
         Letter.setRegion(Letter.getTexture());
         
         toBeCollected.setPosition(screen.getPlayer().body.getPosition().x -(750/Main.PPM) ,600/Main.PPM );
         
         if(LetterTaken){ // display the word for 3 seconds 
             if(displayTime>30){
                 LetterTaken=false;
                 displayTime=0;
             }
             else displayTime+=dt;
         }
     }
     
     public void draw(){
           if (GET[ID]==false) 
                    Letter.draw(Main.batch); 
          
          if (LetterTaken)displayWord();
     }
}
