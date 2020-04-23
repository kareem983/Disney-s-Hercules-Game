package Sprites;

import MovingObjects.Hercules;
import com.Hercules.game.Main;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Sandal extends Sprite{

    public float x,y;
    public int counter;
    public Hercules herucle;
    Sound sound = Main.manager.get("Audio//Hercules - Voices//Hercules//Sandal.wav", Sound.class );
    public Sandal(Texture texture,float x,float y,Hercules herucle) {
        super(texture);
        this.x=x;this.y=y;this.herucle=herucle;
        counter=0;
        setBounds(0, 0, 100/Main.PPM,180/Main.PPM);
        setPosition(x, y); 
    }

    public void update() {
          if(herucle.b2body.getPosition().x >x &&herucle.b2body.getPosition().x <x+100/Main.PPM && herucle.b2body.getPosition().y>y&&herucle.b2body.getPosition().y<y+180/Main.PPM && counter==0)
        { counter++;
            sound.play();
            setBounds(0,0,0,0);
            if(herucle.isRunningRight()){
                    herucle.b2body.setLinearVelocity(new Vector2(5f,0));}
            
            else{
                 herucle.b2body.setLinearVelocity(new Vector2(-5f,0));
            }
        }
    }
}