package inventory;

import $$$.Handler;
import com.sun.glass.events.KeyEvent;
import gfx.Assets;
import gfx.Text;
import items.Item;
import static items.Item.lottoItem;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

public class Inventory {
    
    private Handler handler;
    public boolean active = false;
    private ArrayList <Item> inventoryItems;
    
    private int selectedItem = 0;
    private int len = 0;
            
    private final int invH = 388, invW = 432,
            itemH = 50, itemW = 50,
            botSpacing = 600, //right spacing is equal to invW so we dont need another varaible for that
            wS = 51, hS = 52,        //wS = widthSpacing, hS = heightSpacing  - in the inventory, between the boxes
            invWS = 29, invHS = 27,  //invWS = inventoryWidthSpacing from left margin to first box, same for height
            descW = 170;  //the width of the left part of the inventory, description part
    
    
    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<>();
        
        Item i1 = lottoItem; //testing purposes
        i1.setCount(90);
        inventoryItems.add(i1);
    }

    public void tick() {
        checkItemRemoval();       //first check if there are items to be removed
        len = inventoryItems.size(); //initialise len after removal, because size might change
        
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {  //use E key to open/close the inventory
            active = !active;
        }
        if(!active) {               //check movement in the inventory only if active
            return;
        }
        
        checkKeyboardMovement();
        checkMouseMovement();
    }
    
    public void render(Graphics g) {
        if(!active) {               //render only if inventory is active
            return;
        }
        g.drawImage(Assets.inventoryImage, handler.getGame().getWidth() - invW, 
                handler.getGame().getHeight() - botSpacing, invW, invH, null);  //draw inventory image
        
        int len = inventoryItems.size();
        if (len == 0) {
            return;
        }
        
        drawRightPart(g);  //right part of the inventory
        drawLeftPart(g);   //left part of the inventory
        
    }
    
    
    
    //INVENTORY METHODS
    public void checkItemRemoval() {
        Iterator<Item> it = inventoryItems.iterator(); //the only safe way to moddify a collection while iterating through
                                                       //if is by using an iterator
        while(it.hasNext()) {
            Item i = it.next();
            if (i.getCount() == 0) {
                it.remove();
            }
        }
    }
    
    public void addItem(Item item) {
        for (Item i : inventoryItems) {                    
            if (i.getId() == item.getId()) {                //if the item is already in the inventory we just increase its count number
                if (i.getCount() == 100) {
                    continue;
                }
                if (i.getCount() + item.getCount() > 100) {
                    Item aux = item;
                    aux.setCount(item.getCount() - 100 + i.getCount());
                    i.setCount(100);
                    inventoryItems.add(aux);
                    return;
                } else {
                    i.setCount(i.getCount() + item.getCount());
                    return;
                }
            }
        }
        inventoryItems.add(item); //if the items is not in inventory, we add it;
    }
    
    private void checkKeyboardMovement() {  //checks if the player is moving around in the inventory
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) {
            if (selectedItem < 4) {
                return;
            }
            selectedItem = selectedItem - 4;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) {
            if (selectedItem > 19 || selectedItem + 4 >= inventoryItems.size()) {
                return;
            }
            selectedItem = selectedItem + 4;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT)) {
            if (selectedItem == 23 || selectedItem == inventoryItems.size() - 1) {
                return;
            }
            selectedItem = selectedItem + 1;
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT)) {
            if (selectedItem == 0) {
                return;
            }
            selectedItem = selectedItem - 1;
        }
    }
    
    
    private void checkMouseMovement() {
        if (handler.getMouseManager().isLeftPressed()) {
            int x = handler.getMouseManager().getMouseX();
            int y = handler.getMouseManager().getMouseY();
            int x1 = (int)getItemImageX(0);
            int x2 = (int)getItemImageX(3);
            int y1 = (int)getItemImageY(0);
            int y2 = (int)getItemImageY(23); 
            
            if ((x >= x1) && (x <= x2 + itemW) && (y >= y1) && (y <= y2 + itemH)) {
                if ((int)((x - x1) / 52) + 4 * ((int)((y - y1) / 53)) < inventoryItems.size()) {
                    selectedItem = (int)((x - x1) / 52) + 4 * ((int)((y - y1) / 53));
            }
                
            }
        }
    }
    
    private void drawRightPart(Graphics g) {
        for (int i = 0; i < len; i++) {//draw the items in the inventory
            Item item = inventoryItems.get(i);
            if (selectedItem == i) {  //we color a different background for the selected item
                g.setColor(Color.WHITE);
                g.fillRect((int) getItemImageX(i), (int) getItemImageY(i), itemW, itemH);
                g.drawImage(item.getTexture(),(int) getItemImageX(i), (int) getItemImageY(i), itemW, itemH, null);
            } else {
                g.drawImage(item.getTexture(),(int) getItemImageX(i), (int) getItemImageY(i), itemW, itemH, null);
            }
        }
    }
    
    private void drawLeftPart(Graphics g) {
        g.drawImage(inventoryItems.get(selectedItem).getTexture(), handler.getGame().getWidth() - invW + 55, //draw the image of the item
                    handler.getGame().getHeight() - botSpacing + 55, 62, 62, null);
        g.fillRect(handler.getGame().getWidth() - invW + 71, handler.getGame().getHeight() - botSpacing + 126, 30, 14);  //draw the count with whte bg
        Text.drawString(g, Integer.toString(inventoryItems.get(selectedItem).getCount()), handler.getGame().getWidth() - invW + 86, 
                    handler.getGame().getHeight() - botSpacing + 133, true, Color.BLACK, Assets.font16);
        if (inventoryItems.get(selectedItem).getName().equals("Dragon Ball")) {
            Text.drawString(g, inventoryItems.get(selectedItem).getName(), handler.getGame().getWidth() - invW + 84,  //draw the name of the item
                    handler.getGame().getHeight() - botSpacing + 180, true, Color.ORANGE, Assets.font16);
        }else if (inventoryItems.get(selectedItem).getName().equals("Lottery Ticket")) {
            Text.drawString(g, inventoryItems.get(selectedItem).getName(), handler.getGame().getWidth() - invW + 84,  //draw the name of the item
                    handler.getGame().getHeight() - botSpacing + 180, true, Color.RED, Assets.font16);
        }else {
            Text.drawString(g, inventoryItems.get(selectedItem).getName(), handler.getGame().getWidth() - invW + 84,
                    handler.getGame().getHeight() - botSpacing + 180, true, Color.GREEN, Assets.font16);
        }
        
        Text.drawString(g, inventoryItems.get(selectedItem).getDescription(), handler.getGame().getWidth() - invW + 20,  //draw the description if the item
                    handler.getGame().getHeight() - botSpacing + 210, false, Color.yellow, Assets.font11);
    }
    
    
    
    
    private float getItemImageX(int i) {   //returns the x coordinate of the item image in inventory
        return (handler.getGame().getWidth() - invW + descW + invWS + (i % 4) * wS);
    }
    
    private float getItemImageY(int i) {   //returns the y coordinate of the item image in inventory
        return (handler.getGame().getHeight() - botSpacing + invHS + (i / 4) * hS);
    }
    
    
    //GETTERS AND SETTERS
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    
    public ArrayList <Item> getInventoryItems() {
        return inventoryItems;
    }
    
}
