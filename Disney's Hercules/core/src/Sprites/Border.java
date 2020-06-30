
package Sprites;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Border extends Sprite{

    public Border(PlayScreen screen, float x, float y) {
        setRegion(new Texture("Sprites/Level 2/Border.png"));
        setBounds(0,0, 2/Main.PPM,200/ Main.PPM);
        setPosition(x, y);
    }
    
}
