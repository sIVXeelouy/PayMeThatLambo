package entities.creatures;

import gfx.Assets;
import $$$.Handler;
import entities.Entity;
import gfx.Animation;
import gfx.audio.SoundEffectsPlayer;
import inventory.Inventory;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //Animations
    private Animation animDown, animUp, animLeft, animRight;
    //Attack timer
    private long lastAttackTimer, attackCooldown = 200, attackTimer = attackCooldown;
    //Inventory
    private Inventory inventory;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, 2 * Creature.DEFAULT_CREATURE_HEIGHT);

        //customize these bounds coordinates for different creatures
        bounds.x = 6;   //x coordinate on player
        bounds.y = 75;  //y coordinate on player
        bounds.width = 49; //width part of the player
        bounds.height = 47; //height part of the player

        //Animations
        animDown = new Animation(500, Assets.playerDown);
        animUp = new Animation(500, Assets.playerUp);
        animLeft = new Animation(500, Assets.playerLeft);
        animRight = new Animation(500, Assets.playerRight);
        
        inventory = new Inventory(handler);
    }

    @Override
    public void tick() {
        //Animations
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        //Movement
        getInput(); //check if any keys were pressed
        move();     //move accordingly
        handler.getGameCamera().centerOnEntity(this); //center the camera on the player
        //Attack
        checkAttacks();
        //Inventory
        inventory.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        
        
        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), 
        //        (int) (y + bounds.y - handler.getGameCamera().getyOffset()), 
        //        bounds.width, bounds.height);
        
    }
    
    public void postRender(Graphics g) {  //we render the inventory over everything else
        inventory.render(g);
    }
    
    private SoundEffectsPlayer sep;
    
    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {  //can't attack(do the following code) unless cooldown has passed
            return;
        }

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();  //the attack box
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;
        
        if (handler.getKeyManager().attack) {
            sep = new SoundEffectsPlayer("axe");
            sep.start();
            if (up > 0) {//attack up side
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y - arSize / 2;
            } else if (left > 0) {//attack left side
                ar.x = cb.x - arSize / 2;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
            } else if (right > 0) {//attack right side
                ar.x = cb.x + cb.width;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
            } else {//attack down side
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y + cb.height;
            }  
            
        } else {
            return;
        }

        attackTimer = 0;    //attack will be send in the next loop so now we can start counting time again

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0, 0).intersects(ar)) {
                e.hurt(5);
                return;
            }
        }
    }

    @Override
    public void die() {
        System.out.println("YOU LOST NIGGA!");
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (handler.getKeyManager().up) {
            yMove = -speed;
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
        }
    }
    
    public int up = 0, down = 0, left = 0, right = 0;  //these will help us make the player remain in the left 
                                                        //position after we walked left, same for the others...
    
    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            left = 1;
            up = 0;
            down = 0;
            right = 0;
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            left = 0;
            up = 0;
            down = 0;
            right = 1;
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            left = 0;
            up = 1;
            down = 0;
            right = 0;
            return animUp.getCurrentFrame();
        } else if (yMove > 0){
            left = 0;
            up = 0;
            down = 1;
            right = 0;
            return animDown.getCurrentFrame();
        } else {
            if (left > 0) {
                return animLeft.getCurrentFrame();
            } else if (right > 0) {
                return animRight.getCurrentFrame();
            } else if (up > 0) {
                return animUp.getCurrentFrame();
            } else {
                return animDown.getCurrentFrame();
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    public Handler getHandler() {
        return this.handler;
    }
}
