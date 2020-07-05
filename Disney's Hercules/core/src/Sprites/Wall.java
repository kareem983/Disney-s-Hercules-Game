package Sprites;

import MovingObjects.Hercules;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Wall extends Sprite {

    public Hercules hercule;
    public BodyDef bdef;
    public FixtureDef fdef;
    public Body b2body;
    public World world;
    public Sprite[] wall = new Sprite[5];
    public Sprite Extra;
    public Main game;
    public float x;
    public float y;
    public int i = 0;
    public int counter = 0;
    public float statetimer,statetimer1,statetimer2;
    public Sprite crushess;
    public boolean hitted=false;

    public Wall(World world, Hercules herucle, Main game, float x, float y) {
        this.hercule = herucle;
        this.game = game;
        this.world = world;
        this.x = x;
        this.y = y;
        statetimer = 0;
        statetimer1 = 0;
        statetimer2 = 0;
        WallBlock();
        wall[0] = new Sprite(new Texture("Sprites//Level 2//Block//0.png"));
        wall[0].setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
        wall[1] = new Sprite(new Texture("Sprites//Level 2//Block//1.png"));
        wall[1].setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
        wall[2] = new Sprite(new Texture("Sprites//Level 2//Block//2.png"));
        wall[2].setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
        wall[3] = new Sprite(new Texture("Sprites//Level 2//Block//3.png"));
        wall[3].setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
        wall[4] = new Sprite(new Texture("Sprites//Level 2//Block//4.png"));
        wall[4].setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
        Extra = new Sprite(new Texture("Sprites//Level 2//Block//5.png"));
        Extra.setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
        Texture crushes = new Texture("Sprites//Level 2//Block//crushes.png");
        crushess = new Sprite(crushes);
        crushess.setBounds(0, 0, 0, 0);
    }

    public void HercuelesHit() {

        if (hercule.getBoundingRectangle().overlaps(wall[i].getBoundingRectangle()) && Gdx.input.isKeyJustPressed(Main.normalPunch) && i < 4 && hercule.body.getPosition().x > 2100 / Main.PPM) {
            i++;
             hitted=true;
            crushess.setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
            crushess.setPosition(x / Main.PPM, y / Main.PPM);
            statetimer = 0;
        }
        statetimer += Gdx.graphics.getDeltaTime();
        if (statetimer > 0.2f && hitted==true) {
            crushess.setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
            crushess.setPosition(1750 / Main.PPM, -30 / Main.PPM);
            statetimer1 = 0;
        }
        statetimer1 += Gdx.graphics.getDeltaTime();
        if (statetimer1 > 0.2f && hitted==true) {
            crushess.setBounds(0, 0, 500 / Main.PPM, 500 / Main.PPM);
            crushess.setPosition(1900 / Main.PPM, 0 / Main.PPM);
            statetimer2 = 0;
        }
        statetimer2 += Gdx.graphics.getDeltaTime();
        if (statetimer2 > 0.2f && hitted==true) {
            crushess.setBounds(0, 0, 0, 0);
            hitted=false;
        }
    }

    public void draw() {
        wall[i].draw(game.batch);
        wall[i].setPosition(x / Main.PPM, y / Main.PPM);
        if (i == 4 && counter == 0) {
            counter++;
            world.destroyBody(b2body);
        }

    }

    public void drawExtra() {
        if (i > 3) {
            Extra.draw(game.batch);
        }
        Extra.setPosition(x / Main.PPM, y / Main.PPM);
    }

    public void drawCrushes() {
        crushess.draw(game.batch);
        crushess.setPosition(x / Main.PPM, y / Main.PPM);
    }

    public void WallBlock() {
        bdef = new BodyDef();
        bdef.position.set(2300 / Main.PPM, 300 / Main.PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);
        fdef = new FixtureDef();
        PolygonShape polygon = new PolygonShape();
        polygon.setAsBox(100 / Main.PPM, 200 / Main.PPM);
        fdef.shape = polygon;
        b2body.createFixture(fdef);

    }
}
