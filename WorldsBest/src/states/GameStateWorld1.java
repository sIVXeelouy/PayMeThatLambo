package states;

import $$$.Handler;
import java.awt.Graphics;
import worlds.World;
import worlds.World1;

public class GameStateWorld1 extends State { //everything that happens in this class will be run through game.tick() and game.render()


    public GameStateWorld1(Handler handler) {
        super(handler);
        world = new World1(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }

    @Override
    public World getWorld() {
        return world;
    }

}
