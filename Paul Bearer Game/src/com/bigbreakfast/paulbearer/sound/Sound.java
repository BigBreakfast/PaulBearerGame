package com.bigbreakfast.paulbearer.sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFileChooser;

public class Sound {
	
	public static void playSound() throws Exception {
		BigClip clip = new BigClip();
        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("/Users/pj_mcmanus10/Video Game Code/Platformer/sound/easy now [16-bit].wav"));
        //AudioInputStream ais = AudioSystem.getAudioInputStream(new File("/Users/pj_mcmanus10/Video Game Code/Paul Bearer Game/Paul Bearer Game/sound/The Undertaker 1st WWF Theme - Funeral Dirge.mp3"));
        clip.open(ais);
        clip.loop(clip.LOOP_CONTINUOUSLY);
        //if (!clip.isActive()) clip.start();
        	//clip.loop(clip.LOOP_CONTINUOUSLY);
	}

	/*
	public static void playSound() {
		Clip clip;
		try {
			clip = AudioSystem.getClip();
			//ÊgetAudioInputStream()ÊalsoÊacceptsÊaÊFileÊorÊInputStream
			AudioInputStream ais;
			try {
				ais = AudioSystem.getAudioInputStream(new File("/Users/pj_mcmanus10/Video Game Code/Platformer/sound/easy now.wav"));
				try {
					clip.open(ais);
					clip.loop(Clip.LOOP_CONTINUOUSLY);
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/

	/*
	 * public static void playSound() throws Exception {
	 * 
	 * File soundFile; Clip clip; //JFileChooser chooser = new JFileChooser();
	 * //chooser.showOpenDialog(null); //soundFile = chooser.getSelectedFile();
	 * soundFile = new
	 * File("/Users/pj_mcmanus10/Video Game Code/Platformer/sound/easy now.mp3"
	 * );
	 * 
	 * //System.out.println("Playing " + soundFile.getName());
	 * 
	 * Line.Info linfo = new Line.Info(Clip.class); Line line =
	 * AudioSystem.getLine(linfo); clip = (Clip) line; AudioInputStream ais =
	 * AudioSystem.getAudioInputStream(soundFile); clip.open(ais); clip.start();
	 * }
	 */

	public static void playMusic() {

		/*
		 * 
		 * AudioPlayer bgPlayer = AudioPlayer.player; AudioStream bgMusic;
		 * AudioData musicData;
		 * 
		 * ContinuousAudioDataStream loop = null;
		 * 
		 * try {
		 * 
		 * bgMusic = new AudioStream(newFileInputStream(
		 * "/Users/pj_mcmanus10/Video Game Code/Platformer/sound/easy now.mp3"
		 * )); musicData = bgMusic.getData(); loop = new
		 * ContinuousAudioDataStream(musicData);
		 * 
		 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch
		 * (IOException e) { e.printStackTrace(); }
		 * 
		 * bgPlayer.start(loop); System.out.println("Music");
		 */

		/*
		 * try { clip = AudioSystem.getClip(); } catch (LineUnavailableException
		 * e2) { // TODO Auto-generated catch block e2.printStackTrace(); }
		 * 
		 * try { inputStream =
		 * AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream
		 * ("/Users/pj_mcmanus10/Video Game Code/Platformer/sound/easy now.wav"
		 * )); } catch (UnsupportedAudioFileException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); } catch (IOException
		 * e1) { // TODO Auto-generated catch block e1.printStackTrace(); }
		 * 
		 * try { clip.open(inputStream); clip.start(); } catch
		 * (LineUnavailableException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } catch (IOException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 */

	}

	/*
	 * 
	 * private String filename; Ê private Position curPosition; Ê private final
	 * int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb Ê enum Position { LEFT,
	 * RIGHT, NORMAL };
	 * 
	 * public AePlayWave(String wavfile) { filename = wavfile; curPosition =
	 * Position.NORMAL; } Ê public AePlayWave(String wavfile, Position p) {
	 * filename = wavfile; curPosition = p; } Ê public void run() { Ê File
	 * soundFile = new File(filename); if (!soundFile.exists()) {
	 * System.err.println("Wave file not found: " + filename); return; } Ê
	 * AudioInputStream audioInputStream = null; try { audioInputStream =
	 * AudioSystem.getAudioInputStream(soundFile); } catch
	 * (UnsupportedAudioFileException e1) { e1.printStackTrace(); return; }
	 * catch (IOException e1) { e1.printStackTrace(); return; } Ê AudioFormat
	 * format = audioInputStream.getFormat(); SourceDataLine auline = null;
	 * DataLine.Info info = new DataLine.Info(SourceDataLine.class, format); Ê
	 * try { auline = (SourceDataLine) AudioSystem.getLine(info);
	 * auline.open(format); } catch (LineUnavailableException e) {
	 * e.printStackTrace(); return; } catch (Exception e) { e.printStackTrace();
	 * return; } Ê if (auline.isControlSupported(FloatControl.Type.PAN)) {
	 * FloatControl pan = (FloatControl) auline
	 * .getControl(FloatControl.Type.PAN); if (curPosition == Position.RIGHT)
	 * pan.setValue(1.0f); else if (curPosition == Position.LEFT)
	 * pan.setValue(-1.0f); } Ê auline.start(); int nBytesRead = 0; byte[]
	 * abData = new byte[EXTERNAL_BUFFER_SIZE]; Ê try { while (nBytesRead != -1)
	 * { nBytesRead = audioInputStream.read(abData, 0, abData.length); if
	 * (nBytesRead >= 0) auline.write(abData, 0, nBytesRead); } } catch
	 * (IOException e) { e.printStackTrace(); return; } finally {
	 * auline.drain(); auline.close(); } Ê } }
	 */

}
