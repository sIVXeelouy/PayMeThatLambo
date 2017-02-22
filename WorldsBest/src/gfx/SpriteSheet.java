package gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet { //this class is used to crop/cut sheet.png in pieces and get all the images
    
    private final BufferedImage sheet;
    
    public SpriteSheet (BufferedImage sheet) {
        this.sheet = sheet;
    }
    
    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
}
