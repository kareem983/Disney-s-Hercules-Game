package Sprites;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


public abstract class Coin extends Sprite{
    protected int startX;
    protected int startY;
    protected int width;
    protected int height;
    protected int posX;
    protected int posY;
    protected World world;
    protected Animation CoinDraw;
    protected float stateTimer;
    
     public Coin(World world, PlayScreen screen ,int startX,int startY,int width,int height,int posX,int posY){
         this.startX=startX;
         this.startY=startY;
         this.width=width;
         this.height=height;
         this.posX=posX;
         this.posY=posY;
         this.world=world;
         this.stateTimer=0;
         setBounds(0,0,37/Main.PPM,60/Main.PPM);
         DefineAnimation();
     }
     
     public void update(float dt){
         setPosition(this.posX /Main.PPM , this.posY /Main.PPM);
         stateTimer+=Gdx.graphics.getDeltaTime();
         setRegion((TextureRegion) CoinDraw.getKeyFrame(stateTimer,true ));
         if(stateTimer>10)stateTimer=0;
     }
     
     public abstract void DefineAnimation();
     
}
   