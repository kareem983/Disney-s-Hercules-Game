
package Sprites;

import MovingObjects.Hercules;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class SonicSword extends Swords{
    public Rectangle recleftsonic,recrightsonic,recupsonic; 
    public SonicSword(float x, float y, Hercules herucle) {
        this.herucle = herucle;this.x=x;this.y=y;
        Tsword=new Texture("Sprites\\LeftSonicSword.png");
        leftsonic=new Sprite(Tsword);
        leftsonic.setBounds(0, 0, 500/Main.PPM, 500/Main.PPM);
        Tsword=new Texture("Sprites\\UpSonicSword.png");
        upsonic=new Sprite(Tsword);
        upsonic.setBounds(0, 0, 500/Main.PPM, 500/Main.PPM);
        Tsword=new Texture("Sprites\\RightSonicSword.png");
        rightsonic=new Sprite(Tsword);
        rightsonic.setBounds(0, 0, 500/Main.PPM,500/Main.PPM);
        //
        recleftsonic=leftsonic.getBoundingRectangle();
        recrightsonic=rightsonic.getBoundingRectangle();
        recupsonic=upsonic.getBoundingRectangle();
        
    }

    @Override
    public void update() {
        if(herucle.pickedsonicsword==false)
        {
            leftsonic.setBounds(0, 0, 0, 0);
            rightsonic.setBounds(0, 0, 0, 0);
            upsonic.setBounds(0, 0, 0, 0); 
        }
       
        statetimer+=Gdx.graphics.getDeltaTime();
        //
        leftsonic.setPosition(herucle.b2body.getPosition().x - getWidth() / 2 + 250/ Main.PPM-215 * 4 / Main.PPM, herucle.b2body.getPosition().y - getHeight() / 2 + 50 / Main.PPM);
        //
        rightsonic.setPosition(herucle.b2body.getPosition().x - getWidth() / 2, herucle.b2body.getPosition().y - getHeight() / 2 + 50 / Main.PPM);
        //
        upsonic.setPosition(herucle.b2body.getPosition().x - getWidth() / 2-300/Main.PPM , herucle.b2body.getPosition().y - getHeight() / 2 + 150 / Main.PPM);
       //
        if(statetimer>1)
        {
        leftsonic.setBounds(0, 0, 0, 0);
        rightsonic.setBounds(0, 0, 0, 0);
        upsonic.setBounds(0, 0, 0, 0);         
        }
        
    }
    
        public boolean Finish(){
        return (statetimer > 1) ;
    }

}
