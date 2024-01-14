package ma.enset.jeu2d_stage1;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[]=new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/ma/enset/sound/BlueBoyAdventure2.wav");
        soundURL[1] = getClass().getResource("/ma/enset/sound/coin.wav");
        soundURL[2] = getClass().getResource("/ma/enset/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/ma/enset/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/ma/enset/sound/fanfare.wav");

    }

    public  void setFile(int i){
        try {
            AudioInputStream ais= AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public  void play(){
        clip.start();
    }
    public  void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public  void stop(){
        clip.stop();
    }


}

