package Pooling;

import Screens.PlayScreen;
import Sprites.Coin;
import Sprites.SilverCoin;
import com.badlogic.gdx.utils.Pool;

public 
    class SilverCoinPool extends Pool<Coin>{ // CLASS TO FREE MEMORY
     
    public PlayScreen screen;
    public float x, y;
    
    @Override
    protected SilverCoin newObject() {
        return new SilverCoin(screen, x, y);
        }
    }
