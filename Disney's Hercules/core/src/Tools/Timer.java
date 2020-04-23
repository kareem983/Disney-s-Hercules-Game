
package Tools;

import Scenes.HUD;
import MovingObjects.Hercules;
import com.badlogic.gdx.Gdx;

public class Timer {
    public Hercules herucle;
    public HUD hud;
    public float statetimer1=10,statetimer2=8,statetimer3=6;
    public Timer(Hercules herucle,HUD hud) 
    {
        this.herucle=herucle;
        this.hud=hud;
    }
    public void update()
    {
          if(herucle.pickedlightsword==true)
        {
            
              statetimer1 -= Gdx.graphics.getDeltaTime();
              hud.swordtimerlabel.setText(String.format("%.0f",statetimer1));
              
              if(statetimer1<0)
              {
                  herucle.pickedlightsword=false;
                 statetimer1=0;
                 hud.swordtimerlabel.setText(String.format("%.0f",statetimer1));
              }
        }
            if(herucle.pickedfireballsword==true)
        {
            
              statetimer2 -= Gdx.graphics.getDeltaTime();
              hud.swordtimerlabel.setText(String.format("%.0f",statetimer2));
             
              if(statetimer2<0)
              {
                  herucle.pickedfireballsword=false;
                  statetimer2=0;
                  hud.swordtimerlabel.setText(String.format("%.0f",statetimer2));
              }
        }
              if(herucle.pickedsonicsword==true)
        {
              statetimer3 -= Gdx.graphics.getDeltaTime();
              hud.swordtimerlabel.setText(String.format("%.0f",statetimer3));
              
              if(statetimer3<0)
              {
                  herucle.pickedsonicsword=false;
                  statetimer3=0;
                  hud.swordtimerlabel.setText(String.format("%.0f",statetimer3));
              }
        }
    }
}