package SynthSystem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import net.beadsproject.beads.core.*;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Noise;
import net.beadsproject.beads.core.io.JavaSoundAudioIO;

public class AudioIOSelector {
	AudioContext ac;
	public JavaSoundAudioIO aoi;
	int numberOfMixer;
	public AudioIOSelector() { 

	}

	public void selectMixer(int numberOfMixer) {
		this.numberOfMixer = numberOfMixer;
		aoi = new JavaSoundAudioIO();
		aoi.selectMixer(numberOfMixer);
		ac = new AudioContext(aoi);
	}
	private String[] removeAudioDescriptionFromOutputLog(String outputsWithDescriptions[]) {
		int size = outputsWithDescriptions.length;
		int newSize = 0;
		for(int i = 0; i < size; i++) {
			if(Character.isDigit(outputsWithDescriptions[i].charAt(0)));
				newSize++;
		}
		String output[] = new String[newSize];
		int j = 0;
		for(int i = 0; i < size; i++) {
			if(Character.isDigit(outputsWithDescriptions[i].charAt(0))){
				output[j++] = outputsWithDescriptions[i];
			}
		} 
		return output;
	}
	public String[] getAudioOutputs() {
		JavaSoundAudioIO aoi = new JavaSoundAudioIO();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		// IMPORTANT: Save the old System.out!
		PrintStream old = System.out;
		// Tell Java to use your special stream
		System.setOut(ps);
		// Print some output: goes to your special stream
		JavaSoundAudioIO.printMixerInfo();
		// Put things back
		System.out.flush();
		System.setOut(old);
		// Get what happened
		System.out.println("Here: " + baos.toString());
		return removeAudioDescriptionFromOutputLog(baos.toString().split("\n"));
		//Source : https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
	}
	public AudioContext getAudioContext() {
		return ac;
	}
	
}
