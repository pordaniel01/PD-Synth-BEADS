package SynthSystem;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.GroupLayout.ParallelGroup;

import GUI.Interface;
import SynthSystem.Algorithms.Algorithm;
import SynthSystem.Algorithms.DefaultParallel;
import SynthSystem.Algorithms.DefaultSerial;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.Bead;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Envelope;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Noise;
import net.beadsproject.beads.ugens.WavePlayer;

public class SynthEngine /*extends Thread*/{
	private ArrayList<Keys> whichKeysArePressed;
	Interface interf;
	private AudioContext ac;
	private ArrayList<WavePlayer> notes;
	Gain g;
	private float volume;
	public SynthEngine(Interface interf) {
		this.interf = interf;
		g = new Gain(ac, 1, 0.1f);
		whichKeysArePressed = new ArrayList<Keys>(24);
		notes = new ArrayList<WavePlayer>();
	} 
	public void stopPlaying() {
		notes.clear();
	}
	public void getPressedKeys() {
		whichKeysArePressed= interf.piano.getPressedKeys();
	}
	public void startPlaying() {
		Notes noteFromKey = new Notes();
		Envelope envelope = new Envelope(ac,0.0f);
		envelope.addSegment((float)interf.volSetter.getVolume()/10, interf.envSetter.getStartDelay());
		envelope.addSegment(0.0f, interf.envSetter.getEndDelay());
		g = new Gain(ac, 1, envelope);
		Algorithm alg = interf.algSetter.getSelectedAlgorithm();
		for(int i = 0; i < whichKeysArePressed.size();i++) {
			Keys pressedKey = whichKeysArePressed.get(i);
			WavePlayer wp = alg.producedWavePlayer(interf.fmelement.getFrequencies(), interf.fmelement.getIntensites(), interf.fmelement.getBuffers(), ac, noteFromKey.getFrequencyFromPressedKey(pressedKey),interf.waveSetter.getSelectedBuffer());
			notes.add(wp);
		}
		for(int i = 0; i < notes.size();i++) {
			  g.addInput(notes.get(i));
		}
		ac.out.addInput(g);
		ac.start();
	}
	public void setAudioContext(AudioContext ac) {
		this.ac = ac;
	}

}
