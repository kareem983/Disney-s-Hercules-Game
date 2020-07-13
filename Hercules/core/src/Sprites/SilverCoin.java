package Sprites;

import com.main.Main;
import MovingObjects.Hercules;
import Scenes.HUD;
import Scenes.HUD2;
import Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class SilverCoin extends Coin {

    //private boolean PoolSoundIsPlayed;
    //private int PoolPosX;
    
    public SilverCoin(PlayScreen screen, float posX, float posY) {
        super(0, 0, 563, 564, posX, posY);
        this.screen = screen;
        setPosition(this.posX / Main.PPM, this.posY / Main.PPM);
    }

    @Override
    public void DefineAnimation() {

        Array<TextureRegion> frames = new Array<TextureRegion>();

        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Silver_1.png"), this.startX, this.startY, this.width, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Silver_2.png"), this.startX, this.startY, 559, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Silver_3.png"), this.startX, this.startY, 504, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Silver_4.png"), this.startX, this.startY, 428, 565));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Silver_5.png"), this.startX, this.startY, 262, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Silver_6.png"), this.startX, this.startY, 108, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Silver_7.png"), this.startX, this.startY, 262, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Silver_8.png"), this.startX, this.startY, 503, 565));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Silver_9.png"), this.startX, this.startY, 503, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Silver_10.png"), this.startX, this.startY, 559, this.height));

        CoinDraw = new Animation(0.09f, frames);
        frames.clear();
    }

    @Override
    public void update(Hercules player) {

        Rectangle coin_rec = this.getBoundingRectangle();
        Rectangle palyer_rec = player.getBoundingRectangle();
        if (coin_rec.overlaps(palyer_rec)) {
            screen.creator.getCoins().removeValue(this, true);
            //screen.creator.silverPool.free(this);
            if (this.isfound) {
                if(!screen.noSwords)HUD.score += 5;
                else HUD2.score += 5;
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
