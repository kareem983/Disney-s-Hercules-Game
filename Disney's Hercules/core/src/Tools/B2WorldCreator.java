

package Tools;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


public class B2WorldCreator {
    
    public B2WorldCreator(PlayScreen  screen){
       World world = screen.getWorld();
        TiledMap map = screen.getMap();   
                
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape() ;
        FixtureDef fdef = new FixtureDef();
        Body body ;
        for(MapObject object : map.getLayers().get("Hercules Ground").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle() ;
            
            bdef.type = BodyDef.BodyType.StaticBody ;
            bdef.position.set((rec.getX() + rec.getWidth() /2) / Main.PPM  , (rec.getY()+ rec.getHeight()/2) / Main.PPM  ) ;
            body =world.createBody(bdef) ;
                    shape.setAsBox( (rec.getWidth()/2 ) / Main.PPM , (rec.getHeight() /2) / Main.PPM );
                    fdef.shape =shape ;
                    body.createFixture(fdef ) ; 
        }

        
    }
    
}