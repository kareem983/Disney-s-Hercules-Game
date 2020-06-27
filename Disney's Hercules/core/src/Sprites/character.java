package Sprites;
import MovingObjects.Hercules;
import Screens.Level2;
import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
public class character extends Sprite {
     World world;
     Level2 screen;
     int posx, posy ;
     public boolean GET[] = new boolean[8], getty2[] = new boolean[8];
    public  Sprite sprites[] , sprite1;
    int x1,x2,x3,x4,x5,x6,x7,x8,y1,y2,y3,y4,y5,y6,y7,y8;
     public character(World world, Level2 screen, int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4,int x5,int y5,int x6,int y6,int x7,int y7,int x8,int y8) {
          this.world = world;
          this.screen = screen;
          this.x1 = x1;this.y1 = y1;
          this.x2 = x2;this.y2 = y2;
         this.x3 = x3;this.y3 = y3;
         this.x4 = x4;this.y4 = y4;
         this.x5 = x5;this.y5 = y5;
         this.x6 = x6;this.y6 = y6;
         this.x7 = x7;this.y7 = y7;
         this.x8 = x8;this.y8 = y8;
          sprite1 = new Sprite(new Texture("Sprites/Level 2/Collect name.png"));
          sprites = new Sprite[8];
          sprites[0] = new Sprite(new Texture("Sprites/Level 2/Name/H.png"));
          sprites[1] = new Sprite(new Texture("Sprites/Level 2/Name/E.png"));
          sprites[2] = new Sprite(new Texture("Sprites/Level 2/Name/R.png"));
          sprites[3] = new Sprite(new Texture("Sprites/Level 2/Name/C.png"));
          sprites[4] = new Sprite(new Texture("Sprites/Level 2/Name/U.png"));
          sprites[5] = new Sprite(new Texture("Sprites/Level 2/Name/L.png"));
          sprites[6] = new Sprite(new Texture("Sprites/Level 2/Name/E.png"));
          sprites[7] = new Sprite(new Texture("Sprites/Level 2/Name/S.png"));



          sprites[0].setPosition(x1 / Main.PPM, y1 / Main.PPM);
          sprites[0].setBounds(0, 0, 80 / Main.PPM, 80 / Main.PPM);
          sprites[0].setRegion(sprites[0].getTexture());
          sprites[1].setPosition(x2 / Main.PPM, y2 / Main.PPM);
          sprites[1].setBounds(0, 0, 80 / Main.PPM, 80 / Main.PPM);
          sprites[1].setRegion(sprites[1].getTexture());
          sprites[2].setPosition(x3 / Main.PPM, y3 / Main.PPM);
          sprites[2].setBounds(0, 0, 80 / Main.PPM, 80 / Main.PPM);
          sprites[2].setRegion(sprites[2].getTexture());
          sprites[3].setPosition(x4 / Main.PPM, y4 / Main.PPM);
          sprites[3].setBounds(0, 0, 80 / Main.PPM, 80 / Main.PPM);
          sprites[3].setRegion(sprites[3].getTexture());
          sprites[4].setPosition(x5/ Main.PPM, y5 / Main.PPM);
          sprites[4].setBounds(0, 0, 80 / Main.PPM, 80 / Main.PPM);
          sprites[4].setRegion(sprites[4].getTexture());
          sprites[5].setPosition(x6 / Main.PPM, y6 / Main.PPM);
          sprites[5].setBounds(0, 0, 80 / Main.PPM, 80 / Main.PPM);
          sprites[5].setRegion(sprites[5].getTexture());
          sprites[6].setPosition(x7 / Main.PPM, y7 / Main.PPM);
          sprites[6].setBounds(0, 0, 80 / Main.PPM, 80 / Main.PPM);
          sprites[6].setRegion(sprites[6].getTexture());
          sprites[7].setPosition(x8 / Main.PPM, y8 / Main.PPM);
          sprites[7].setBounds(0, 0, 80 / Main.PPM, 80 / Main.PPM);
          sprites[7].setRegion(sprites[7].getTexture());



          sprite1.setPosition(screen.getPlayer().b2body.getPosition().x -(500/Main.PPM)  ,600/Main.PPM );
         sprite1.setBounds(0, 0, 1500 / Main.PPM, 200 / Main.PPM);
         sprite1.setRegion(sprite1.getTexture());
     }

    void Collision(){
for (int i=0;i<8;i++){
     if(sprites[i].getBoundingRectangle().overlaps(screen.getPlayer().getBoundingRectangle()))
          GET[i] =true;


}
    }
    void Board(){

    }
     public void update() {
Collision();

         sprites[0].setPosition(x1 / Main.PPM, y1 / Main.PPM);
         sprites[0].setRegion(sprites[0].getTexture());
         sprites[1].setPosition(x2 / Main.PPM, y2 / Main.PPM);
         sprites[1].setRegion(sprites[1].getTexture());
         sprites[2].setPosition(x3 / Main.PPM, y3 / Main.PPM);
         sprites[2].setRegion(sprites[2].getTexture());
         sprites[3].setPosition(x4 / Main.PPM, y4 / Main.PPM);
         sprites[3].setRegion(sprites[3].getTexture());
         sprites[4].setPosition(x5/ Main.PPM, y5 / Main.PPM);
         sprites[4].setRegion(sprites[4].getTexture());
         sprites[5].setPosition(x6 / Main.PPM, y6 / Main.PPM);
         sprites[5].setRegion(sprites[5].getTexture());
         sprites[6].setPosition(x7 / Main.PPM, y7 / Main.PPM);
         sprites[6].setRegion(sprites[6].getTexture());
         sprites[7].setPosition(x8 / Main.PPM, y8 / Main.PPM);
         sprites[7].setRegion(sprites[7].getTexture());


         sprite1.setPosition(screen.getPlayer().b2body.getPosition().x -(600/Main.PPM) ,600/Main.PPM );

         sprite1.setRegion(sprite1.getTexture());
     }
}
