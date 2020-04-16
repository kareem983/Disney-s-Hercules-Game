

package Tools;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import HealthAttacker.BabyDragon;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


public class B2WorldCreator {
    private PlayScreen screen;
    private World world;
    private TiledMap map;
    private Body body;
    private BodyDef bdef;
    private PolygonShape shape;
    private FixtureDef fdef;
    private Array <BabyDragon> babyDragons;
    
    public B2WorldCreator(PlayScreen  screen){
        this.screen = screen;
        world = screen.getWorld();
        map = screen.getMap();   
                
        bdef = new BodyDef();
        shape = new PolygonShape() ;
        fdef = new FixtureDef();
        babyDragons = new Array<BabyDragon>();
        
            Hercules();
            BabyDragonSurface();
            BabyDragons();
    }
      private void Hercules(){
          
        for(MapObject object : map.getLayers().get("Hercules Ground").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle() ;
            
                bdef.type = BodyDef.BodyType.StaticBody ;
                bdef.position.set((rec.getX() + rec.getWidth() /2) / Main.PPM  , (rec.getY()+ rec.getHeight()/2) / Main.PPM  ) ;
                body =world.createBody(bdef) ;
                shape.setAsBox( (rec.getWidth()/2 ) / Main.PPM , (rec.getHeight() /2) / Main.PPM );
                fdef.filter.categoryBits = Main.GROUND_BIT;
                fdef.shape =shape ;
                body.createFixture(fdef ) ;
             }   
         }
      private void BabyDragonSurface(){
          
         for(MapObject object : map.getLayers().get("Baby Dragon Surface").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle() ;
            
            bdef.type = BodyDef.BodyType.StaticBody ;
            bdef.position.set((rec.getX() + rec.getWidth() /2) / Main.PPM  , (rec.getY()+ rec.getHeight()/2) / Main.PPM  ) ;
            body =world.createBody(bdef) ;
            shape.setAsBox( (rec.getWidth()/2 ) / Main.PPM , (rec.getHeight() /2) / Main.PPM );
            fdef.filter.categoryBits = Main.BIRD_SURFACE_BIT;
            fdef.shape =shape ;
            body.createFixture(fdef ) ;
        }

         for(MapObject object : map.getLayers().get("Sky Border").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle() ;
            
            bdef.type = BodyDef.BodyType.StaticBody ;
            bdef.position.set((rec.getX() + rec.getWidth() /2) / Main.PPM  , (rec.getY()+ rec.getHeight()/2) / Main.PPM  ) ;
            body =world.createBody(bdef) ;
            shape.setAsBox( (rec.getWidth()/2 ) / Main.PPM , (rec.getHeight() /2) / Main.PPM );
            fdef.filter.categoryBits = Main.SKY_BORDER_BIT;
            fdef.shape =shape ;
            body.createFixture(fdef ) ;
        }
         
      }
      private void BabyDragons(){
           for(MapObject object : map.getLayers().get("Baby Dragons").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle() ;
            babyDragons.add(new BabyDragon(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM));
      }
    }

    public Array<BabyDragon> getBabyDragons() {
        return babyDragons;
    }
}