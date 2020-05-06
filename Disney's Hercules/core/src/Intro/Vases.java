package Intro;

import Intro.LevelPassword;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

public class Vases extends Sprite{
   LevelPassword screen;
   World world;
    Array<TextureRegion>v1,v2,v3,v4;
    public int Counterv1,Counterv2,Counterv3,Counterv4;
   public Sprite s1,s2,s3,s4;
    public Vases(World world , LevelPassword screen)
    {
        v1= new Array<TextureRegion>();
       v4= new Array<TextureRegion>();
       v3= new Array<TextureRegion>();
        v2= new Array<TextureRegion>();
        Counterv1 = Counterv2=Counterv3=Counterv4=0;
        this.screen=screen;
        this.world=world;

          s1=new Sprite(screen.getVasatlas().findRegion("11"));
          s2=new Sprite(screen.getVasatlas().findRegion("21"));
          s3=new Sprite(screen.getVasatlas().findRegion("31"));
          s4=new Sprite(screen.getVasatlas().findRegion("41"));
        addVasesToArray();
    }
    void addVasesToArray() {

        v1.add(new TextureRegion(s1.getTexture(), 804, 4,39, 50) );
        s1.setRegion(screen.getVasatlas().findRegion("12"));
        v1.add(new TextureRegion(s1.getTexture(), 673, 2,42, 52) );
        s1.setRegion(screen.getVasatlas().findRegion("13"));
        v1.add(new TextureRegion(s1.getTexture(), 717, 5,43, 49) );
        s1.setRegion(screen.getVasatlas().findRegion("14"));
        v1.add(new TextureRegion(s1.getTexture(), 762, 2,40, 52) );
        s2.setRegion(screen.getVasatlas().findRegion("21"));
        v2.add(new TextureRegion(s2.getTexture(), 191, 3,59, 51) );
        s2.setRegion(screen.getVasatlas().findRegion("22"));
        v2.add(new TextureRegion(s2.getTexture(), 65, 4,61, 50) );
        s2.setRegion(screen.getVasatlas().findRegion("23"));
        v2.add(new TextureRegion(s2.getTexture(), 128, 5,61, 49    ) );
        s2.setRegion(screen.getVasatlas().findRegion("24"));
        v2.add(new TextureRegion(s2.getTexture(),  1, 1 ,62, 53) );
        s3.setRegion(screen.getVasatlas().findRegion("31"));
        v3.add(new TextureRegion(s3.getTexture(),  252, 4,54, 50) );
        s3.setRegion(screen.getVasatlas().findRegion("32"));
        v3.add(new TextureRegion(s3.getTexture(), 308, 5,55, 49) );
        s3.setRegion(screen.getVasatlas().findRegion("33"));
        v3.add(new TextureRegion(s3.getTexture(), 421, 6,53, 48) );
        s3.setRegion(screen.getVasatlas().findRegion("34"));
        v3.add(new TextureRegion(s3.getTexture(), 365, 4, 54, 50) );
        s4.setRegion(screen.getVasatlas().findRegion("41"));
        v4.add(new TextureRegion(s4.getTexture(), 527, 4,48, 50) );
        s4.setRegion(screen.getVasatlas().findRegion("42"));
        v4.add(new TextureRegion(s4.getTexture(), 577, 4,46, 50) );
        s4.setRegion(screen.getVasatlas().findRegion("43"));
        v4.add(new TextureRegion(s4.getTexture(), 476, 4,49, 50) );
        s4.setRegion(screen.getVasatlas().findRegion("44"));
        v4.add(new TextureRegion(s4.getTexture(), 625, 4,46, 50) );


        s1.setBounds(0,0,120,230);
        s2.setBounds(0,0,150,190);
        s3.setBounds(0,0,140,210);
        s4.setBounds(0,0,130,200);
    }
   public void update(float dt){
     System.out.println(Counterv1 + " "+ Counterv2 + " " + Counterv3 + " "+Counterv4 );
     if (Counterv1 == -1)Counterv1=3;
     if (Counterv1 == 4)Counterv1=0;
       if (Counterv2 == -1)Counterv2=3;
       if (Counterv2 == 4)Counterv2=0;
       if (Counterv3 == -1)Counterv3=3;
       if (Counterv3 == 4)Counterv3=0;
       if (Counterv4 == -1)Counterv4=3;
       if (Counterv4 == 4)Counterv4=0;
        s1.setPosition(210 , 370);
        s1.setRegion(v1.get(Counterv1));
        s2.setPosition(575,360);
        s2.setRegion(v2.get(Counterv2));
       s3.setPosition(920,360);
       s3.setRegion(v3.get(Counterv3));
       s4.setPosition(1280,360);
       s4.setRegion(v4.get(Counterv4));

       if (screen.success)
       {
           s1.setPosition(210,500);
           s2.setPosition(575,500);
           s3.setPosition(920,500);
           s4.setPosition(1280,500);
       }
    }
}
