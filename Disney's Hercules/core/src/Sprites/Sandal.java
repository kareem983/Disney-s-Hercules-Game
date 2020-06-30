package Sprites;

import MovingObjects.Hercules;
import com.Hercules.game.Main;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Sandal extends Sprite{

    public float x,y;
    public int counter;
    public Hercules herucle;
    public Music sound = Main.manager.get("Audio//Hercules - Voices//Hercules//Sandal.wav", Music.class );
    public Sandal(Texture texture,float x,float y,Hercules herucle) {
        super(texture);
        this.x=x;this.y=y;this.herucle=herucle;
        counter=0;
        setBounds(0, 0, 100/Main.PPM,180/Main.PPM);
        setPosition(x, y); 
    }

    public void update() {
          if(herucle.body.getPosition().x >x &&herucle.body.getPosition().x <x+100/Main.PPM && herucle.body.getPosition().y>y&&herucle.body.getPosition().y<y+180/Main.PPM && counter==0)
        { counter++;
        sound.setVolume(Main.vol);
            sound.play();
            setBounds(0,0,0,0);
            if(herucle.isRunningRight()){
                    herucle.body.setLinearVelocity(new Vector2(5f,0));}
            
            else{
                 herucle.body.setLinearVelocity(new Vector2(-5f,0));
            }
        }
    }
}