package entities.statics;

import $$$.Handler;
import gfx.Assets;
import items.Item;
import java.awt.Graphics;
import java.util.Random;
import tiles.Tile;

public class TreePin extends StaticEntity {

    public TreePin(Handler handler, float x, float y) {
        super(handler, x, y, 2 * Tile.TILE_WIDTH, 4 * Tile.TILE_HEIGHT);
        
        bounds.x = 2 * Tile.TILE_WIDTH / 3;
        bounds.y = 3 * Tile.TILE_WIDTH;
        bounds.width = 2 * Tile.TILE_WIDTH / 3;
        bounds.height = 2 * Tile.TILE_WIDTH / 3;
    }

    @Override
    public void tick() {
        
    }
    
    @Override
    public void die() { //when we destroy the tree we want to see the item
        Random woodRand = new Random();
        int woodNumber = 1 + woodRand.nextInt(4); //a random number of wood items from 1 to 4 will be dropped
        for (int i = 0; i < woodNumber; i++) {
            handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y + i * Tile.TILE_HEIGHT));
        }
        Random coneRand = new Random();
        int coneNumber = 1 + coneRand.nextInt(3); //a random number of cone items from 1 to 3 will be dropped
        for (int i = 0; i < coneNumber; i++) {
            handler.getWorld().getItemManager().addItem(Item.coneItem.createNew((int) x + 50, (int) y + i * Tile.TILE_HEIGHT));
        }
        if (new Random().nextInt(6) + new Random().nextInt(6) + new Random().nextInt(6) > 12) {
            handler.getWorld().getItemManager().addItem(Item.dbItem.createNew((int) x - 50, (int) y));
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.treePin, (int) (x - handler.getGameCamera().getxOffset()), 
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    
    
}
