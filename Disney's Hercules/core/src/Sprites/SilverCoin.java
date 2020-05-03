package Sprites;

import MovingObjects.Hercules;
import Scenes.HUD;
import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class SilverCoin extends Coin {

    private boolean PoolSoundIsPlayed;
    private int PoolPosX;

    public SilverCoin(PlayScreen screen, float posX, float posY, Hercules hercule, HUD hud) {
        super(screen, 0, 0, 563, 564, posX, posY);
        this.hercule = hercule;
        this.hud = hud;
        setPosition(this.posX / Main.PPM, this.posY / Main.PPM);

        //Swimming pool sound effect boolean
        PoolSoundIsPlayed = true;
        this.PoolPosX = 14200;
    }

    @Override
    public void DefineAnimation() {

        Array<TextureRegion> frames = new Array<TextureRegion>();

        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Silver_1.png"), this.startX, this.startY, this.width, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Silver_2.png"), this.startX, this.startY, 559, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Silver_3.png"), this.startX, this.startY, 504, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Silver_4.png"), this.startX, this.startY, 428, 565));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Silver_5.png"), this.startX, this.startY, 262, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Silver_6.png"), this.startX, this.startY, 108, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Silver_7.png"), this.startX, this.startY, 262, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Silver_8.png"), this.startX, this.startY, 503, 565));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Silver_9.png"), this.startX, this.startY, 503, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Silver_10.png"), this.startX, this.startY, 559, this.height));

        CoinDraw = new Animation(0.09f, frames);
        frames.clear();
    }

    @Override
    public void update(Hercules player) {

        //getting close Swimming pool sound effects
        Rectangle coin_rec = this.getBoundingRectangle();
        Rectangle palyer_rec = player.getBoundingRectangle();
        //if (this.PoolSoundIsPlayed && hercule.b2body.getPosition().x > (this.PoolPosX-950)/Main.PPM && hercule.b2body.getPosition().x < (this.PoolPosX+120)/Main.PPM )
        /*  if(coin_rec.overlaps(palyer_rec))

         {
             music = Main.manager.get("Audio//Hercules - sounds//Water Sound.wav", Music.class);
             music.setLooping(false);
             music.play();
             this.PoolSoundIsPlayed=false;
         }*/

        //if (hercule.b2body.getPosition().x > (this.posX-68)/Main.PPM && hercule.b2body.getPosition().x < (this.posX+88)/Main.PPM && hercule.b2body.getPosition().y>(this.posY-120)/Main.PPM && hercule.b2body.getPosition().y<(this.posY+50)/Main.PPM)
        if (coin_rec.overlaps(palyer_rec)) {
            setPosition(-50, -50);
            if (this.isfound) {
                this.hud.score += 5;
                music = Main.manager.get("Audio//Hercules - sounds//Coin.wav", Music.class);
                music.setLooping(false);
                music.setVolume(Main.vol);
                music.play();
            }
            this.isfound = false;
            this.move = false;
        }

        stateTimer += Gdx.graphics.getDeltaTime();
        setRegion((TextureRegion) CoinDraw.getKeyFrame(stateTimer, true));
        if (stateTimer > 10) {
            stateTimer = 0;
        }
        moving();
    }

}
