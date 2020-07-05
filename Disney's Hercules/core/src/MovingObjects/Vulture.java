package MovingObjects;

import HealthAttacker.VultureEgg;
import Scenes.HUD2;
import Screens.PlayScreen;
import Sprites.Border;
import Tools.Timer;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Vulture extends SecondaryCharacter {
    
    private boolean right;
    private TextureRegion region;
    private Animation animation;
    private Array<TextureRegion> frames;
    private float stateTimer, eggTimer, y;
    public VultureEgg egg;
    public HUD2 hud2;
    public Timer timer;
    public Vulture(PlayScreen screen,HUD2 hud2,Timer timer, float x, float y){
        super(screen, x, y);
        this.y = y;
        this.hud2=hud2;
        this.timer=timer;
        right = true;
        setPosition(x, y);
        egg = new VultureEgg(getX(), getY());
    }
    
    @Override
    protected void defineCharacter() {
        frames = new Array<TextureRegion>();
        for(int i = 0; i < 9; ++i)
            frames.add(new TextureRegion(new Texture("Sprites/Level 2/Vulture/VultureE.png"), i*271,0,268, 217));
        animation = new Animation(0.18f, frames);
        setBounds(0,0, 250/Main.PPM,250/ Main.PPM);
    }
    
    private void reverseDirection(){
            for(int i = 0 ; i < frames.size; ++i)
                frames.get(i).flip(true, false);
            
            animation = new Animation(0.2f, frames);
            right = (right)?false:true;
    }
    
    @Override
    public void update(float dt) {
        stateTimer += dt; eggTimer+=dt;
        region = (TextureRegion) animation.getKeyFrame(stateTimer,true);
        setRegion(region);
        
        for (Border border : screen.creator.getBorders()) // Reached the Border
              if(getBoundingRectangle().overlaps(border.getBoundingRectangle())){
                  reverseDirection(); break;
              }
            
        if (right)
            setPosition((getX() + 2/Main.PPM) , y);
        else
            setPosition((getX() - 2/Main.PPM) , y);

        if (eggTimer > 5){
            eggTimer=0;
            egg = new VultureEgg(getX(), getY());
        }
        if (egg.getBoundingRectangle().overlaps(screen.getPlayer().getBoundingRectangle())){
            //timer.statetimer4 -=  10;
        }
        egg.update(dt);
    }

    
}
