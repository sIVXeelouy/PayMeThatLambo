package states;

import java.awt.Graphics;
import $$$.Handler;
import worlds.World;

public abstract class State {
    
    //the state manager code
    private static State currentState = null;
    
    public static void setState(State state) {
        currentState =  state;
    }
    
    public static State getState() {
        return currentState;
    }
    
    //the real code
    protected Handler handler;
    protected World world;
    
    public State(Handler handler) {
        this.handler = handler;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public abstract World getWorld();
}
