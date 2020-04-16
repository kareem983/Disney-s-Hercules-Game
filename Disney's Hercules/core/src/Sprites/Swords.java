package Sprites;

import Screens.PlayScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Swords extends Sprite {
 public Animation Asword;
 public Texture Tsword;
 public TextureRegion region;
 public Hercules herucle;
 public World world;
 public PlayScreen screen;
 public float statetimer=0;
 public float x,y;
    public abstract void update();  
}
