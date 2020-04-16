package Sprites;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class SilverCoin extends Coin{

    
   public SilverCoin(World world ,PlayScreen screen,int posX, int posY){
      super(world,screen,0,0,563,564,posX,posY);  
   }

   
   
   @Override
   public void DefineAnimation(){
    
     Array<TextureRegion> frames=new Array<TextureRegion>();    
     
     frames.add(new TextureRegion(new Texture("Sprites\\Silver_1.png"),this.startX,this.startY,this.width,this.height));
     frames.add(new TextureRegion(new Texture("Sprites\\Silver_2.png"),this.startX,this.startY,559,this.height));
     frames.add(new TextureRegion(new Texture("Sprites\\Silver_3.png"),this.startX,this.startY,504,this.height));
     frames.add(new TextureRegion(new Texture("Sprites\\Silver_4.png"),this.startX,this.startY,428,565));
     frames.add(new TextureRegion(new Texture("Sprites\\Silver_5.png"),this.startX,this.startY,262,this.height));
     frames.add(new TextureRegion(new Texture("Sprites\\Silver_6.png"),this.startX,this.startY,108,this.height));
     frames.add(new TextureRegion(new Texture("Sprites\\Silver_7.png"),this.startX,this.startY,262,this.height));
     frames.add(new TextureRegion(new Texture("Sprites\\Silver_8.png"),this.startX,this.startY,503,565));
     frames.add(new TextureRegion(new Texture("Sprites\\Silver_9.png"),this.startX,this.startY,503,this.height));
     frames.add(new TextureRegion(new Texture("Sprites\\Silver_10.png"),this.startX,this.startY,559,this.height));
      
         CoinDraw=new Animation(0.09f,frames);
         frames.clear();
   }
}
