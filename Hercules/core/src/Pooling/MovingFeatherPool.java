package Pooling;

import HealthAttacker.MovingFeather;
import Screens.PlayScreen;
import com.badlogic.gdx.utils.Pool;

public class MovingFeatherPool extends Pool<MovingFeather>{
    public PlayScreen screen; 
    public float x, y;
    public int order;
    
    @Override
    protected MovingFeather newObject() {
        return new MovingFeather(screen, x, y, order);
    }
    
}
