package SynthSystem.Algorithms;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.ugens.WavePlayer;

public interface Algorithm {
	public WavePlayer producedWavePlayer(FreqModulator[] freqModulators, AudioContext ac,float baseFreq);
}
