package Tools;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import HealthAttacker.BabyDragon;
import MovingObjects.*;
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
    private Phill phill;
    private Array <Bird> birds;
    private Array <Deer> deers;
    private Array <Ape> apes;
    private Array <BabyDragon> babyDragons;
    
    public B2WorldCreator(PlayScreen  screen){
        this.screen = screen;
        world = screen.getWorld();
        map = screen.getMap();   
                
        bdef = new BodyDef();
        shape = new PolygonShape() ;
        fdef = new FixtureDef();
        birds = new Array<Bird>();
        deers = new Array<Deer>();
        apes = new Array<Ape>();
        babyDragons = new Array<BabyDragon>();
        
            Hercules();
            CharactersGround();
            Phill();
            Deer();
            Apes();
            BirdSurface();
            Birds();
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
    
      private void CharactersGround(){
           for(MapObject object : map.getLayers().get("Characters Ground").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle() ;
            
            bdef.type = BodyDef.BodyType.StaticBody ;
            bdef.position.set((rec.getX() + rec.getWidth() /2) / Main.PPM  , (rec.getY()+ rec.getHeight()/2) / Main.PPM  ) ;
            body =world.createBody(bdef) ;
            shape.setAsBox( (rec.getWidth()/2 ) / Main.PPM , (rec.getHeight() /2) / Main.PPM );
            fdef.shape =shape ;
            fdef.filter.categoryBits = Main.CHARACTERS_GROUND_BIT;
            body.createFixture(fdef ) ;
        }
      }
      private void BirdSurface(){
           for(MapObject object : map.getLayers().get("Birds Surface").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle() ;
            
            bdef.type = BodyDef.BodyType.StaticBody ;
            bdef.position.set((rec.getX() + rec.getWidth() /2) / Main.PPM  , (rec.getY()+ rec.getHeight()/2) / Main.PPM  ) ;
            body =world.createBody(bdef) ;
            shape.setAsBox( (rec.getWidth()/2 ) / Main.PPM , (rec.getHeight() /2) / Main.PPM );
            fdef.shape =shape ;
            fdef.filter.categoryBits = Main.BIRDS_GROUND_BIT;
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
            fdef.filter.categoryBits = Main.BABYDRAGONS_SURFACE_BIT;
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
      
       private void Phill(){
           for(MapObject object : map.getLayers().get("Phill").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle();
            phill = new Phill(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM);
      }
    }
       private void Birds(){
           for(MapObject object : map.getLayers().get("Birds").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle();
            birds.add(new Bird(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM));
      }
    }
       private void Deer(){
           for(MapObject object : map.getLayers().get("Deer").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle();
            deers.add(new Deer(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM));
      }
    }
       private void Apes(){
           for(MapObject object : map.getLayers().get("Apes").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle() ;
            apes.add(new Ape(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM));
      }
    }
       private void BabyDragons(){
           for(MapObject object : map.getLayers().get("Baby Dragons").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle() ;
            babyDragons.add(new BabyDragon(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM));
      }
    }

    public Phill getPhill() {
        return phill;
    }
    public Array<Bird> getBirds() {
        return birds;
    }
    public Array<Deer> getDeers() {
        return deers;
    }
    public Array<Ape> getApes(){
        return apes;
    }
    public Array<BabyDragon> getBabyDragons() {
        return babyDragons;
    }
    
}