package entities.statics;

import $$$.Handler;
import gfx.Assets;
import items.Item;
import java.awt.Graphics;
import java.util.Random;
import tiles.Tile;

public class Stone extends StaticEntity {

    public Stone(Handler handler, float x, float y) {
        super(handler, x, y, 2 * Tile.TILE_WIDTH, 2 * Tile.TILE_HEIGHT);
        
        bounds.x = Tile.TILE_WIDTH / 8;
        bounds.y = Tile.TILE_WIDTH / 8;
        bounds.width = 2 * Tile.TILE_WIDTH;
        bounds.height = 2 * Tile.TILE_WIDTH;
    }

    @Override
    public void tick() {
        
    }
    
    @Override
    public void die() { //when we destroy the stone we want to see the item
        Random stoneRand = new Random();
        int stoneNumber = 1 + stoneRand.nextInt(3); //a random number of stone items from 1 to 3 will be dropped
        for (int i = 0; i < stoneNumber; i++) {
            handler.getWorld().getItemManager().addItem(Item.stoneItem.createNew((int) x, (int) y + i * Tile.TILE_HEIGHT));
        }
        if (new Random().nextInt(6) + new Random().nextInt(6) + new Random().nextInt(6) > 12) {
            handler.getWorld().getItemManager().addItem(Item.dbItem.createNew((int) x - 50, (int) y));
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.stone, (int) (x - handler.getGameCamera().getxOffset()), 
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

}
