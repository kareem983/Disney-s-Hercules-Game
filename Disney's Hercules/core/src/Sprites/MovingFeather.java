/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import Screens.PlayScreen;
import com.Hercules.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author computer center
 */
public class MovingFeather extends FeatherSack {
    public Sprite Rope ;
    public boolean move ;
    float x, y ;
   public MovingFeather(float pposx, float pposy, World wworld, PlayScreen Screen) {
        super(pposx, pposy, wworld, Screen); y = pposy; x = pposx;
        Rope =new Sprite();
    Rope.setRegion(new Texture("Sprites\\Rope.png"));
        Rope.setBounds(0, 0, 30/Main.PPM, 300/Main.PPM);
       Rope.setPosition(pposx+(40/Main.PPM), pposy+(150/Main.PPM)  );
        move = false;
    }
   public void featherMoving(Hercules player){
    if(player.getX()<=(this.getX() +120/Main.PPM) && player.getX()>=(this.getX() -120/Main.PPM)  ) move = true ;

     if(move == true ){
         if (y>100/Main.PPM ) y -= 10/Main.PPM ;
         setPosition(x, y);
    }    
   }

  
    
}
