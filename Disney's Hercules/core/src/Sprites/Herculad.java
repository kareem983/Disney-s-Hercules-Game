package Sprites;
import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.physics.box2d.World;


public class Herculad extends Sprite {
    World world;
    PlayScreen screen;
    PolygonShape shape;
    float posx, posy;


    float stateTimer;
    public boolean p  , Catch;

    public Rectangle rect;
    Texture texture ;
    public Herculad(World world, PlayScreen screen, float posx, float posy) {
        super(new Texture("Sprites\\Juice.png"));
        this.world = world;
        this.screen = screen;
        this.posx = posx;
        this.posy = posy;
        stateTimer = 80f;
        Catch = false;
        rect = new Rectangle();
        setPosition(posx/Main.PPM , posy/Main.PPM);
        setRegion(getTexture());

        setBounds(0, 0, 80 / Main.PPM, 80 / Main.PPM);
        setPosition(posx/Main.PPM, posy/Main.PPM);
        setRegion(getTexture());
        rect = getBoundingRectangle();
    }


    public void update(float dt) {



        if (Catch){
            setSize(0,0);
            rect.setSize(0,0);
            rect.setPosition(-50,-50);
        }
        else {
            if (posy < 200 && !p) {
                posy += Gdx.graphics.getDeltaTime() * 90;
                if (posy >= 130) p = true;
            } else if (p) {
                posy -= Gdx.graphics.getDeltaTime() * 90;
                if (posy <= 100) p = false;
            }
            setPosition(posx / Main.PPM, posy / Main.PPM);
            setRegion(getTexture());
        }
    }

}