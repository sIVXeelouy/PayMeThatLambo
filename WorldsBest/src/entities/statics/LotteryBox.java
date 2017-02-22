package entities.statics;

import $$$.Handler;
import gfx.Assets;
import java.awt.Graphics;
import tiles.Tile;

public class LotteryBox extends StaticEntity{
    
    
    private int newHealth = 100000;
    private boolean sendMessage = false;
    
    public LotteryBox(Handler handler, float x, float y) {
        super(handler, x, y, 4 * Tile.TILE_WIDTH, 4 * Tile.TILE_HEIGHT);
        super.health = 100000; //never dies
        bounds.x = Tile.TILE_WIDTH / 2 ;
        bounds.y = 3 * Tile.TILE_WIDTH / 2;
        bounds.width = 3 * Tile.TILE_WIDTH;
        bounds.height = 2 * Tile.TILE_WIDTH;
    }

    @Override
    public void tick() {
        if (this.health < newHealth) {
            sendMessage = true;
            this.health = 100000;        //this shit doesn't fking die, ever
            newHealth = this.health;
        } else {
            sendMessage = false;
        }
     }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.lotteryBoxImage, (int) (x - handler.getGameCamera().getxOffset()), 
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        
    }

    @Override
    public void die() {
        //THIS SHIT WON'T FKING DIE
    }
    
    
    public void renderMessage1(Graphics g) {
        g.drawImage(Assets.lotteryMessage1, 300, 200, 300, 200, null);
        
    }
    
    public void renderMessage2(Graphics g) {
        g.drawImage(Assets.lotteryMessage2, 300, 200, 300, 200, null);
        
    }
    
    public void renderMessage3(Graphics g) {
        g.drawImage(Assets.lotteryMessage3, 300, 200, 300, 200, null);
        
    }
    
    public void renderMessage4(Graphics g) {
        g.drawImage(Assets.lotteryMessage4, 300, 200, 300, 200, null);
        
    }
    
    public boolean getSendMessage() {
        return sendMessage;
    }
    
}
