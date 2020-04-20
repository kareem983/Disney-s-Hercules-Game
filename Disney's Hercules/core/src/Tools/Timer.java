
package Tools;

import Scenes.Hud;
import MovingObjects.Hercules;
import com.badlogic.gdx.Gdx;

public class Timer {
    public Hercules herucle;
    public Hud hud;
    public float statetimer=0;
    public Timer(Hercules herucle,Hud hud) 
    {
        this.herucle=herucle;
        this.hud=hud;
    }
    public void update()
    {
          if(herucle.pickedlightsword==true)
        {
              statetimer += Gdx.graphics.getDeltaTime();
              hud.swordtimerlabel.setText(String.format("%.0f",statetimer));
              
              if(statetimer>10)
              {
                  herucle.pickedlightsword=false;
                 statetimer=0;
                 hud.swordtimerlabel.setText(String.format("%.0f",statetimer));
              }
        }
            if(herucle.pickedfireballsword==true)
        {
              statetimer += Gdx.graphics.getDeltaTime();
              hud.swordtimerlabel.setText(String.format("%.0f",statetimer));
             
              if(statetimer>8)
              {
                  herucle.pickedfireballsword=false;
                  statetimer=0;
                  hud.swordtimerlabel.setText(String.format("%.0f",statetimer));
              }
        }
              if(herucle.pickedsonicsword==true)
        {
              statetimer += Gdx.graphics.getDeltaTime();
              hud.swordtimerlabel.setText(String.format("%.0f",statetimer));
              
              if(statetimer>6)
              {
                  herucle.pickedsonicsword=false;
                  statetimer=0;
                  hud.swordtimerlabel.setText(String.format("%.0f",statetimer));
              }
        }
    }
}
