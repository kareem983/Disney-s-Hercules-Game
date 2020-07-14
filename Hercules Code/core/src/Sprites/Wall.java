package Sprites;

import com.main.Main;
import MovingObjects.Hercules;
import Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Wall extends Sprite {
    
    public PlayScreen screen;
    public Hercules hercule;
    public BodyDef bdef;
    public FixtureDef fdef;
    public Body body;
    public World world;
    public Sprite[] wall = new Sprite[5];
    public Sprite Extra;
    public float x;
    public float y;
    public int i = 0;
    public float statetimer,statetimer1,statetimer2;
    public Sprite crushess;
    public boolean hitted, getInOnce, once;
    private Music music;
    
    private static int cnt;
    public Wall(PlayScreen screen, float x, float y) {
        this.screen = screen;
        this.hercule = screen.getPlayer();
        this.world = screen.getWorld();
        this.x = x;
        this.y = y;
        hitted = once = getInOnce = false;
        statetimer = statetimer1 = statetimer2 = 0;
        for(int k = 0; k < 5; ++k){
            wall[k] = new Sprite(new Texture("Sprites//Level 2//Block//"+k+".png"));
            wall[k].setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
        }
        Extra = new Sprite(new Texture("Sprites//Level 2//Block//5.png"));
        Extra.setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
        Texture crushes = new Texture("Sprites//Level 2//Block//crushes.png");
        crushess = new Sprite(crushes);
        crushess.setBounds(0, 0, 0, 0);
        WallBlockDefinition();
        music = Main.manager.get("Audio//Hercules - sounds//Tall pillar Cracked.wav", Music.class);
    }

    public void HercuelesHit(float dt) {

        if (hercule.getBoundingRectangle().overlaps(wall[i].getBoundingRectangle()) && Gdx.input.isKeyJustPressed(Main.normalPunch) && i < 4 && hercule.body.getPosition().x > (x+100f) / Main.PPM) {
            i++;
            hitted=true;
            crushess.setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
            crushess.setPosition(x / Main.PPM, y / Main.PPM);
            statetimer = 0;
            music.play();
            music.setVolume(Main.vol);
        }
        statetimer += dt;
        if (statetimer > 0.2f && hitted==true) {
            crushess.setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
            crushess.setPosition((x-250f) / Main.PPM, -30 / Main.PPM);
            statetimer1 = 0;
        }
        statetimer1 += dt;
        if (statetimer1 > 0.2f && hitted==true) {
            crushess.setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
            crushess.setPosition((x-100f) / Main.PPM, 0 / Main.PPM);
            statetimer2 = 0;
        }
        statetimer2 += dt;
        if (statetimer2 > 0.2f && hitted==true) {
            crushess.setBounds(0, 0, 0, 0);
            hitted=false;
        }
    }

    public void draw() {
        wall[i].draw(Main.batch);
        wall[i].setPosition(x / Main.PPM, y / Main.PPM);
        if(screen.restart && !once){
            world.destroyBody(body);
            once=true;
        }
        if (i == 4 && !getInOnce) {
            getInOnce=true;
            body.setActive(false);
        }
        drawCrushes();
    }

    public void drawExtra() {
        if (i > 3) {
            Extra.draw(Main.batch);
        }
        Extra.setPosition(x / Main.PPM, y / Main.PPM);
    }

    public void drawCrushes() {
        crushess.draw(Main.batch);
        crushess.setPosition(x / Main.PPM, y / Main.PPM);
    }

    public final void WallBlockDefinition() {
        bdef = new BodyDef();
        bdef.position.set((x+250f) / Main.PPM, (y+200f) / Main.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bdef);
        fdef = new FixtureDef();
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(80 / Main.PPM, 250 / Main.PPM);
        fdef.shape = polygon;
        body.createFixture(fdef);
    }
}