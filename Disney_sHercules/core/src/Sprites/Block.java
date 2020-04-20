
package Sprites;

import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Block extends Sprite {
    private float posx ,posy ;
    public boolean blockDown ,blockFinish ;
    World world ;
         public  Body b2body;

      private Body body;
    private BodyDef bdef;
    private PolygonShape shape;
    private FixtureDef fdef;
  
    public  Block(float posx , float posy , World world){
        this.posx =posx ;
        this.posy =posy;
        this.world = world ;
        setRegion(new Texture("Map\\Hock.png"));
        setBounds(0, 0, 546/ Main.PPM, 722/ Main.PPM);
        setPosition(posx/Main.PPM, posy/Main.PPM);
        blockDown = true ; blockFinish = false ;
        
            bdef = new BodyDef();
        shape = new PolygonShape() ;
        fdef = new FixtureDef();
    
        defineT_satic_Block();
    }
    
    
        public void update(float dt){
                if(posy /Main.PPM >= 200/Main.PPM) blockDown=false ;
        setPosition(posx/Main.PPM, posy/Main.PPM);
        }
   public void Block_Moving(int numOf_feathersack_destoyrd_to_open){
       if(FeatherSack.Num_of_feather_Destroyed >=numOf_feathersack_destoyrd_to_open){
           if(posy/Main.PPM<400/Main.PPM) posy+= 1 ;
       }
   }
        public void defineT_satic_Block(){
          /*  Rectangle rec = this.getBoundingRectangle();
                bdef.type = BodyDef.BodyType.StaticBody ;
                bdef.position.set((rec.getX() + rec.getWidth() /2) / Main.PPM  , (rec.getY()+ rec.getHeight()/2) / Main.PPM  ) ;
                body =world.createBody(bdef) ;
                shape.setAsBox( (rec.getWidth()/2 ) / Main.PPM , (rec.getHeight() /2) / Main.PPM );
                fdef.filter.categoryBits = Main.GROUND_BIT;
                fdef.shape =shape ;
                body.createFixture(fdef ) ;
            */
          
                  bdef = new BodyDef();
        bdef.position.set( ((posx+105)/ Main.PPM) ,( (posy+150) / Main.PPM));
        bdef.type = BodyDef.BodyType.StaticBody ;
        b2body =world.createBody(bdef) ;

        fdef = new FixtureDef();
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(113/Main.PPM, 300  / Main.PPM);
    fdef.shape=polygon;
        b2body.createFixture(fdef);

        }
   

}