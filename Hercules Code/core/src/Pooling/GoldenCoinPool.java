
package Pooling;

import Screens.PlayScreen;
import Sprites.Coin;
import Sprites.GoldenCoin;
import com.badlogic.gdx.utils.Pool;

public class GoldenCoinPool extends Pool<Coin>{
    public PlayScreen screen; 
    public float x, y;
    
    @Override
    protected GoldenCoin newObject() {
        return new GoldenCoin(screen, x, y);
        }
    }
