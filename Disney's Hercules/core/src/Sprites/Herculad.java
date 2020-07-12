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
    float posx, posy, x, y;
    float stateTimer, safe;
    public static boolean p  , Catch=false , played= false , beforeDrinkSound = true;
    public boolean draw, once;
    public Rectangle rect;
    Texture texture ;
    Music music , m;
    Hercules player;
    
    public Herculad(PlayScreen screen, float posx, float posy) {
        super(new Texture("Sprites\\Level 1\\Complement\\Juice.png"));
        this.world = screen.getWorld();
        this.screen = screen;
        this.player=screen.getPlayer();
        this.posx = x = posx;
        this.posy = y = posy;
        stateTimer = 80f;
        Catch = false;
        rect = new Rectangle();
        safe =0f;
        
        setRegion(getTexture());
        setBounds(0, 0, 80 / Main.PPM, 80 / Main.PPM);
        setPosition(posx/Main.PPM, posy/Main.PPM);
        
        rect = getBoundingRectangle();
        draw=true;
    }

    public void update(float dt) {
        handleJuice();
        if ( (posx/Main.PPM-player.body.getPosition().x )  <  ( 1.18f) && beforeDrinkSound)  {
            m = Main.manager.get("Audio//Hercules - Voices//Hercules//Herculad.wav" , Music.class);
            m.setVolume(Main.vol);
            m.play();
            beforeDrinkSound = false;
        }

        if (Catch){
            if (!played) {
                music = Main.manager.get("Audio//Hercules - Voices//Hercules//HerculadToken.wav", Music.class);
                music.setVolume(Main.vol);
                music.play();
                played = true;
            }
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
        }
    }
    
    private void handleJuice() {
        if (rect.overlaps(player.getBoundingRectangle())) 
            if (!Catch) {
                Catch = true;
                player.hercules_Drink = true;
                draw=false;
                safe=0;
            }
        safe += Gdx.graphics.getDeltaTime();
        if(Catch && safe > 3){
            player.hercules_Drink = false;
        }
    }
    public void resetData(){
        stateTimer = 80f;
        safe=0;
        Catch = false;
        beforeDrinkSound=true;
        player.hercules_Drink = p = false;
        setPosition(x/Main.PPM, y/Main.PPM);
        draw=true;
    }
}