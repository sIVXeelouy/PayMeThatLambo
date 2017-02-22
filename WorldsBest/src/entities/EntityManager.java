package entities;

import $$$.Handler;
import entities.creatures.Player;
import entities.statics.LotteryBox;
import gfx.Assets;
import gfx.Text;
import items.Item;
import static items.Item.dbItem;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    
    private Comparator<Entity> renderSorter = new Comparator<Entity>() {
        @Override
        public int compare(Entity e1, Entity e2) { 
            if (e1.getY() + e1.getHeight() < e2.getY() + e2.getHeight()){
                return -1;               //we will sort the entity array so that we render first the entities
            }                            //higher on the screen; for that we compare their bottom y coordinate                                    
            return 1;                    //if y is lower in value, then it is higher on the screen, then it gets 
        }                                //rendered first
    };
    
    private boolean lotteryPlaying = false;
    private boolean lotteryDoneGood = false;  //we trade the wood
    private boolean lotteryDoneBad = false;   //we dont trade the wood
    private boolean lotteryNotDone = false;
    private Entity aux = null;
    private long t= 0;
    private long end = 0;
            
    public EntityManager(Handler handler) {
        this.handler = handler;
        this.player = handler.getPlayer();
        entities = new ArrayList<>();
        entities.add(player);
    }

    public void tick() {
        checkEntityRemoval(); //first we check if there are 'dead' entities who need to be removed
        entities.sort(renderSorter);   //after checking the entities, we sort them so they get rendered correctly(from top to bottom)
        
        checkLottery();  //check if any player is playing the lottery
    }
    
    public void render(Graphics g) {
        renderEntities(g);      //render all the entities first
        player.postRender(g);   //render the inventory over everything else
        
        checkLotteryMessage(g); //check if there is any lotteryMessage to be shown
    }
    
    
    //ENTITY MANAGER METHODS
    
    private void checkEntityRemoval() {
        Iterator<Entity> it = entities.iterator();
        
        while(it.hasNext()) {
            Entity e = it.next();
            e.tick();
            if (!e.isActive()) {
                it.remove();
            }
        }
    }
    
    public void addEntity(Entity e) {
        entities.add(e); 
    }
    
    private void lotto() {
        int counter = 0;
        for (Item i1 : this.player.getInventory().getInventoryItems()) {
            if (i1.getId() == 5) { //DRAGONBALL
                i1.setCount(i1.getCount() + 1);   //the player already has the item he won
                break;
            } else {
                counter++;
            }
        }   //if the player doesn't have the item he won yet, then we add it to his inventory
        if (counter == this.player.getInventory().getInventoryItems().size()) {
            this.player.getInventory().getInventoryItems().add(dbItem);
        }
    }
    
    private void checkLottery() {
        if (lotteryPlaying) {  //we see the image with the text inviting us to play
            if ((this.handler.getMouseManager().getMouseX() >= 300 + 100 && this.handler.getMouseManager().getMouseX() <= 300 + 140)
                     && (this.handler.getMouseManager().getMouseY() >= 200 + 130 && this.handler.getMouseManager().getMouseY() <= 300 + 152)) {
                if (this.handler.getMouseManager().isLeftPressed()) { //we pressed YES
                    for (Item i : this.player.getInventory().getInventoryItems()) {
                        if (i.getId() == 6) {    //check if there are any lottery tickets
                            lotto();
                            i.setCount(i.getCount() - 1);
                            lotteryDoneGood = true;  //show what the player won
                            lotteryPlaying = false;
                            t= System.currentTimeMillis(); //from now on, for 3 seconds we will show the winning message
                            end = t + 3000;
                            return;
                        }
                        if (i.getId() == 0 && i.getCount() >= 50) {  //check if there is enough wood
                            lotto();
                            i.setCount(i.getCount() - 50);
                            lotteryDoneGood = true;   //show what the  player won
                            lotteryPlaying = false;
                            t= System.currentTimeMillis(); //from now on, for 3 seconds we will show the winning message
                            end = t + 3000;
                            return;
                        }
                    }
                    lotteryPlaying = false;
                    lotteryDoneBad = true;
                    t= System.currentTimeMillis(); //from now on, for 3 seconds we will show the not even playing message
                    end = t + 3000;
                }
            } else if ((this.handler.getMouseManager().getMouseX() >= 300 + 160 && this.handler.getMouseManager().getMouseX() <= 300 + 200)
                     && (this.handler.getMouseManager().getMouseY() >= 200 + 130 && this.handler.getMouseManager().getMouseY() <= 300 + 152)) {
                if (this.handler.getMouseManager().isLeftPressed()) { //we pressed NO
                    lotteryPlaying = false;
                    lotteryNotDone = true;
                    t= System.currentTimeMillis(); //from now on, for 3 seconds we will show the not even playing message
                    end = t + 3000;
                    return;
                }
            }
        }
    }
    
    private void checkLotteryMessage(Graphics g) {
        if (aux != null && lotteryPlaying) {   //if a lottery box is accesed, we render its message;
            ((LotteryBox) aux).renderMessage1(g);
        }
        
        if (lotteryDoneGood) {         //here we create the lottery picks with their chance
            if (System.currentTimeMillis() < end) {
                ((LotteryBox) aux).renderMessage2(g);
                Item item = null;
                item = dbItem;
                if (item.getId() == 5) {
                    Text.drawString(g, "1", 300 + 110, 200 + 140, false, Color.ORANGE, Assets.font25);
                    g.drawImage(item.getTexture(), 300 + 140, 200 + 100, 62, 62, null);
                }
            } else {
                lotteryDoneGood = false;
                end = 0;
                t = 0;
            }
        }
        
        if (lotteryDoneBad) {
            if (System.currentTimeMillis() < end) {
                ((LotteryBox) aux).renderMessage3(g);
            } else {
                lotteryDoneBad = false;
                t = 0;
                end = 0;
            }
        }
        
        if (lotteryNotDone) {
            if (System.currentTimeMillis() < end) {
                ((LotteryBox) aux).renderMessage4(g);
            } else {
                lotteryNotDone = false;
                t = 0;
                end = 0;
            }
        }
    }
    
    private void renderEntities(Graphics g) {
        for (Entity e : entities) {
            if (e instanceof LotteryBox) {             //check if someone is trying to play the lottery
                if (((LotteryBox) e).getSendMessage()) {
                    aux = e;
                    lotteryPlaying = true;
                }
            }
            e.render(g);     //render all entities
        }
    }
    
    
    
    
    //GETTERS AND SETTERS
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
    
}
