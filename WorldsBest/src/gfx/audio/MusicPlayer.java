package gfx.audio;

import java.util.ArrayList;

public class MusicPlayer implements Runnable {

    private ArrayList<AudioFile> musicFiles;
    private int currentSongIndex;
    private boolean running = false;
    private Thread thread;
    
    public MusicPlayer(String... files) {
        musicFiles = new ArrayList<>();
        for (String file : files) {
            musicFiles.add(new AudioFile("res/audio/music/" + file + ".wav"));
        }
    }
    
    @Override
    public void run() {
        AudioFile song = musicFiles.get(currentSongIndex);
        song.play();
        
        while(running) {
            if(!song.isPlaying()) {   //if the song ended we play the next song, and if we finished the songs, we go back to the first one
                currentSongIndex++;
                if (currentSongIndex >= musicFiles.size()) {
                    currentSongIndex = 0;
                }
                song = musicFiles.get(currentSongIndex);
                song.play();
            }

        }
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
