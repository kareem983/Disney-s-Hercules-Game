package Sprites;

import MovingObjects.Hercules;
import Scenes.HUD;
import Scenes.HUD2;
import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class GoldenCoin extends Coin {

    public GoldenCoin(PlayScreen screen, float posX, float posY) {
        super(0, 0, 563, 564, posX, posY);
        this.screen = screen;
        setPosition(this.posX / Main.PPM, this.posY / Main.PPM);
    }

    @Override
    public void DefineAnimation() {

        Array<TextureRegion> frames = new Array<TextureRegion>();

        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Gold_1.png"), this.startX, this.startY, this.width, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Gold_2.png"), this.startX, this.startY, 559, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Gold_3.png"), this.startX, this.startY, 504, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Gold_4.png"), this.startX, this.startY, 428, 565));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Gold_5.png"), this.startX, this.startY, 262, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Gold_6.png"), this.startX, this.startY, 108, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Gold_7.png"), this.startX, this.startY, 262, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Gold_8.png"), this.startX, this.startY, 428, 565));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Gold_9.png"), this.startX, this.startY, 503, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Level 1\\Coins\\Gold_10.png"), this.startX, this.startY, 559, this.height));

        CoinDraw = new Animation(0.09f, frames);
        frames.clear();
    }

    @Override
    public void update(Hercules player) {

        //Rectangle coin_rec = this.getBoundingRectangle();
        //Rectangle palyer_rec = player.getBoundingRectangle();
        //if(coin_rec.overlaps(palyer_rec))

        if (player.body.getPosition().x > (this.posX - 68) / Main.PPM && player.body.getPosition().x < (this.posX + 88) / Main.PPM && player.body.getPosition().y > (this.posY - 120) / Main.PPM && player.body.getPosition().y < (this.posY + 50) / Main.PPM) {
            //setPosition(-50, -50); 
            screen.creator.getCoins().removeValue(this, true);
            if (this.isfound) {
                if(!screen.noSwords)HUD.score += 10;
                else HUD2.score += 10;
                music = Main.manager.get("Audio//Hercules - sounds//Coin.wav", Music.class);
                music.setLooping(false);
                music.setVolume(Main.vol);
                music.play();
            }
            this.isfound = false;
        }

        stateTimer += Gdx.graphics.getDeltaTime();
        setRegion((TextureRegion) CoinDraw.getKeyFrame(stateTimer, true));
        if (stateTimer > 10) {
            stateTimer = 0;
        }
        moving();
    }
}
