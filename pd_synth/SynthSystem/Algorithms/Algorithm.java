package SynthSystem.Algorithms;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.WavePlayer;

public interface Algorithm {

	WavePlayer producedWavePlayer(float[] freqs, float[] intensities, Buffer[] buffs, AudioContext ac, float baseFreq, Buffer baseBuffer);
}
