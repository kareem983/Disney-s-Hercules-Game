package Pooling;

import HealthAttacker.FeatherSack;
import Screens.PlayScreen;
import com.badlogic.gdx.utils.Pool;

public class FeatherPool extends Pool<FeatherSack>{
    public PlayScreen screen; 
    public float x, y;

    @Override
    protected FeatherSack newObject() {
        return new FeatherSack(screen, x, y);
    }
    
}
