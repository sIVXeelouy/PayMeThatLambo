package items;

import $$$.Handler;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {  //will store items that are currently on the game, lying on the ground
    
    private Handler handler;
    private ArrayList <Item> items;
    
    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<>();
    }
    
    public void tick() {
        Iterator <Item> it = items.iterator();
        while(it.hasNext()) {
            Item i = it.next();
            i.tick();
            if (i.isPickedUp()) { //remove the item from the game world if the player has picked it
                it.remove();
            }
        }
    }
    
    public void render(Graphics g) {
        for (Item i : items) {
            i.render(g);
        }
    }
    
    public void addItem(Item i) {
        i.setHandler(handler); //we do this because initially the item's handler is null
        items.add(i);
    }

    
    
    //GETTERS AND SETTERS
    public Handler getHandler() {
        return handler;
    }
    
    
    
}
