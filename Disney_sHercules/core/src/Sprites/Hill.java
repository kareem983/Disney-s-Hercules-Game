
package Sprites;


import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TideMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Animation;
import  com.badlogic.gdx.math.*;
import java.awt.*;


public class Hill {
    World world;
    Body body ;
    BodyDef bdef;
    PolygonShape shape;
    FixtureDef fdef;

    public Hill(World world, PlayScreen screen, TiledMap map){
        for (MapObject object :map.getLayers().get("Hills").getObjects().getByType(RectangleMapObject.class)) {
            bdef = new BodyDef();
            fdef = new FixtureDef();
            shape = new PolygonShape();
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / Main.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / Main.PPM);
            body = world.createBody(bdef);
            shape.setAsBox((rectangle.getWidth() / 2 / Main.PPM) , rectangle.getHeight() / 2 / Main.PPM );
            fdef.shape = shape;
            body.createFixture(fdef);
        }

    }

}

