
package Tools;

import HealthAttacker.Enemy;
import MovingObjects.Hercules;
import MovingObjects.Wagon;
import com.Hercules.game.Main;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener{
    public Hercules player;
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        
        int collisionDefinition = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        switch (collisionDefinition){
            case Main.HERCULES_BIT | Main.GROUND_BIT:
               if (player.body.getLinearVelocity().y <= 0)
                     Hercules.onGround = true;
                break;
            case Main.HERCULES_BORDER_BIT | Main.ENEMY_BIT:
                if (fixA.getFilterData().categoryBits == Main.ENEMY_BIT && Hercules.hercules_sword2)
                    ((Enemy)fixA.getUserData()).Stap();
                else if (Hercules.hercules_sword2)
                    ((Enemy)fixB.getUserData()).Stap(); 
                //      Damaging of The Baby Dragon To Hercules
                else if (fixA.getFilterData().categoryBits == Main.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).attackHercules();
                else if (fixB.getFilterData().categoryBits == Main.ENEMY_BIT)
                    ((Enemy)fixB.getUserData()).attackHercules();  
                break;
            case Main.ENEMY_BIT | Main.SKY_BORDER_BIT:
                if (fixA.getFilterData().categoryBits == Main.ENEMY_BIT && Hercules.hercules_sword)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true,false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case Main.HERCULES_BIT | Main.WAGON_BIT:
                if (fixA.getFilterData().categoryBits == Main.WAGON_BIT)
                    ((Wagon)fixA.getUserData()).move();
                else
                    ((Wagon)fixB.getUserData()).move();
            break;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        
        int collisionDefinition = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        switch (collisionDefinition){
            case Main.HERCULES_BIT | Main.GROUND_BIT:
                if (player.body.getLinearVelocity().y>0)
                         Hercules.onGround = false;   
                break;
            case Main.HERCULES_BIT | Main.WAGON_BIT:
                if (fixA.getFilterData().categoryBits == Main.WAGON_BIT)
                    ((Wagon)fixA.getUserData()).stop();
                else
                    ((Wagon)fixB.getUserData()).stop();
            break;
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
    }
    
    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {
    }
    
}
