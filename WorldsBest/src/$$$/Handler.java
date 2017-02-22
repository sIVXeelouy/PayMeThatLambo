package $$$;

import entities.creatures.Player;
import gfx.GameCamera;
import input.KeyManager;
import input.MouseManager;
import worlds.World;

public class Handler { //this class helps us access game,world,... through just one object
    
    private Game game;
    private World world;
    private Player player;
    
    public Handler(Game game) {
        this.game = game;
    }

    public int getWidth() {
        return game.getWidth();
    }
    
    public int getHeight() {
        return game.getHeight();
    }
    
    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }
    
    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }
    
    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }
    
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    
}
