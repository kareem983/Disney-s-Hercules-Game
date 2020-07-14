package MovingObjects;

import com.main.Main;
import HealthAttacker.VultureEgg;
import Pooling.EggPool;
import Scenes.HUD2;
import Screens.PlayScreen;
import Sprites.Border;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Vulture extends SecondaryCharacter {
    
    private boolean right, hitHercules;
    private float stateTimer, eggTimer, recoveryTime, y;
    private TextureRegion region;
    private Animation animation;
    private Array<TextureRegion> frames;
    public EggPool pool;
    public VultureEgg egg;
    private Music vulture;
    private Music eggCollision, eggCollision2;
    
    public Vulture(PlayScreen screen, float x, float y){
        super(screen, x, y);
        this.y = y;
        right = true;
        hitHercules=false;
        recoveryTime = 0f;
        pool = new EggPool();
        egg = pool.obtain();
        egg.x = x; egg.y = y;
        setPosition(x, y);
        vulture = Main.manager.get("Audio//Hercules - sounds//Vulture.mp3", Music.class);
        eggCollision = Main.manager.get("Audio//Hercules - sounds//Hercules_Atacked.wav", Music.class);
        eggCollision2 = Main.manager.get("Audio//Hercules - sounds//CrashEgg.mp3", Music.class);
    }
    
    @Override
    protected void defineObject() {
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
        stateTimer += dt; eggTimer+=dt; recoveryTime+=dt;
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
            /************************************/
            
        if (eggTimer > 5){
            eggTimer=0;
            
            pool.free(egg);
            egg = pool.obtain();
            egg.x = getX();
            egg.y = getY();
        }
        
        egg.update(dt);
        /*************************/ // FOR EGG COLLISION WITH HERCULES
        if (egg.getBoundingRectangle().overlaps(screen.getPlayer().getBoundingRectangle()) && !hitHercules){
               HUD2.timer-=10;
               hitHercules = true;
               recoveryTime = 0f;
               eggCollision.play(); eggCollision2.play();
               eggCollision.setVolume(Main.vol); eggCollision2.setVolume(Main.vol);
        }
        if(hitHercules && recoveryTime > 3)
            hitHercules=false;
        /**************************/
        
        // SOUNDS
        if(screen.getPlayer().body.getPosition().x > getX() - 0.2f && screen.getPlayer().body.getPosition().x < getX() + 0.2f && !vulture.isPlaying()){
            vulture.play();
            vulture.setVolume(Main.vol);
        }
    }
}
