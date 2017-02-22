package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    
    //STATIC STUFF HERE
    public static final Tile [] tiles = new Tile[256]; //vector of all types of tiles
    public static Tile wall1Tile = new WallTile1(0);
    public static Tile wall2Tile = new WallTile2(1);
    public static Tile grass1Tile = new GrassTile1(2);
    public static Tile grass2Tile = new GrassTile2(3);
    public static Tile path1Tile = new PathTile1(11);
    public static Tile path2Tile = new PathTile2(12);
    public static Tile path3Tile = new PathTile3(13);
    public static Tile path4Tile = new PathTile4(14);
    public static Tile path5Tile = new PathTile5(15);
    public static Tile path6Tile = new PathTile6(16);
    public static Tile path7Tile = new PathTile7(17);
    public static Tile path8Tile = new PathTile8(18);
    public static Tile path9Tile = new PathTile9(19);
    
    
    //CLASS BODY
    public static int TILE_WIDTH = 32;
    public static int TILE_HEIGHT = 32;
    protected BufferedImage texture;
    protected final int id; //one id for each different tile
    
    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }
    
    public void tick() {
        
    }
    
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }
    
    public boolean isSolid() {
        return false;
    }
    
    public int getId() {
        return id;
    }  
}
