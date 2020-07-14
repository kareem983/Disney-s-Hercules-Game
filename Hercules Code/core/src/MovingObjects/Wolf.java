package MovingObjects;

import com.main.Main;
import Screens.PlayScreen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Wolf extends SecondaryCharacter {
    Sprite wolf ;boolean l ,start;
    float counter = 0, st , en,x,y ;
public TextureAtlas atlas ;
Animation animation;
float stateTimer;
    boolean flip1, flip2;
    Array<TextureRegion> frames;
    private Music music;
    
    public Wolf(PlayScreen screen, float x, float y , float en){
        super(screen , x , y);
        st = x/Main.PPM ;
        this.en = st+en/Main.PPM;
        this.x = x ; this.y = y;
        flip1 = false;
        flip2 = true;
        
        music = Main.manager.get("Audio//Hercules - sounds//Wolf.mp3", Music.class);
    }

    @Override
    protected void defineObject() {
        atlas = new TextureAtlas("Sprites/Level 2/wolf/r.pack");
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(atlas.findRegion("wolf6") , 1,1,107, 52));
        frames.add(new TextureRegion(atlas.findRegion("wolf1")  , 108 , 1 , 107, 52));
        frames.add(new TextureRegion(atlas.findRegion("wolf2")  , 211 ,1,106, 52 ));
        frames.add(new TextureRegion(atlas.findRegion("wolf6") , 1,1,107, 52));
        frames.add(new TextureRegion(atlas.findRegion("wolf1")  , 108 , 1 , 107, 52));
        frames.add(new TextureRegion(atlas.findRegion("wolf2")  , 211 ,1,106, 52 ));
        animation = new Animation(0.2f , frames );
        setBounds(0,0,250/Main.PPM,100/ Main.PPM);
        setPosition(x/Main.PPM , y/ Main.PPM);
    }

    @Override
    public void update(float dt) {
if (!start){setPosition(x/Main.PPM , y/ Main.PPM); start = true;}
        TextureRegion region = (TextureRegion) animation.getKeyFrame(stateTimer,true);

        stateTimer += dt;

if (flip1)
{
    if (region.isFlipX() != false) {
        region.flip(true, false);
    }
    setPosition((getX() - 4/Main.PPM) , y / Main.PPM);
    if (getX() < st) {flip1 = false;flip2 = true;}
}

    else if (flip2)
{
    if (region.isFlipX() != true) {
        region.flip(true, false);
    }
    setPosition((getX() + 4/Main.PPM) , y / Main.PPM);
    if (getX() > en) {flip1 = true;flip2 = false ;}
}

    /***********/
    if(screen.getPlayer().body.getPosition().x > getX()-0.5f && screen.getPlayer().body.getPosition().x < getX()+0.5f){
        music.play();
        music.setVolume(Main.vol);
    }
    /**********/
    setRegion(region);
    }


}
