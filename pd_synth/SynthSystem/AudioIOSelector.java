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
	private SynthEngine engine;
	int numberOfMixer;
	public AudioIOSelector() {

	}
	public void setEngine(SynthEngine engine) {
		this.engine = engine;
	}
	public void selectMixer(int numberOfMixer) {
		this.numberOfMixer = numberOfMixer;
		aoi = new JavaSoundAudioIO();
		aoi.selectMixer(numberOfMixer);
		ac = new AudioContext(aoi);
		//System.out.println(ac.getAudioIO().toString());
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
		return baos.toString().split("\n");
		//Source : https://stackoverflow.com/questions/8708342/redirect-console-output-to-string-in-java
	}
	public AudioContext getAudioContext() {
		return ac;
	}
	
}
