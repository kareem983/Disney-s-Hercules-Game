package Pooling;

import HealthAttacker.VultureEgg;
import com.badlogic.gdx.utils.Pool;

public class EggPool extends Pool<VultureEgg>{ // CLASS TO FREE MEMORY
    @Override
    protected VultureEgg newObject() {
        return new VultureEgg();
        }
}
