package gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets { //this is the class that loads all the images, textures, sounds
    
    private static final int WIDTH = 32, HEIGHT = 32; //all images are 64x64 in sheet, including borders
    
    //Fonts
    public static Font font25;
    public static Font font16;
    public static Font font11;
    
    
    //Tiles images
    public static BufferedImage wall1, wall2, grass1, grass2, //static fields can be accessed like: ClassName.fieldName
            path1, path2, path3, path4, path5, path6, path7, path8, path9;
    //Entities images
    public static BufferedImage treeApple, treePear, treePin, stone;
    //Items images
    public static BufferedImage treeTrunk, apple, pear, cone, stonie, db, lotto;
    //Inventory image
    public static BufferedImage inventoryImage;
    //LotteryBox image
    public static BufferedImage lotteryBoxImage;
    public static BufferedImage lotteryMessage1;
    public static BufferedImage lotteryMessage2;
    public static BufferedImage lotteryMessage3;
    public static BufferedImage lotteryMessage4;
    
    
    //Player images
    public static BufferedImage [] playerUp;  //we have more images to create animations
    public static BufferedImage [] playerDown;
    public static BufferedImage [] playerLeft;
    public static BufferedImage [] playerRight;
    
    //Portal images
    public static BufferedImage [] portalImages; //more images to create the portal animation
    
    public static void init() {
        
        //fonts
        font25 = FontLoader.loadFont("res/fonts/slkscr.ttf", 25);
        font16 = FontLoader.loadFont("res/fonts/slkscr.ttf", 16);
        font11 = FontLoader.loadFont("res/fonts/slkscr.ttf", 11);
        
        //the spriteSheets for all the images 
        SpriteSheet tilesSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tilesSheet.png"));
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/playerSheet.png"));
        SpriteSheet entitySheet = new SpriteSheet(ImageLoader.loadImage("/textures/entitySheet.png"));
        SpriteSheet itemsSheet = new SpriteSheet(ImageLoader.loadImage("/textures/itemsSheet.png"));
        SpriteSheet portalSheet = new SpriteSheet(ImageLoader.loadImage("/textures/portalSheet.png"));
        
        //player images
        playerUp = new BufferedImage [2];  //2 different images for each direction
        playerDown = new BufferedImage [2];
        playerLeft = new BufferedImage [2];
        playerRight = new BufferedImage [2];
        //portal images
        portalImages = new BufferedImage[4];
        
        playerLeft[0] = playerSheet.crop(0 * WIDTH, 0 * HEIGHT, WIDTH, 2 * HEIGHT); //left 1
        playerLeft[1] = playerSheet.crop(1 * WIDTH, 0 * HEIGHT, WIDTH, 2 * HEIGHT); //left 2
        playerRight[0] = playerSheet.crop(2 * WIDTH, 0 * HEIGHT, WIDTH, 2 * HEIGHT); //right 1
        playerRight[1] = playerSheet.crop(3 * WIDTH, 0 * HEIGHT, WIDTH, 2 * HEIGHT); //right 2
        playerUp[0] = playerSheet.crop(0 * WIDTH, 2 * HEIGHT, WIDTH, 2 * HEIGHT); //up 1
        playerUp[1] = playerSheet.crop(1 * WIDTH, 2 * HEIGHT, WIDTH, 2 * HEIGHT); //up 2
        playerDown[0] = playerSheet.crop(2 * WIDTH, 2 * HEIGHT, WIDTH, 2 * HEIGHT); //down 1
        playerDown[1] = playerSheet.crop(3 * WIDTH, 2 * HEIGHT, WIDTH, 2 * HEIGHT); //down 2
        
        //tiles images
        wall1 = tilesSheet.crop(0 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
        wall2 = tilesSheet.crop(1 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
        grass1 = tilesSheet.crop(1 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
        grass2 = tilesSheet.crop(0 * WIDTH, 2 * HEIGHT, WIDTH, HEIGHT);
        path1 = tilesSheet.crop(0 * WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);
        path2 = tilesSheet.crop(1 * WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);
        path3 = tilesSheet.crop(2 * WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);
        path4 = tilesSheet.crop(3 * WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);
        path5 = tilesSheet.crop(4 * WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);
        path6 = tilesSheet.crop(5 * WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);
        path7 = tilesSheet.crop(6 * WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);
        path8 = tilesSheet.crop(7 * WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);
        path9 = tilesSheet.crop(8 * WIDTH, 1 * HEIGHT, WIDTH, HEIGHT);
        
        //entities images
        treeApple = entitySheet.crop(0 * WIDTH, 0 * HEIGHT, 2 * WIDTH, 2 * HEIGHT);
        treePear = entitySheet.crop(0 * WIDTH, 2 * HEIGHT, 2 * WIDTH, 2 * HEIGHT);
        treePin = entitySheet.crop(2 * WIDTH, 0 * HEIGHT, 2 * WIDTH, 4 * HEIGHT);
        stone = entitySheet.crop(4 * WIDTH, 0 * HEIGHT, 2 * WIDTH, 2 * HEIGHT);
        
        //items images
        treeTrunk = itemsSheet.crop(0 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
        pear = itemsSheet.crop(1 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
        apple = itemsSheet.crop(2 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
        cone = itemsSheet.crop(3 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
        stonie = itemsSheet.crop(4 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
        db = itemsSheet.crop(5 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
        lotto = itemsSheet.crop(6 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
        
        //inventory image
        inventoryImage = ImageLoader.loadImage("/textures/inventorySheet.png");
        
        //lotteryBox image
        lotteryBoxImage = entitySheet.crop(0 * WIDTH, 4 * HEIGHT, 4 * WIDTH, 4 * HEIGHT);
        lotteryMessage1 = ImageLoader.loadImage("/textures/lotteryMessage1.png");
        lotteryMessage2 = ImageLoader.loadImage("/textures/lotteryMessage2.png");
        lotteryMessage3 = ImageLoader.loadImage("/textures/lotteryMessage3.png");
        lotteryMessage4 = ImageLoader.loadImage("/textures/lotteryMessage4.png");
        
        
        
        //portal images
        portalImages[0] = portalSheet.crop(2 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
        portalImages[1] = portalSheet.crop(1 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
        portalImages[2] = portalSheet.crop(0 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
        portalImages[3] = portalSheet.crop(1 * WIDTH, 0 * HEIGHT, WIDTH, HEIGHT);
    }
}
