package Sprites;

import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Sandal extends Sprite{

    public float x,y;
    public int counter;
    public Hercules herucle;
    public Sandal(Texture texture,float x,float y,Hercules herucle) {
        super(texture);
        this.x=x;this.y=y;this.herucle=herucle;
        counter=0;
        setBounds(0, 0, 100/Main.PPM,180/Main.PPM);
        setPosition(x, y); 
    }

    public void update() {
          if(herucle.b2body.getPosition().x >x+25/Main.PPM && herucle.b2body.getPosition().y>y && counter==0&&herucle.b2body.getPosition().x<x+100/Main.PPM)
        { counter++;
            setBounds(0,0,0,0);
            if(herucle.isRunningRight()){
                    herucle.b2body.setLinearVelocity(new Vector2(5f,0));}
            
            else{
                 herucle.b2body.setLinearVelocity(new Vector2(-5f,0));
            }
        }
//         statetimer += Gdx.graphics.getDeltaTime();
//      
//        region = (TextureRegion) Asword.getKeyFrame(statetimer, true);
//        setRegion(region);
//        if (statetimer>5)statetimer=0;
    }
}
  
    
    

