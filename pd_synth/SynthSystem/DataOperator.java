package SynthSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import GUI.Interface;
import SynthSystem.Algorithms.Algorithm;
import net.beadsproject.beads.data.Buffer;

public class DataOperator implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private transient Interface iface;
	public int gain;
	private float freqs[];
	private float intensities[];
	private int attack;
	public int decay;
	private Buffer fmBuffers[];
	private Buffer mainBuffer;
	private Algorithm alg;
	private String mainBufferString;
	private String[] fmBufferStrings;
	public DataOperator(Interface iface){
		this.iface = iface;
	}
	public void setInterface(Interface iface) {
		this.iface = iface;
	}
	public void getData() {
		gain = iface.volSetter.getVolume();
		freqs = iface.fmelement.getFrequencies();
		fmBuffers = iface.fmelement.getBuffers();
		intensities = iface.fmelement.getIntensites();
		attack = iface.envSetter.getStartDelay();
		decay = iface.envSetter.getEndDelay();
		mainBuffer = iface.waveSetter.getSelectedBuffer();
		mainBufferString = iface.waveSetter.getBufferString();
		fmBufferStrings = iface.fmelement.getBufferNames();
		for(int i = 0; i < 4;i++)
			System.out.print(fmBufferStrings[i]);
	}
	public void setData() {
		iface.volSetter.setVolume(gain);
		iface.fmelement.setIntensities(intensities);
		iface.fmelement.setFrequencies(freqs);
		iface.fmelement.setBuffers(fmBuffers,fmBufferStrings);
		iface.envSetter.setEndDelay(decay);
		iface.envSetter.setStartDelay(attack);
		iface.waveSetter.setBuffer(mainBuffer,mainBufferString);

	}
	
	
}
