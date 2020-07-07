
package HealthAttacker;

import Screens.PlayScreen;
import HealthAttacker.FeatherSack;
import MovingObjects.Hercules;
import Scenes.HUD;
import Sprites.SilverCoin;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MovingFeather extends FeatherSack {
   
    public MovingFeather(float pposx, float pposy, PlayScreen Screen , int order) {
        super(pposx, pposy, Screen);
        yy = pposy;
        xx = pposx; xcopy = pposx;
        xdomin = xx +(500/Main.PPM);
        this.order = order;
        Rope = new Sprite();
        Rope.setRegion(new Texture("Sprites\\Level 1\\Complement\\Rope.png"));
        Rope.setBounds(0, 0, 30 / Main.PPM, 300 / Main.PPM);
        Rope.setPosition(xx + (40 / Main.PPM), yy + (150 / Main.PPM));
        move = false; right = true;
    }
    
    public void featherMoving(Hercules player) {
        if(order==1){
        
        if (player.getX() <= (this.getX() + 120 / Main.PPM) && player.getX() >= (this.getX() - 120 / Main.PPM)) {
            move = true;
        }

        if (move == true) {
            if (yy > 100 / Main.PPM) {
                yy -= 10 / Main.PPM;
            }

        }

    }
        else if(order == 2){
            
        if( right==true ){
            if(xx<xdomin)
            xx+= 5/Main.PPM ;
            else right = false ;
        }else {
              xx-= 5/Main.PPM ;
              if(xx<=xcopy) right=true;
        }
                        Rope.setPosition(xx + (27 / Main.PPM), yy + (90 / Main.PPM));

        }
        
                    setPosition(xx, yy);
    }
    
    @Override
    public void Update(){
            featherMoving(screen.getPlayer());
            if (featherCollsoin(screen.getPlayer()) == 2) {
                HUD.featherHit();
            } else {
                if (featherCollsoin(screen.getPlayer()) == 1) {
                        //HerculesActionSound("Audio//Hercules - sounds//featherFinish.wav");
                        
                        SilverCoin coin = new SilverCoin (screen, getX() * Main.PPM -100f  ,getY() * Main.PPM +200f);  coin.move=true; 
                        screen.creator.getCoins().add(coin);
                        screen.creator.getFeathers().removeValue(this, true);
                        HUD.score+=15;
                        } 
            }
    }
}
