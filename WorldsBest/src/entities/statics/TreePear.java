package entities.statics;

import $$$.Handler;
import gfx.Assets;
import items.Item;
import java.awt.Graphics;
import java.util.Random;
import tiles.Tile;

public class TreePear extends StaticEntity {

    public TreePear(Handler handler, float x, float y) {
        super(handler, x, y, 2 * Tile.TILE_WIDTH, 2 * Tile.TILE_HEIGHT);
        
        bounds.x = Tile.TILE_WIDTH;
        bounds.y = 3 * Tile.TILE_WIDTH / 2;
        bounds.width = Tile.TILE_WIDTH / 4;
        bounds.height = Tile.TILE_WIDTH / 3;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void die() { //when we destroy the tree we want to see the item
        Random woodRand = new Random();
        int woodNumber = 1 + woodRand.nextInt(3); //a random number of wood items from 1 to 3 will be dropped
        for (int i = 0; i < woodNumber; i++) {
            handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y + i * Tile.TILE_HEIGHT));
        }
        Random pearRand = new Random();
        int pearNumber = 1 + pearRand.nextInt(3); //a random number of pear items from 1 to 3 will be dropped
        for (int i = 0; i < pearNumber; i++) {
            handler.getWorld().getItemManager().addItem(Item.pearItem.createNew((int) x + 50, (int) y + i * Tile.TILE_HEIGHT));
        }
        if (new Random().nextInt(6) + new Random().nextInt(6) + new Random().nextInt(6) > 12) {
            handler.getWorld().getItemManager().addItem(Item.dbItem.createNew((int) x - 50, (int) y));
        }
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.treePear, (int) (x - handler.getGameCamera().getxOffset()), 
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
    
}
