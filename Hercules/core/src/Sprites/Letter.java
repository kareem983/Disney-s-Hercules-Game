package Sprites;

import com.main.Main;
import Screens.PlayScreen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Letter extends Sprite {
    
     private PlayScreen screen;
     private Animation animation;
     private float frameDuration;
     private static float displayTime=0;
     public static boolean GET[] = new boolean[8], LetterTaken;
     public boolean onlyOnce;
     public  Sprite Letter , toBeCollected;
     private Music music;
     private float x, y;
     private static int next=0, i=0;
     public int ID;
     
     public Letter(PlayScreen screen, float x,float y) {
          this.screen = screen;
          this.x=x; this.y=y;
          ID = next; next++; 
          onlyOnce=false;
          
          char[] s = {'H', 'E', 'R', 'C', 'U', 'L', 'E', 'S'};
          
          Array <TextureRegion> frames = new Array<TextureRegion>();
          Texture texture = new Texture("Sprites\\Level 2\\Name\\Letter"+s[ID]+".png");
            for(int i = 0; i < 2; ++i)
                frames.add(new TextureRegion(texture, i*126, 0, 126, 144));
            animation = new Animation(0.2f, frames);
            
            Letter = new Sprite(texture, 0, 0, 126, 144);            
            Letter.setPosition(x, y);
            Letter.setBounds(0, 0, 80 / Main.PPM, 80 / Main.PPM);
            Letter.setRegion(Letter.getTexture());
          
            toBeCollected = new Sprite(new Texture("Sprites/Level 2/Name/zRemaining Letters.png"));
            toBeCollected.setBounds(0, 0, 1500 / Main.PPM, 80 / Main.PPM);
            toBeCollected.setRegion(toBeCollected.getTexture());
            
            music = Main.manager.get("Audio//Hercules - Voices//Name//"+s[ID]+".mp3", Music.class);
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
            
            if(!onlyOnce){
              music.play();
              music.setVolume(Main.vol);  
              onlyOnce=true;
            }
        }
        i++;
    }
    
     public void update(float dt) {
         Collision();
        
         Letter.setPosition(x, y);
         
         toBeCollected.setPosition(screen.getPlayer().body.getPosition().x -(750/Main.PPM) ,600/Main.PPM );
         
         if(LetterTaken){ // display the word for 3 seconds 
             if(displayTime>30){
                 LetterTaken=false;
                 displayTime=0;
             }
             else displayTime+=dt;
         }
         
         frameDuration+=dt;
         Letter.setRegion((TextureRegion) animation.getKeyFrame(frameDuration, true));
     }
     
     public void draw(){
           if (GET[ID]==false) 
                    Letter.draw(Main.batch); 
          
          if (LetterTaken) displayWord();
     }
}
