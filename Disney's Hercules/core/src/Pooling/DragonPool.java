package Pooling;

import HealthAttacker.BabyDragon;
import Screens.PlayScreen;
import com.badlogic.gdx.utils.Pool;

public class DragonPool extends Pool<BabyDragon>{
    public PlayScreen screen; 
    public float x, y;

    @Override
    protected BabyDragon newObject() {
        return new BabyDragon(screen, x, y);
    }
    
}
