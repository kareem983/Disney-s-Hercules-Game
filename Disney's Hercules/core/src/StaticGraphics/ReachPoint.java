
package StaticGraphics;

import Scenes.Transition1;
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

 
public class ReachPoint extends Sprite{

    private PlayScreen screen;
    private Animation animation;
    private static TextureAtlas atlas=new TextureAtlas("Sprites\\Level 1\\Complement\\Reach Point.pack");
    private float elapsedTime;
    private float delayTime;
    private float x, y;
    
    public ReachPoint (PlayScreen screen) {
        super(atlas.findRegion("Reach"));
        this.screen = screen;
        TiledMap map = screen.getMap();
        
        try{
            for (MapObject object : map.getLayers().get("Reach Point").getObjects().getByType(RectangleMapObject.class)){
                 Rectangle rect = ((RectangleMapObject) object).getRectangle();

                 x = rect.getX() / Main.PPM;
                 y = rect.getY() / Main.PPM;
            }
        } catch(Exception ex){}
        
        setPosition(x, y);
        Array <TextureRegion> frames = new Array<TextureRegion>();
        
        for (int i =0; i < 4; ++i)
            frames.add(new TextureRegion(getTexture(), i*169, 0, 169, 267));
        animation = new Animation(0.2f, frames, Animation.PlayMode.LOOP_PINGPONG);
        setBounds(getX(), getY(), 200/Main.PPM, 280/Main.PPM);
    }
    
     public void update(float dt){
        setRegion(getFrame(dt));
        if (getBoundingRectangle().overlaps(screen.getPlayer().getBoundingRectangle())){
                screen.stopHercAction=true;
                delayTime+=dt;
                if (delayTime>=3){ // Wait for 3 Seconds and Move on Transition1 Screen
                    screen.Game.stop();
                    screen.game.setScreen(new Transition1(screen.game));
                    screen.dispose();
                }
            }
     }
     
    public TextureRegion getFrame(float dt) {
        elapsedTime += dt;
       TextureRegion region = (TextureRegion) animation.getKeyFrame(elapsedTime, true);
       return region;
    }
    
}
