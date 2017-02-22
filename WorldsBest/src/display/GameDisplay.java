package display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class GameDisplay {
    
    private JFrame frame;
    private Canvas canvas;
    private final String title;
    private final int width;
    private final int height;
    
    public GameDisplay(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        
        createDisplay();
    }
    
    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
        
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }
    
    public Canvas getCanvas() {
        return canvas;
    }
    
    public JFrame getJFrame() {
        return frame;
    }
}
