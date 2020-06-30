
package StaticGraphics;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

 
public class Waterfall extends Sprite{

    private Animation animation;
    private static TextureAtlas atlas=new TextureAtlas("Sprites\\Level 2\\Animation\\Waterfall.pack");
    private float elapsedTime;
    private float x, y;
    
    public Waterfall(PlayScreen screen) {
        super(atlas.findRegion("Region"));
        TiledMap map = screen.getMap();
        
        try{
            for (MapObject object : map.getLayers().get("Waterfall").getObjects().getByType(RectangleMapObject.class)){
                 Rectangle rect = ((RectangleMapObject) object).getRectangle();

                 x = rect.getX() / Main.PPM;
                 y = rect.getY() / Main.PPM;
            }
        } catch(Exception ex){}
        
        setPosition(x, y);
        Array <TextureRegion> frames = new Array<TextureRegion>();
        
        for (int i =0; i < 10; ++i)
            frames.add(new TextureRegion(getTexture(), 0, i*486, 864, 486));
        animation = new Animation(0.1f, frames);
        setBounds(getX(), getY(), 1400/Main.PPM, 800/Main.PPM);
    }
    
     public void update(float dt){
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        elapsedTime += dt;
       TextureRegion region = (TextureRegion) animation.getKeyFrame(elapsedTime, true);
       return region;
    }
    
}
