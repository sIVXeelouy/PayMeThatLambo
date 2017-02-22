package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener{

    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    
    public MouseManager() {
        
    }
    
    //Getters
    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
    
    //Implemented methods
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { //button1 is leftClick
            leftPressed = true;
        } else if (e.getButton() == MouseEvent.BUTTON3) { //button3 is rightClick
            rightPressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { //button1 is leftClick
            leftPressed = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) { //button3 is rightClick
            rightPressed = false;
        }
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
     
    }

    @Override
    public void mouseExited(MouseEvent e) {
    
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    
    }
    
}
