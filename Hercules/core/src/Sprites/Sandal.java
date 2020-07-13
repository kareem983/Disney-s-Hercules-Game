package Sprites;

import com.main.Main;
import MovingObjects.Hercules;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Sandal extends Sprite{
    public int counter;
    public float x,y;
    public boolean draw;
    public Hercules herucle;
    public Music sound = Main.manager.get("Audio//Hercules - Voices//Hercules//Sandal.wav", Music.class );
    
    public Sandal(float x,float y,Hercules herucle) {
        super(new Texture("Sprites\\Level 1\\Complement\\Sandal.png"));
        this.x=x;this.y=y;this.herucle=herucle;
        counter=0; draw =true;
        setBounds(0, 0, 100/Main.PPM,120/Main.PPM);
        setPosition(x, y); 
    }

    public void update() {
          if(herucle.body.getPosition().x >x &&herucle.body.getPosition().x <x+100/Main.PPM && herucle.body.getPosition().y>y&&herucle.body.getPosition().y<y+180/Main.PPM&&draw){ 
            sound.setVolume(Main.vol);
            sound.play();
            draw=false;
            if(herucle.isRunningRight()){
                    herucle.body.setLinearVelocity(new Vector2(5f,0));}
            
            else{
                 herucle.body.setLinearVelocity(new Vector2(-5f,0));
            }
        }
    }
}