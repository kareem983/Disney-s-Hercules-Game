
package Sprites;

import Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.Hercules.game.Main;

public class Coin extends InteractiveObject{
    
     public Coin(World world, TiledMap map, Rectangle bounds){
         super(world, map, bounds);
         
     }
    
}
