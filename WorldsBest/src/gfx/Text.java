package gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Text {         //this class will be used to draw the font on the screen
    
    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font) {
        g.setColor(c);
        g.setFont(font);
        int x = xPos;
        int y = yPos;
        if (center) {  //if we want to center the next, we do some more code
            FontMetrics fm = g.getFontMetrics(font);
            x = xPos - fm.stringWidth(text) / 2;
            y = yPos - fm.getHeight() / 2 + fm.getAscent();
        }
        String[] line = text.split("\n");
        for (int i = 0; i < line.length; i++) {  //write parts of the text because it is too long for a line
            g.drawString(line[i], x, y + i * g.getFontMetrics().getHeight());
        }
    }
}
