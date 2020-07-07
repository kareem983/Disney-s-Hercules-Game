package Sprites;

import MovingObjects.Hercules;
import Screens.Level1;
import Screens.PlayScreen;
import Tools.WorldCreator;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Swords extends Sprite {
public Animation Asword;
public Texture Tsword=new Texture("Sprites\\Level 1\\Swords\\LeftSonicSword.png");
public Texture Lsword=new Texture("Sprites\\Level 1\\Swords\\fireball1.png");
public Texture Msword=new Texture("Sprites\\Level 1\\Swords\\fireball2.png");
public TextureRegion region;
public Hercules herucle;
public World world;
public Level1 screen;
public float statetimer=0;
public float x,y;
public Sprite leftsonic=new Sprite(Tsword),upsonic=new Sprite(Tsword),rightsonic=new Sprite(Tsword);
public Music music;
public abstract void update();  
public abstract boolean Finish();

    public static void getbaby(PlayScreen screen){
    for (int i = 0; i < screen.creator.getBabyDragons().size; i++) {
            Rectangle recbaby = screen.creator.getBabyDragons().get(i).getBoundingRectangle();
            if (recbaby.overlaps(screen.lightningsword.getBoundingRectangle())) {
                if (screen.lightningsword.Finish() == false) {
                    screen.creator.getBabyDragons().get(i).Stap();
                }
            } else if (recbaby.overlaps(screen.rightfireball.getBoundingRectangle())) {
                if (screen.rightfireball.Finish() == false) {
                    screen.creator.getBabyDragons().get(i).Stap();
                }

            } else if (recbaby.overlaps(screen.leftfirball.getBoundingRectangle())) {
                if (screen.leftfirball.Finish() == false) {
                    screen.creator.getBabyDragons().get(i).Stap();
                }

            } else if (recbaby.overlaps(screen.sonicsword.rightsonic.getBoundingRectangle()) || recbaby.overlaps(screen.sonicsword.leftsonic.getBoundingRectangle()) || recbaby.overlaps(screen.sonicsword.upsonic.getBoundingRectangle())) {
                if (screen.sonicsword.Finish() == false) {
                    screen.creator.getBabyDragons().get(i).Stap();
                }
            }
        }
    }
}