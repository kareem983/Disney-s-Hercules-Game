package Sprites;

import MovingObjects.Hercules;
import Scenes.HUD;
import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GoldenCoin extends Coin {

    public GoldenCoin(PlayScreen screen, float posX, float posY, Hercules hercule, HUD hud) {
        super(screen, 0, 0, 563, 564, posX, posY);
        this.hercule = hercule;
        this.hud = hud;
        setPosition(this.posX / Main.PPM, this.posY / Main.PPM);
    }

    @Override
    public void DefineAnimation() {

        Array<TextureRegion> frames = new Array<TextureRegion>();

        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Gold_1.png"), this.startX, this.startY, this.width, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Gold_2.png"), this.startX, this.startY, 559, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Gold_3.png"), this.startX, this.startY, 504, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Gold_4.png"), this.startX, this.startY, 428, 565));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Gold_5.png"), this.startX, this.startY, 262, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Gold_6.png"), this.startX, this.startY, 108, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Gold_7.png"), this.startX, this.startY, 262, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Gold_8.png"), this.startX, this.startY, 428, 565));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Gold_9.png"), this.startX, this.startY, 503, this.height));
        frames.add(new TextureRegion(new Texture("Sprites\\Coins\\Gold_10.png"), this.startX, this.startY, 559, this.height));

        CoinDraw = new Animation(0.09f, frames);
        frames.clear();
    }

    @Override
    public void update(Hercules player) {

        Rectangle coin_rec = this.getBoundingRectangle();
        Rectangle palyer_rec = player.getBoundingRectangle();
        //if(coin_rec.overlaps(palyer_rec))

        if (hercule.b2body.getPosition().x > (this.posX - 68) / Main.PPM && hercule.b2body.getPosition().x < (this.posX + 88) / Main.PPM && hercule.b2body.getPosition().y > (this.posY - 120) / Main.PPM && hercule.b2body.getPosition().y < (this.posY + 50) / Main.PPM) {
            setPosition(-50, -50);
            if (this.isfound) {
                this.hud.score += 10;
                music = Main.manager.get("Audio//Hercules - sounds//Coin.wav", Music.class);
                music.setLooping(false);
                music.setVolume(0.5f);
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
