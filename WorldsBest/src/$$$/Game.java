package $$$;

import display.GameDisplay;
import entities.creatures.Player;
import gfx.Assets;
import gfx.GameCamera;
import input.KeyManager;
import input.MouseManager;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import states.GameStateWorld1;
import states.GameStateWorld2;
import states.State;
import tiles.Tile;

public class Game implements Runnable {
    
    private GameDisplay display;
    private final int width;
    private final int height;
    private final String title;
    
    private boolean running = false; // the variable used in the game loop in the run method
    private Thread thread;           // of this thread
    
    private BufferStrategy bs;
    private Graphics g;

    //States
    private State gameStateWorld1;
    private State gameStateWorld2;
    
    //Input
    private final KeyManager km;
    private final MouseManager mm;
    
    //Camera
    private GameCamera gameCamera1;
    private GameCamera gameCamera2;
    
    //Handler
    private Handler handler;
    
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        km = new KeyManager();
        mm = new MouseManager();
    }

    private void init() {
        display = new GameDisplay(title, width, height);
        display.getJFrame().addKeyListener(km);
        display.getJFrame().addMouseListener(mm);      //add mm on both frame and canvas to be sure it works well
        display.getJFrame().addMouseMotionListener(mm);//no mather on which one the game is focused on
        display.getCanvas().addMouseListener(mm);
        display.getCanvas().addMouseMotionListener(mm); 
        
        Assets.init();   //initialiaze all the assets, ready to be used
        
        //we have one handler, one game camera and one game state world for each new level/world
        handler = new Handler(this);
        
        gameCamera1 = new GameCamera(handler, 0, 0); //when we initialize the gameCamera we see the original screen so use 0,0
        gameCamera2 = new GameCamera(handler, 0, 0);
        
        handler.setPlayer(new Player(handler, 100, 100)); //set the player
        
        gameStateWorld1 = new GameStateWorld1(handler); //we create the first world where the game starts
        State.setState(gameStateWorld1);  // set the state to the world (with its entities...)
        
    }
    
    private int world2 = 0;
    
    private void tick() { //first method of the game loop that checks the position of player and other things...
        km.tick();
        if (State.getState() != null) { //if the gameState is good to use, then tick()
            State.getState().tick();
        }
        if (State.getState() instanceof GameStateWorld1) { //if player sits on the portal of the first world he goes to 2nd world
            if (handler.getWorld().getEntityManager().getPlayer().getX() >= 49 * Tile.TILE_WIDTH &&
                    handler.getWorld().getEntityManager().getPlayer().getX() <= 50 * Tile.TILE_WIDTH &&
                    handler.getWorld().getEntityManager().getPlayer().getY() >= 22 * Tile.TILE_HEIGHT &&
                    handler.getWorld().getEntityManager().getPlayer().getY() <= 23 * Tile.TILE_HEIGHT) {
                if (world2 == 0) { //first time we go on 2nd map so we create it
                    gameStateWorld2 = new GameStateWorld2(handler);
                    world2++;
                }
                State.setState(gameStateWorld2);   
                handler.setWorld(gameStateWorld2.getWorld()); //we change both the map and its entities
                //player appears on the new map at the 'start' spot
                handler.getWorld().getEntityManager().getPlayer().setX(handler.getWorld().getSpawnX() * Tile.TILE_WIDTH);
                handler.getWorld().getEntityManager().getPlayer().setY(handler.getWorld().getSpawnY() * Tile.TILE_HEIGHT);
            }
        }
        if (State.getState() instanceof GameStateWorld2) {
            if (handler.getWorld().getEntityManager().getPlayer().getX() >= 9 * Tile.TILE_WIDTH &&
                    handler.getWorld().getEntityManager().getPlayer().getX() <= 10 * Tile.TILE_WIDTH &&
                    handler.getWorld().getEntityManager().getPlayer().getY() >= 12 * Tile.TILE_HEIGHT &&
                    handler.getWorld().getEntityManager().getPlayer().getY() <= 13 * Tile.TILE_HEIGHT) {
                State.setState(gameStateWorld1);   //portal had 7 and 5 but player has portal * 2 height
                handler.setWorld(gameStateWorld1.getWorld());
                //player appears on the new map at the 'start' spot
                handler.getWorld().getEntityManager().getPlayer().setX(handler.getWorld().getSpawnX() * Tile.TILE_WIDTH);
                handler.getWorld().getEntityManager().getPlayer().setY(handler.getWorld().getSpawnY() * Tile.TILE_HEIGHT);
            }
        }
    }
    
    private void render() { // second method of the game loop that redraws the player, things...
        bs = display.getCanvas().getBufferStrategy();   //we get the buffer strategy from the jframe and we need it
        if (bs == null) {                               //because we first draw all the image on a buffer and then 
            display.getCanvas().createBufferStrategy(3);//that buffer is sent to our screen, all image at once to
            return;                                     //prevent flickering...
        }
        g = bs.getDrawGraphics();  //this is how you actually create the paint brush that will paint the images...
        //before starting drawing we have to clear the whole screen
        g.clearRect(0, 0, width, height);
        //start drawing:
        if (State.getState() != null) { //if the gameState that we created above is good to use, then render(g)
            State.getState().render(g); //drawings takes place in GameStateWorld1
        }
        
        //stop drawing!
        bs.show();   //Makes the BufferStrategy visible
        g.dispose(); //Removes the graphics after added (So it doesn't duplicate)
    }
    
    @Override
    public void run() {
        
        init();
        
        int fps = 60; //how many times we want tick() and render() to run every single second
        double timePerTick = 1000000000 / fps; // 1000000000ns = 1s
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); //current time of the computer in ns
        long timer = 0;
        int ticks = 0;
        
        while(running) { //the game loop which ticks and renders the screen while the game is running
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if(delta >= 1) { //delta = 1 means it has passed 1/60 of a second since last tick() and render()
                tick();      //so we should tick() and render() again because we want 60fps
                render();
                ticks++;
                delta--;     
            }
            /*
            if (timer >= 1000000000) { //this block shows how many times tick() and render() have been called during the last second
                System.out.println("number of ticks per second = " + ticks);
                ticks = 0;
                timer = 0;
            }*/
        }
        
        stop();
       
    }
    
    public KeyManager getKeyManager() {
        return km;
    }
    
    public MouseManager getMouseManager() {
        return mm;
    }
    
    public GameCamera getGameCamera() {
        if (State.getState() instanceof GameStateWorld1) {
            return gameCamera1;
        } else {
            return gameCamera2;
        }
        
    }
    
    public synchronized void start() {
        if (running) {  //if the game is already running we dont want to create a new thread
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop() {
        if (!running) {  //if the game is already stopped there's no thread to stop(join)
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
