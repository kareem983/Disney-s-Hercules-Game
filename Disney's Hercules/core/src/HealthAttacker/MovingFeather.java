/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HealthAttacker;

import Screens.PlayScreen;
import HealthAttacker.FeatherSack;
import MovingObjects.Hercules;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author computer center
 */
public class MovingFeather extends FeatherSack {

    public Sprite Rope;
    public boolean move ,right;
    float x, y, xdomin, xcopy;
   public int order ;
    public MovingFeather(float pposx, float pposy, World wworld, PlayScreen Screen , int order) {
        super(pposx, pposy, wworld, Screen);
        y = pposy;
        x = pposx; xcopy = pposx;
        xdomin = x +(500/Main.PPM);
        this.order = order;
        Rope = new Sprite();
        Rope.setRegion(new Texture("Sprites\\Rope.png"));
        Rope.setBounds(0, 0, 30 / Main.PPM, 300 / Main.PPM);
        Rope.setPosition(x + (40 / Main.PPM), y + (150 / Main.PPM));
        move = false; right = true;
    }

    public void featherMoving(Hercules player) {
        if(order==1){
        
        if (player.getX() <= (this.getX() + 120 / Main.PPM) && player.getX() >= (this.getX() - 120 / Main.PPM)) {
            move = true;
        }

        if (move == true) {
            if (y > 100 / Main.PPM) {
                y -= 10 / Main.PPM;
            }

        }

    }
        else if(order == 2){
            
        if( right==true ){
            if(x<xdomin)
            x+= 5/Main.PPM ;
            else right = false ;
        }else {
              x-= 5/Main.PPM ;
              if(x<=xcopy) right=true;
        }
                        Rope.setPosition(x + (27 / Main.PPM), y + (90 / Main.PPM));

        }
        
                    setPosition(x, y);

        
        
    }

}
