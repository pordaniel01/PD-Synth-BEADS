package SynthSystem.Algorithms;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Function;
import net.beadsproject.beads.ugens.WavePlayer;

public class DefaultParallel implements Algorithm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public WavePlayer producedWavePlayer(float[] freqs, float[] intensities, Buffer[] buffs, AudioContext ac,
			float baseFreq, Buffer baseBuffer) {
		WavePlayer FM1 = new WavePlayer(ac, freqs[0], buffs[0]);
		WavePlayer FM2 = new WavePlayer(ac, freqs[1], buffs[1]);
		WavePlayer FM3 = new WavePlayer(ac, freqs[2], buffs[2]);
		WavePlayer FM4 = new WavePlayer(ac, freqs[3], buffs[3]);
		Function func1 = new Function(FM1,FM2) {		
			@Override
			public float calculate() {
				return x[0] * intensities[0] + x[1] * intensities[1] ;
			}
		};
		Function func2 = new Function(FM3,FM4) {			
			@Override
			public float calculate() {
				return x[0] * intensities[2] + x[1] * intensities[3] ;
			}
		};
		WavePlayer thread1 = new WavePlayer(ac, func1, Buffer.SINE);
		WavePlayer thread2 = new WavePlayer(ac, func2, Buffer.SINE);
		Function func3 = new Function(thread1,thread2) {
			@Override
			public float calculate() {
				return x[0] * 100 + x[1] * 100 + baseFreq;
			}
			
		};
		WavePlayer output = new WavePlayer(ac, func3, baseBuffer);
		return output;
	}

	@Override
	public String getAlgorithName() {
		return "Default Parallel";
	}

}
