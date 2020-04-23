package Sprites;

import MovingObjects.Hercules;
import Screens.PlayScreen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Swords extends Sprite {
public Animation Asword;
public Texture Tsword=new Texture("Sprites\\LeftSonicSword.png");
public Texture Lsword=new Texture("Sprites\\fireball1.png");
public Texture Msword=new Texture("Sprites\\fireball2.png");
public TextureRegion region;
public Hercules herucle;
public World world;
public PlayScreen screen;
public float statetimer=0;
public float x,y;
public Sprite leftsonic=new Sprite(Tsword),upsonic=new Sprite(Tsword),rightsonic=new Sprite(Tsword);
public Music music;
public abstract void update();  
public abstract boolean Finish();
}
