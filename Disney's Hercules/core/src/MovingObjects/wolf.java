package MovingObjects;

import Screens.Level2;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
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
    boolean flip1 = false, flip2 = true , f2=true,f1=false;
    Array<TextureRegion> frames;
    public Wolf(Level2 screen, float x, float y , float en){
        super(screen , x , y);
st = x/Main.PPM ;
 this.en = en/Main.PPM;
 this.x = x ; this.y = y;
    }

    @Override
    protected void defineCharacter() {
        atlas = new TextureAtlas("Sprites/Level 2/wolf/r.pack");
        frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(atlas.findRegion("wolf6") , 1,1,107, 52));
        frames.add(new TextureRegion(atlas.findRegion("wolf1")  , 108 , 1 , 107, 52));
        frames.add(new TextureRegion(atlas.findRegion("wolf2")  , 211 ,1,106, 52 ));
        frames.add(new TextureRegion(atlas.findRegion("wolf6") , 1,1,107, 52));
        frames.add(new TextureRegion(atlas.findRegion("wolf1")  , 108 , 1 , 107, 52));
        frames.add(new TextureRegion(atlas.findRegion("wolf2")  , 211 ,1,106, 52 ));
        animation = new Animation(0.6f , frames );
        setBounds(0,0,250/Main.PPM,100/ Main.PPM);
        setPosition(x/Main.PPM , y/ Main.PPM);
    }

    @Override
    public void update(float dt) {
if (!start){setPosition(x/Main.PPM , y/ Main.PPM); start = true;}
        TextureRegion region = (TextureRegion) animation.getKeyFrame(stateTimer,true);

        stateTimer += Gdx.graphics.getDeltaTime();

if (flip1)
{
    if (region.isFlipX() != f1) {
        region.flip(true, false);
    }
    setPosition((getX() - 2/Main.PPM) , y / Main.PPM);
    if (getX() <= st) {flip2 = true;flip1 = false;}
}

    else if (flip2)
{
    if (region.isFlipX() != f2) {
        region.flip(true, false);
    }
    setPosition((getX() + 2/Main.PPM) , y / Main.PPM);
    if (getX() >= en) {flip1 = true;flip2 = false ;}
}

    setRegion(region);
    }


}
