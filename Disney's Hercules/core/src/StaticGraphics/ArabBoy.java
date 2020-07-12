
package StaticGraphics;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

 
public class ArabBoy extends Sprite{

    private Animation animation;
    private float elapsedTime;
    private float x, y;
    
    public ArabBoy (PlayScreen screen) {
        TiledMap map = screen.getMap();
        Texture texture = new Texture("Sprites\\Level 2\\Animation\\Arab Boy.png");
        try{
            for (MapObject object : map.getLayers().get("Arab Boy").getObjects().getByType(RectangleMapObject.class)){
                 Rectangle rect = ((RectangleMapObject) object).getRectangle();

                 x = rect.getX() / Main.PPM;
                 y = rect.getY() / Main.PPM;
            }
        } catch(Exception ex){}
        
        setPosition(x, y);
        Array <TextureRegion> frames = new Array<TextureRegion>();
        
        for (int i =0; i < 2; ++i)
            frames.add(new TextureRegion(texture, i*276, 0, 276, 442));
        animation = new Animation(0.4f, frames, Animation.PlayMode.LOOP_PINGPONG);
        setBounds(getX(), getY(), 150/Main.PPM, 200/Main.PPM);
    }
    
     public void update(float dt){
         elapsedTime += dt;
        setRegion((TextureRegion) animation.getKeyFrame(elapsedTime, true));
     }
    
}
