package com.bigbreakfast.paulbearer.sound;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JOptionPane;

class BigClipExample {

    public static void main(String[] args) throws Exception {
    	
        BigClip clip = new BigClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("/Users/pj_mcmanus10/Video Game Code/Platformer/sound/easy now [16-bit].wav"));
        clip.open(ais);
        clip.start();
        //clip.loop(4);
    }
}