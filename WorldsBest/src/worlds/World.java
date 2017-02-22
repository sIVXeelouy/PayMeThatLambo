package worlds;

import $$$.Handler;
import entities.EntityManager;
import items.ItemManager;
import java.awt.Graphics;
import tiles.Tile;
import utils.Util;

public abstract class World {

    protected Handler handler;

    protected int width, height; //number of tiles on horizontal, vertical
    protected int spawnX, spawnY; //player spawn coordinates
    protected int[][] tilesMatrix; //tiles matrix indexing tiles'id

    
    
    //Entities
    protected EntityManager entityManager;
    //Items
    protected ItemManager itemManager;

    public World(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler);
        itemManager = new ItemManager(handler);

        loadWorld(path);

        entityManager.getPlayer().setX(spawnX * Tile.TILE_WIDTH); //setting the coordinates received from the txt file
        entityManager.getPlayer().setY(spawnY * Tile.TILE_HEIGHT);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    private void loadWorld(String path) {  //loads the tiles numbers in our tilesMatrix
        String file = Util.loadFileAsString(path);
        String[] tokens = file.split("\\s+"); //split the string when find space,tab,enter,...
        width = Util.parseInt(tokens[0]);
        height = Util.parseInt(tokens[1]);
        spawnX = Util.parseInt(tokens[2]);
        spawnY = Util.parseInt(tokens[3]);

        tilesMatrix = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tilesMatrix[x][y] = Util.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public Tile getTile(int x, int y) {   //returns the tile from a specified position in the tilesMatrix
        if (x < 0 || y < 0 || x >= width || y >= height) { //in case of an error;
            return Tile.grass1Tile;
        }

        Tile t = Tile.tiles[tilesMatrix[x][y]];
        if (t == null) {            // in case of an error; this happens if a number in the txt file is greater than
            return Tile.grass1Tile;  // the Tile.tiles.length();
        }
        return t;
    }

    //GETTERS AND SETTERS
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

}
