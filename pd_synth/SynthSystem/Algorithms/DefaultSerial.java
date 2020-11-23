package SynthSystem.Algorithms;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Function;
import net.beadsproject.beads.ugens.WavePlayer;

public class DefaultSerial implements Algorithm {
	public DefaultSerial() {
		
	}
	@Override
	public WavePlayer producedWavePlayer(float[] freqs, float[] intensities, Buffer[] buffs,  AudioContext ac, float baseFreq, Buffer mainBuffer) {
		WavePlayer FM1 = new WavePlayer(ac, freqs[0], buffs[0]);
		WavePlayer FM2 = new WavePlayer(ac, freqs[1], buffs[1]);
		WavePlayer FM3 = new WavePlayer(ac, freqs[2], buffs[2]);
		WavePlayer FM4 = new WavePlayer(ac, freqs[3], buffs[3]);
		Function func = new Function(FM1,FM2,FM3,FM4) {
			@Override
			public float calculate() {
				return x[0] * intensities[0] + x[1] * intensities[1] + 
						x[2] * intensities[2] + x[3] * intensities[3] + baseFreq;
			}
		};
		WavePlayer output = new WavePlayer(ac, func, mainBuffer);
		return output;
	}
	

}
