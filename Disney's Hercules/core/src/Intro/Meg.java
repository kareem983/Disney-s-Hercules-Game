package Intro;

import Intro.LevelPassword;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Gdx;;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Meg extends Sprite {
        public World world ;
        public Body body;
       LevelPassword screen;
    TextureRegion region , Original,unlock;
    Animation <TextureRegion> walk , edit ;
  public  enum state { stand , walk , edit ,success};
    public state current , prev;
   public boolean right,Edit , v=false , st1=false,st2=false,st3=false,st4=false;

   public float stateTimer , EditTime = 0;
        public Meg(World world , LevelPassword screen){
            super (screen.getAtlas().findRegion("meg4"));
          this.world = world;
         this.screen = screen;
          right = true;
          Edit = false;
          defineMeg();
            Original = new TextureRegion(getTexture() , 1 ,138 , (830/14)-5 , 142 );


            setBounds(0,0,190,600);
        }
        public void defineMeg(){
            BodyDef bdef=new BodyDef();
            bdef.position.set(25,80);
            bdef.type = BodyDef.BodyType.DynamicBody;
            body = world.createBody(bdef);
            FixtureDef fdef = new FixtureDef();
            CircleShape  shape = new CircleShape();
            shape.setRadius(5);
            fdef.shape = shape;
          //  fdef.density = 0.5f;
           // fdef.restitution = 0.6f;
            //fdef.friction = 0.4f;
            body.createFixture(fdef);

            Array<TextureRegion> frames = new Array<TextureRegion>();
              setRegion(screen.getAtlas().findRegion("2"));
            for (int i=1;i<=5;i++)
            {
                frames.add(new TextureRegion(getTexture() , (i*(356/6))+1219, 301 , (356/6)-10 , 131 ));
            }

            walk = new Animation(0.13f , frames);
            frames.clear();
            setRegion(screen.getAtlas().findRegion("4"));
            frames.add(new TextureRegion(getTexture() , 1,5 , 58 , 131));
            frames.add(new TextureRegion(getTexture() , 120,5 , 76 , 131 ));
           frames.add(new TextureRegion(getTexture() , 205,5 , 76 , 131 ));
            frames.add(new TextureRegion(getTexture() , 286,5 , 76 , 131 ));
            frames.add(new TextureRegion(getTexture() , 59,5 , 60 , 131 ));
            setSize(170,550);
            setBounds(0,0 , 170,550);
            edit = new Animation(0.2f , frames);
            frames.clear();
            setRegion(screen.getAtlas().findRegion("meg6"));
            unlock=new TextureRegion(getTexture(),1871, 293,55, 139);
        }
boolean x = false;

        public void definePos(){

            if (body.getPosition().x < 190 || (body.getPosition().x <400 )) {
                st1 = true;
                st4=st3=st2=false;
            }
             else if (body.getPosition().x<570 || body.getPosition().x <730  )
            {
                st2 = true;
                st4=st3=st1=false;
            }
            else if (body.getPosition().x<910 || body.getPosition().x <1080 )
            {
                st3 = true;
                st4=st2=st1=false;
            }
            else if (body.getPosition().x>=1090  )
            {
                st4 = true;
                st2=st3=st1=false;
            }
            else {
                st1=st2=st3=st4=false;
            }

        }

public void update(float dt){
            stateTimer += dt;
    setPosition(body.getPosition().x , body.getPosition().y);
    setRegion(getFrame());
}
public TextureRegion getFrame(){
 TextureRegion e = new TextureRegion();
switch (getState()){
    case success:
        e = unlock;
        break;
    case walk:
            e = (TextureRegion) walk.getKeyFrame(stateTimer, true);

              break;
              case edit:
                  e = (TextureRegion) edit.getKeyFrame(stateTimer, true);
                  break;
              case stand:
                  e = Original;

                  break;
          }

       if (e == Original && ((body.getPosition().x > 260 &&body.getPosition().x < 450)||
                             (body.getPosition().x > 550 &&body.getPosition().x < 750)||
                             (body.getPosition().x > 890 &&body.getPosition().x < 1090)||
                             (body.getPosition().x > 1220 &&body.getPosition().x < 1450))  && !e.isFlipX() && right){
           right = false;
           e.flip(true,false);

       }
    if (e == Original && ((body.getPosition().x < 55 )||
            (body.getPosition().x <= 435 &&body.getPosition().x > 320)||
            (body.getPosition().x > 700 &&body.getPosition().x <= 775)||
            (body.getPosition().x <=1150 &&body.getPosition().x > 1045))  && e.isFlipX() && !right){
        right = true;
        e.flip(true,false);
    }
    else  if ( ((body.getLinearVelocity().x < 0|| !right)&& !e.isFlipX())  ) {
        // Looking left
        e.flip(true, false);
        right=false;
    }
    else if (((body.getLinearVelocity().x > 0||right) && e.isFlipX())) {
        //Looking Right
        e.flip(true, false);
        right=true;
    }
return  e;
}
public state getState(){
            if (screen.success)
                return state.success;
else if (body.getLinearVelocity().x > 0)
{
 right = true;
 return state.walk;
}
else if (body.getLinearVelocity().x < 0)
{
    right=false;
    return state.walk;
}
else
{

    if (Edit && EditTime <= 0.65f && v ) {
        EditTime+= Gdx.graphics.getDeltaTime();
        return state.edit;
    }
    else {
        EditTime=0;
        v = false ;
        System.out.println("Stand " + body.getPosition().x);
        return state.stand;
    }

}
}

}

/*
1- the position of meg with stands --
2- password**
 */