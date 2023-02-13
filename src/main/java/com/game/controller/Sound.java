package com.game.controller;

import javax.sound.sampled.*;
import java.net.URL;

//Singleton
public class Sound {

    private static Sound single_instance = null;
    private static boolean musicIsPlaying;
    private static boolean soundIsPlaying;
    private static Clip musicClip;
    private static Clip soundClip;
    private static Float currentMusicVolume;
    private static Float currentSoundVolume;

    public Sound() {
        musicIsPlaying = false;
        soundIsPlaying = false;
    }

    public static void setCurrentMusicVolume(Float currentMusicVolume) {
        Sound.currentMusicVolume = currentMusicVolume;
    }

    public static void setCurrentSoundVolume(Float currentSoundVolume) {
        Sound.currentSoundVolume = currentSoundVolume;
    }

    public static void setMusicIsPlaying(boolean isPlaying) {
        Sound.musicIsPlaying = isPlaying;
    }
    public static void setSoundIsPlaying(boolean isPlaying) {
        Sound.soundIsPlaying = isPlaying;
    }

    public static Sound getInstance() {
        if (single_instance == null)
            single_instance = new Sound();

        return single_instance;
    }

    public static void playMusic() {
        URL url = Sound.class.getResource("/sound.wav");
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url)) {
            getInstance().setMusicIsPlaying(true);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioInputStream);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
            if (currentMusicVolume != null){
                getInstance().setPreviousMusicVolume();
            }
            new Thread(() -> {
                musicClip.start();
                while (musicClip.isRunning()) {
                    // Wait for the sound to finish
                }
            }).start();
            Thread.sleep(193000);
            musicClip.stop();
        } catch (Exception e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }

    public static void playSound(String sound) {
        URL url = Sound.class.getResource(sound);
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url)) {
            getInstance().setSoundIsPlaying(true);
            soundClip = AudioSystem.getClip();
            soundClip.open(audioInputStream);
            if (currentSoundVolume != null){
                getInstance().setPreviousSoundVolume();
            }
            new Thread(() -> {
                soundClip.start();
                while (soundClip.isRunning()) {
                    // Wait for the sound to finish
                }
            }).start();
            //Thread.sleep(2500);
            soundClip.stop();
        } catch (Exception e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }

    public void muteSound() {
        if (soundIsPlaying) {
            FloatControl gainControl = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-80f);
            getInstance().setCurrentSoundVolume(-80f);
        } else {
            System.out.println("Nothing to mute");
        }
    }

    public void muteMusic() {
        if (musicIsPlaying) {
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-80f);
            getInstance().setCurrentMusicVolume(-80f);
        } else {
            System.out.println("Nothing to mute");
        }
    }
    //6.026 is that maximum for my system
    public void setMusicVolume(int volume) {
        if (musicIsPlaying) {
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            if (volume < 1 || volume > 4) {
                throw new IllegalArgumentException("Volume level must be between 1 and 4");
            }
            float minimumGain = gainControl.getMinimum();
            float maximumGain = gainControl.getMaximum();
            float range = maximumGain - minimumGain;

            int multi = volume * 5;
            float gain = minimumGain + (range / volume) * (volume - 1) + multi;

            gainControl.setValue(gain);
            getInstance().setCurrentMusicVolume(gain);
        } else {
            System.out.println("Nothing to change");
        }
    }

    //6.026 is that maximum for my system
    public void setSoundVolume(int volume) {
        if (soundIsPlaying) {
            FloatControl gainControl = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
            if (volume < 1 || volume > 4) {
                throw new IllegalArgumentException("Volume level must be between 1 and 4");
            }
            float minimumGain = gainControl.getMinimum();
            float maximumGain = gainControl.getMaximum();
            float range = maximumGain - minimumGain;

            int multi = volume * 5;
            float gain = minimumGain + (range / volume) * (volume - 1) + multi;

            gainControl.setValue(gain);
            getInstance().setCurrentSoundVolume(gain);
        } else {
            System.out.println("Nothing to change");
        }
    }
    public void setPreviousMusicVolume(){
        if (musicIsPlaying) {
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(Sound.currentMusicVolume);
            getInstance().setCurrentMusicVolume(Sound.currentMusicVolume);
        } else {
            System.out.println("Nothing to change");
        }
    }

    public void setPreviousSoundVolume(){
        if (soundIsPlaying) {
            FloatControl gainControl = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(Sound.currentSoundVolume);
            getInstance().setCurrentSoundVolume(Sound.currentSoundVolume);
        } else {
            System.out.println("Nothing to change");
        }
    }
}
