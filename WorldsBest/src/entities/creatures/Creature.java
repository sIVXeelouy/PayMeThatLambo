package entities.creatures;

import $$$.Handler;
import entities.Entity;
import java.awt.Graphics;
import tiles.Tile;

public abstract class Creature extends Entity {

    public static float DEFAULT_SPEED = 5.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 63;
    public static final int DEFAULT_CREATURE_HEIGHT = 63;
    protected float speed;
    protected float xMove;
    protected float yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        if (!checkEntityCollisions(xMove, 0.f)) { //we check where we want to move, before actually moving
            moveX();
        }
        if (!checkEntityCollisions(0.f, yMove)) {
            moveY();
        }
    }

    public void moveX() {
        if (xMove > 0) { //Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && //colt dreapta sus
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {//colt dreapta jos
                x += xMove;
            }else {
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0) { //Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;

            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && //colt stanga sus
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {//colt stanga jos
                x += xMove;
            }else {
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    public void moveY() {
        if (yMove < 0) { //Moving up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
            
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_HEIGHT , ty) && //colt stanga sus
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_HEIGHT, ty)) {//colt dreapta sus
                y += yMove;
            }else {
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        } else if (yMove > 0) { //Moving down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILE_HEIGHT , ty) && //colt stanga jos
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_HEIGHT, ty)) {//colt dreapta jos
                y += yMove;
            }else {
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
            }
        }

    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    //GETTERS AND SETTERS
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        
    }

    @Override
    public void die() {
        
    }

}
