package SynthSystem;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import GUI.Interface;
import SynthSystem.Algorithms.DefaultSerial;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Noise;
import net.beadsproject.beads.ugens.WavePlayer;

public class SynthEngine /*extends Thread*/{
	private ArrayList<Keys> whichKeysArePressed;
	Interface interf;
	private AudioContext ac;
	private ArrayList<WavePlayer> notes;
	Gain g;
	ArrayList<WavePlayer> wavePlayers;
	
	public SynthEngine(Interface interf) {
		this.interf = interf;
		g = new Gain(ac, 1, 0.1f);
		whichKeysArePressed = new ArrayList<Keys>(24);
		notes = new ArrayList<WavePlayer>();
	}
	public void addWavePlayer(WavePlayer wp) {
		wavePlayers.add(wp);
	}
	

	public void stopPlaying() {
		/*for(float i = g.getGain(); i > -0.5f; i -= 0.01f) {
			System.out.println();
			g.setGain(i);
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.setGain(0);
		notes.clear();
		System.out.println("end");
	}
	public void getPressedKeys() {
		whichKeysArePressed= interf.piano.getPressedKeys();
	
		System.out.print(whichKeysArePressed.size());
	}
	public void startPlaying() {
		Notes noteFromKey = new Notes();
		g = new Gain(ac, 1, 1f);
		DefaultSerial alg1 = new DefaultSerial(); 
		Buffer buffs[] = {Buffer.SINE,Buffer.SINE,Buffer.SINE,Buffer.SINE};
		System.out.println("geci: " + interf.fmelement.getIntensites()[3]);
		//alg1.producedWavePlayer(interf.fmelement.getFrequencies(), interf.fmelement.getIntensites(), buffs, ac, noteFromKey.getFrequencyFromPressedKey(pressedKey))
		for(int i = 0; i < whichKeysArePressed.size();i++) {
			Keys pressedKey = whichKeysArePressed.get(i);
			WavePlayer wpNEW = alg1.producedWavePlayer(interf.fmelement.getFrequencies(), interf.fmelement.getIntensites(), buffs, ac, noteFromKey.getFrequencyFromPressedKey(pressedKey));
			notes.add(wpNEW);
			//notes.add(alg1.producedWavePlayer(interf.fmelement.getFrequencies(), interf.fmelement.getIntensites(), buffs, ac, noteFromKey.getFrequencyFromPressedKey(pressedKey))
//);
			//notes.add(new WavePlayer(ac, noteFromKey.getFrequencyFromPressedKey(pressedKey), Buffer.SINE));
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
