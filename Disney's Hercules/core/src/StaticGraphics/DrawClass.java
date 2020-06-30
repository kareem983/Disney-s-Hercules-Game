
package StaticGraphics;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class DrawClass {

    private Array <Flame> flames;
    
    public DrawClass(PlayScreen screen) {
        TiledMap map = screen.getMap();
        
        flames = new Array<Flame>();
        try{
            for (MapObject object : map.getLayers().get("Fire On").getObjects().getByType(RectangleMapObject.class)){
                 Rectangle rect = ((RectangleMapObject) object).getRectangle();

                 flames.add(new Flame(screen, rect.getX() / Main.PPM, rect.getY() / Main.PPM ));
            }
        } catch(Exception ex){}
        
    }
         public Array<Flame> getFlames() {
            return flames;
        }
}
