package Sprites;

import MovingObjects.Hercules;
import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.audio.Music;
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
    public static boolean p  , Catch=false , played= false , drink = true;

    public Rectangle rect;
    Texture texture ;
    Hercules hercule;
    Music music , m;
    Hercules player;
    
    public Herculad(World world, PlayScreen screen, Hercules player, float posx, float posy) {
        super(new Texture("Sprites\\Level 1\\Complement\\Juice.png"));
        this.world = world;
        this.screen = screen;
        this.player=player;
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
        hercule = new Hercules(world , screen);
    }

    public void update(float dt) {
        Rectangle r = new Rectangle();
        handleJuice();
        
        if ( (posx/Main.PPM-screen.getPlayer().body.getPosition().x )  <=  (1.148f) && drink)  {
            m = Main.manager.get("Audio//Hercules - Voices//Hercules//Herculad.wav" , Music.class);
            m.setLooping(false);
            m.setVolume(Main.vol);
            m.play();
            drink = false;
        }

        if (Catch){
            setSize(0,0);
            rect.setSize(0,0);
            rect.setPosition(-50,-50);
            if (!played) {
                music = Main.manager.get("Audio//Hercules - Voices//Hercules//HerculadToken.wav", Music.class);
                music.setLooping(false);
                music.setVolume(Main.vol);
                music.play();
                played = true;
            }
            Catch = false;
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
    
    private void handleJuice() {
        if (rect.overlaps(player.getBoundingRectangle())) 
            if (!Catch) {
                Catch = true;
                player.hercules_Drink = true;
            }
    }

}