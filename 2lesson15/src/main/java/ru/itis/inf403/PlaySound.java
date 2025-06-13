package ru.itis.inf403;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class PlaySound {
    private static Clip clip;
    public static void play(Track track) {
        new Thread(() -> {try {
            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(new File(track.getPath()));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }}).start();
    }

    public static Clip getClip() {
        return clip;
    }
}
