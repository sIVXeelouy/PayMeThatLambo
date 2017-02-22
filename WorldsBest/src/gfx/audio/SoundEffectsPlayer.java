package gfx.audio;

import java.util.ArrayList;

public class SoundEffectsPlayer implements Runnable{ //just like MusicPlayer, but will play each audio file just once
    
    private ArrayList<AudioFile> musicFiles;
    private int currentSongIndex;
    private boolean running = false;
    private Thread thread;
    
    public SoundEffectsPlayer(String... files) {
        musicFiles = new ArrayList<>();
        for (String file : files) {
            musicFiles.add(new AudioFile("res/audio/soundEffects/" + file + ".wav"));
        }
        
    }
    
    @Override
    public void run() {
        AudioFile song = musicFiles.get(currentSongIndex);
        song.play();
    }
    
    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }
}
