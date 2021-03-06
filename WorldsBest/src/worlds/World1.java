package worlds;

import $$$.Handler;
import entities.statics.LotteryBox;
import entities.statics.Stone;
import entities.statics.TreeApple;
import entities.statics.TreePear;
import entities.statics.TreePin;
import gfx.Animation;
import gfx.Assets;
import java.awt.Graphics;
import tiles.Tile;

public class World1 extends World {

    //Portal to the next world
    private final Animation portal;

    public World1(Handler handler, String path) {
        super(handler, path);
        
        entityManager.addEntity(new TreeApple(handler, 10 * Tile.TILE_WIDTH, 3 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreeApple(handler, 10 * Tile.TILE_WIDTH, 5 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePear(handler, 10 * Tile.TILE_WIDTH, 7 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePear(handler, 10 * Tile.TILE_WIDTH, 9 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePin(handler, 20 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePin(handler, 22 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePin(handler, 24 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePin(handler, 26 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePin(handler, 20 * Tile.TILE_WIDTH, 3 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePin(handler, 22 * Tile.TILE_WIDTH, 3 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePin(handler, 24 * Tile.TILE_WIDTH, 3 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePin(handler, 26 * Tile.TILE_WIDTH, 3 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePin(handler, 20 * Tile.TILE_WIDTH, 5 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePin(handler, 22 * Tile.TILE_WIDTH, 5 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePin(handler, 24 * Tile.TILE_WIDTH, 5 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new TreePin(handler, 26 * Tile.TILE_WIDTH, 5 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new Stone(handler, 5 * Tile.TILE_WIDTH, 20 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new Stone(handler, 5 * Tile.TILE_WIDTH, 22 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new Stone(handler, 5 * Tile.TILE_WIDTH, 24 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new Stone(handler, 7 * Tile.TILE_WIDTH, 20 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new Stone(handler, 7 * Tile.TILE_WIDTH, 22 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new Stone(handler, 7 * Tile.TILE_WIDTH, 24 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new Stone(handler, 9 * Tile.TILE_WIDTH, 20 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new Stone(handler, 9 * Tile.TILE_WIDTH, 22 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new Stone(handler, 9 * Tile.TILE_WIDTH, 24 * Tile.TILE_HEIGHT));
        entityManager.addEntity(new LotteryBox(handler, 30 * Tile.TILE_WIDTH, 10 * Tile.TILE_HEIGHT));
        
        //setting the portal animation
        portal = new Animation(300, Assets.portalImages);
        //in game tick() we check if the players sits on the portal and if so he goes to the 2nd world
    }

    @Override
    public void tick() {
        itemManager.tick();
        entityManager.tick();
        portal.tick();
    }
    
    @Override
    public void render(Graphics g) { //for efficiecy, we render only the tiles that we see on our screen while moving
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) { //the block that actually creates our world image
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        //Items
        itemManager.render(g);
        //Portal
        g.drawImage(portal.getCurrentFrame(), (int) (50 * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                (int) (25 * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()), Tile.TILE_WIDTH, Tile.TILE_HEIGHT,
                null);
        //Entities
        entityManager.render(g);

    }

}
