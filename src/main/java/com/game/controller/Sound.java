package com.game.controller;

import javax.sound.sampled.*;
import java.net.URL;

//Singleton
public class Sound {

    private static Sound single_instance = null;
    private static boolean isPlaying;
    private static Clip clip;
    private static Float currentVolume;

    public Sound() {
        isPlaying = false;
    }

    public static void setCurrentVolume(Float currentVolume) {
        Sound.currentVolume = currentVolume;
    }

    public static void setIsPlaying(boolean isPlaying) {
        Sound.isPlaying = isPlaying;
    }

    public static Sound getInstance() {
        if (single_instance == null)
            single_instance = new Sound();

        return single_instance;
    }

    public static void playSound() {
        URL url = Sound.class.getResource("/sound.wav");
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url)) {
            getInstance().setIsPlaying(true);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            if (currentVolume != null){
                getInstance().setPreviousVolume();
            }
            new Thread(() -> {
                clip.start();
                while (clip.isRunning()) {
                    // Wait for the sound to finish
                }
            }).start();
            Thread.sleep(193000);
            clip.stop();
        } catch (Exception e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }

    public void muteSound() {
        if (isPlaying) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-80f);
            getInstance().setCurrentVolume(-80f);
        } else {
            System.out.println("Nothing to mute");
        }
    }
    //6.026 is that maximum for my system
    public void setVolume(int volume) {
        if (isPlaying) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (volume < 1 || volume > 4) {
                throw new IllegalArgumentException("Volume level must be between 1 and 4");
            }
            float minimumGain = gainControl.getMinimum();
            float maximumGain = gainControl.getMaximum();
            float range = maximumGain - minimumGain;

            int multi = volume * 5;
            float gain = minimumGain + (range / volume) * (volume - 1) + multi;

            gainControl.setValue(gain);
            getInstance().setCurrentVolume(gain);
        } else {
            System.out.println("Nothing to change");
        }
    }

    public void setPreviousVolume(){
        if (isPlaying) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(Sound.currentVolume);
            getInstance().setCurrentVolume(Sound.currentVolume);
        } else {
            System.out.println("Nothing to change");
        }
    }
}
