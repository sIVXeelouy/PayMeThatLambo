package items;

import $$$.Handler;
import gfx.Assets;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Item {  //this class is something like entity and tile
    
    //items description
    private final static String woodS = "Wood is used for\nGRATAREEE!";
    private final static String appleS = "Apple is one of the\nbest fruit and you\ncan eat it!";
    private final static String pearS = "Pear is a fruit just\nlike apple and you\ncan eat this too!";
    private final static String coneS = "To be honest, cone\nis useless!";
    private final static String stoneS = "You can use stone\nto attack others\nor to attack\nyourself!";
    private final static String dbS = "This item is one of\nthe rarest items in\nthis game. You can\nuse it for a lot of\nthings or "
            + "you can\nsell it for a good\nprice!";
    public final static String lottoS = "You can use this to\ntry your luck in\nthe lottery or you\ncan sell it!";
    
    
    //HANDLER
    
    public static Item[] items = new Item[256];
    public static Item woodItem = new Item(Assets.treeTrunk, "Wood", 0, woodS); //just like in tile class
    public static Item pearItem = new Item(Assets.pear, "Pear", 1, pearS);
    public static Item appleItem = new Item(Assets.apple, "Apple", 2, appleS);
    public static Item coneItem = new Item(Assets.cone, "Cone", 3, coneS);
    public static Item stoneItem = new Item(Assets.stonie, "Stone", 4, stoneS);
    public static Item dbItem = new Item(Assets.db, "Dragon Ball", 5, dbS);
    public static Item lottoItem = new Item(Assets.lotto, "Lottery Ticket", 6, lottoS);
    
    
    //CLASS
    public static final int ITEM_WIDTH = 32, ITEM_HEIGHT = 32;
    
    protected Handler handler;
    protected String name;          //name of the item
    protected BufferedImage texture;//image of the item
    protected final int id;         //id of the item
    protected final String description; //description of the item
    
    protected Rectangle bounds;   //use this for picking the items
    
    protected int x, y, count;  //count will be used for the player inventory
    protected boolean pickedUp;
    
    public Item (BufferedImage texture, String name, int id, String description) {
        this.texture = texture;
        this.name = name;
        this.id = id;
        this.description = description;
        count = 1;
        
        bounds = new Rectangle(x, y, ITEM_WIDTH, ITEM_HEIGHT);
        
        items[id] = this;
    }
    
    public void tick() {  //if the player runs over an item, he will pick it
        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0.f, 0.f).intersects(bounds)) {
            pickedUp = true;  //the items disappears and is added to the player inventory
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
        }
    }
    
    public void render(Graphics g) {  //we will use this method to render the item in the world on screen
        if (handler == null) {  //if we havent set the handler yet, theres no reason for this method to run
            return;
        }
        render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
    }
    
    
    public void render(Graphics g, int x, int y) { //we will use this method to render the item in the inventory on the screen
        g.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
    }
    
    public Item createNew(int x, int y) {  //will create a copy of this item
        Item i = new Item(texture, name, id, description);
        i.setPosition(x, y);
        return i;
    }
    
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        bounds.x = x;     //update the item bounds coordinates
        bounds.y = y;
    }
    
    
    
    //GETTERS AND SETTERS
    
    
    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
    
    public boolean isPickedUp() {
        return pickedUp;
    }

    public int getId() {
        return id;
    }
    
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
}
