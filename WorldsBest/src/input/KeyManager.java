package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;
    public boolean up, down, right, left; //movements keys
    public boolean attack; //attack key
    
    
    
    public KeyManager() {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void tick() {
        for (int i = 0; i < keys.length; i++) {   //we do all of this because after we pressed a key we want to send
            if (cantPress[i] && !keys[i]) {        //a signal and right after, in the next tick() iteration (remember
                cantPress[i] = false;             //that every tick() and render(g) iterates 60 times a second),
            } else if (justPressed[i]) {           //we want that key to go back to its original state(not pressed);
                cantPress[i] = true;              //for ex.: if we press a key for longer to do something(like opening
                justPressed[i] = false;           //the inventory), that something will happen right when we start
            }                                     //pressing the key and only once, not 60times a second...
            if (!cantPress[i] && keys[i]) {
                justPressed[i] = true;
            }
        }

        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        right = keys[KeyEvent.VK_D];
        left = keys[KeyEvent.VK_A];

        attack = keys[KeyEvent.VK_P];
    }

    public boolean keyJustPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length) {
            return false;
        }
        return justPressed[keyCode];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) { //in case of an error
            return;
        }
        keys[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) { //in case of an error
            return;
        }
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
