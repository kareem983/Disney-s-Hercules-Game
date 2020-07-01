package Tools;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import HealthAttacker.BabyDragon;
import MovingObjects.*;
import Sprites.Border;
import Sprites.Slider;
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


public class WorldCreator {
    private PlayScreen screen;
    private World world;
    private TiledMap map;
    private Body body;
    private BodyDef bdef;
    private PolygonShape shape;
    private FixtureDef fdef;
    private Phill phill;
    private Array<Bird> birds;
    private Array<Deer> deers;
    private Array<Ape> apes;
    private Array<BabyDragon> babyDragons;
    private Array<Wagon> wagons;
    private Array<Vulture> vultures;
    private Array<Border> borders;
    
    public WorldCreator(PlayScreen  screen){
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
        wagons = new Array<Wagon>();
        vultures = new Array<Vulture>();
        borders = new Array<Border>();
        
            Hercules();
            CharactersGround();
            Phill();
            Deer();
            Apes();
            BirdSurface();
            Birds();
            BabyDragonSurface();
            BabyDragons();
            Wagons();
            Vultures();
            Barriers();
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
          try{      
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
          }catch(Exception ex){}
      }
    private void BirdSurface(){
        try{
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
        } catch(Exception ex){}
      }
    private void BabyDragonSurface(){
          try{
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
        } catch(Exception ex){}
      }
    private void Phill(){
        try{
                for(MapObject object : map.getLayers().get("Phill").getObjects().getByType(RectangleMapObject.class)){
                Rectangle  rec = ((RectangleMapObject) object ).getRectangle();
                phill = new Phill(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM);
            }
        } catch(Exception ex){}
    }
    private void Birds(){
        try{
                for(MapObject object : map.getLayers().get("Birds").getObjects().getByType(RectangleMapObject.class)){
                Rectangle  rec = ((RectangleMapObject) object ).getRectangle();
                birds.add(new Bird(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM));
          }
        } catch(Exception ex){}
    }
    private void Deer(){
        try{
            for(MapObject object : map.getLayers().get("Deer").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle();
            deers.add(new Deer(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM));
            }
        } catch(Exception ex){}
    }
    private void Apes(){
        try{
                for(MapObject object : map.getLayers().get("Apes").getObjects().getByType(RectangleMapObject.class)){
                Rectangle  rec = ((RectangleMapObject) object ).getRectangle() ;
                apes.add(new Ape(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM));
          }
        } catch(Exception ex){}
    }
    private void BabyDragons(){
        try{
                for(MapObject object : map.getLayers().get("Baby Dragons").getObjects().getByType(RectangleMapObject.class)){
                Rectangle  rec = ((RectangleMapObject) object ).getRectangle() ;
                babyDragons.add(new BabyDragon(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM));
          }
        } catch(Exception ex){}
    }
    private void Wagons(){
        try{
                for(MapObject object : map.getLayers().get("Wagons").getObjects().getByType(RectangleMapObject.class)){
                Rectangle  rec = ((RectangleMapObject) object ).getRectangle();
                wagons.add(new Wagon(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM));
            }
        } catch(Exception ex){}
    }
    private void Vultures() {
        try{
                for(MapObject object : map.getLayers().get("Vultures").getObjects().getByType(RectangleMapObject.class)){
                Rectangle  rec = ((RectangleMapObject) object ).getRectangle();
                vultures.add(new Vulture(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM));
            }
                
                for(MapObject object : map.getLayers().get("Vulture Borders").getObjects().getByType(RectangleMapObject.class)){
                Rectangle  rec = ((RectangleMapObject) object ).getRectangle();
                borders.add(new Border(screen, rec.getX() / Main.PPM, rec.getY() / Main.PPM));
            }
                
        } catch(Exception ex){}
    }
    private void Barriers() {
        try{
        for(MapObject object : map.getLayers().get("Obstacless").getObjects().getByType(RectangleMapObject.class)){
            Rectangle  rec = ((RectangleMapObject) object ).getRectangle() ;
            
                bdef.type = BodyDef.BodyType.StaticBody ;
                bdef.position.set((rec.getX() + rec.getWidth() /2) / Main.PPM  , (rec.getY()+ rec.getHeight()/2) / Main.PPM  ) ;
                body =world.createBody(bdef) ;
                shape.setAsBox( (rec.getWidth()/2 ) / Main.PPM , (rec.getHeight() /2) / Main.PPM );
                fdef.shape =shape ;
                body.createFixture(fdef ) ;
                
                
                new Slider(screen);
             } 
        } catch (Exception ex){}
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
    public Array<Wagon> getWagons(){
        return wagons;
    }
    public Array<Vulture> getVultures(){
        return vultures;
    }
    public Array<Border> getBorders(){
        return borders;
    }
}