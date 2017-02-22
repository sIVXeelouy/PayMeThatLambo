package gfx.audio;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class AudioFile implements LineListener{  //we use Line Listener to know when a song starts/stops
    
    private File audioFile;
    private AudioInputStream ais;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip clip;
    private FloatControl gainControl;
    private volatile boolean playing;           //we make this field volatile because our threads communicate through it (AudioFile and MusicPlayer) 
    
    public AudioFile(String fileName) {
        try {
            audioFile = new File(fileName);
            ais = AudioSystem.getAudioInputStream(audioFile);
            format = ais.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip)AudioSystem.getLine(info);
            clip.addLineListener(this);                //here we add the Line Listener
            clip.open(ais);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public void play() {   //play the music with the default volume (-10)
        play(-10);
    }
    
    public void play(float volume) {  //play the music with choosen volume
        gainControl.setValue(volume);
        clip.start();
        playing = true;
    }

    public boolean isPlaying() {
        return playing;
    }
    
    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.START) {
            playing = true;
        }else if (event.getType() == LineEvent.Type.STOP) {
            clip.stop();
            clip.flush();
            clip.setFramePosition(0);
            playing = false;
        }
    }
}
