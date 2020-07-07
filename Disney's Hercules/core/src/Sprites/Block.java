
package Sprites;

import HealthAttacker.FeatherSack;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Block extends Sprite {
    private float posx ,posy ;
    public boolean blockDown ,blockFinish, once ;
    private World world ;
    public  Body body;
    private BodyDef bdef;
    private FixtureDef fdef;
    private int numOf_feathersack_destoyed_to_open;
    
    public  Block(float posx , float posy , World world, int num){
        this.posx =posx ;
        this.posy =posy;
        this.world = world ;
        once=false;
        numOf_feathersack_destoyed_to_open = num;
        setRegion(new Texture("Maps\\Level One\\Hock.png"));
        setBounds(0, 0, 546/ Main.PPM, 722/ Main.PPM);
        setPosition(posx/Main.PPM, posy/Main.PPM);
        blockDown = true ; blockFinish = false ;
        
        bdef = new BodyDef();
        fdef = new FixtureDef();
    
        defineT_satic_Block();
    }
    
        public void update(float dt){
                if(posy /Main.PPM >= 200/Main.PPM) blockDown=false ;
                 setPosition(posx/Main.PPM, posy/Main.PPM);
        
            if(FeatherSack.Num_of_feather_Destroyed >=numOf_feathersack_destoyed_to_open){
               if(posy/Main.PPM<400/Main.PPM) posy+= 2 ;
               blockFinish = true;
           }
            
            if (blockDown == false && blockFinish && !once) {
                    world.destroyBody(body);
                    once=true;
            }
    }
        
        public void defineT_satic_Block(){
            bdef = new BodyDef();
            bdef.position.set( ((posx+275)/ Main.PPM) ,( (posy+150) / Main.PPM));
            bdef.type = BodyDef.BodyType.StaticBody ;
            body =world.createBody(bdef) ;

            fdef = new FixtureDef();
            PolygonShape polygon = new PolygonShape();
            polygon.setAsBox(283/Main.PPM, 300  / Main.PPM);
            fdef.shape=polygon;
            body.createFixture(fdef);
        }

}
