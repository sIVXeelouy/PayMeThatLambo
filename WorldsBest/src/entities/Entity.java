package entities;

import java.awt.Graphics;
import $$$.Handler;
import java.awt.Rectangle;

public abstract class Entity { //entities are the player, monsters, items, ..

    public static final int DEFAULT_HEALTH = 10; //every entity has a health
    protected float x, y; //entity positions
    protected int width, height; //entity sizes
    protected Handler handler;
    protected Rectangle bounds; //the collision bounds
    protected int health;
    protected boolean active = true; //active means the entity is alive,present in the game
    
    public Entity(Handler handler,float x, float y, int width, int height) {
        health = DEFAULT_HEALTH;
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void die();
    
    public void hurt(int amount) { //method for combat
        health -= amount;
        if (health <= 0) {  //if no more health, remove the entity from the game
            active = false;
            die();       
        }
    }
    
    //we will be using this method only in the creature class, because there's no reason to check this in the staticEntity
    //class since that class doesn't "move"
    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) { //dont check collisions of a entity with itself
                continue;
            }
            if (e.getCollisionBounds(0.f, 0.f).intersects(getCollisionBounds(xOffset, yOffset))) {
                return true;
            }
        }
        return false;
    }
    
    public Rectangle getCollisionBounds(float xOffset, float yOffset) { //this covers the solid area of the entity
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    //GETTERS AND SETTERS
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public float getX() {   //alt + insert , to insert getter,setter and other methods
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
