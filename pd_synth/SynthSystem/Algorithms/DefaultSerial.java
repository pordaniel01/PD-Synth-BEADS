package SynthSystem.Algorithms;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.ugens.Function;
import net.beadsproject.beads.ugens.WavePlayer;

public class DefaultSerial implements Algorithm {
	private float baseFreq;
	public DefaultSerial(float baseFreq) {
		this.baseFreq = baseFreq;
	}
	@Override
	public WavePlayer producedWavePlayer(FreqModulator[] freqModulators, AudioContext ac, float baseFreq) {
		WavePlayer FM1 = new WavePlayer(ac, freqModulators[0].getFrequency(), freqModulators[0].getBuffer());
		WavePlayer FM2 = new WavePlayer(ac, freqModulators[1].getFrequency(), freqModulators[1].getBuffer());
		WavePlayer FM3 = new WavePlayer(ac, freqModulators[2].getFrequency(), freqModulators[2].getBuffer());
		WavePlayer FM4 = new WavePlayer(ac, freqModulators[3].getFrequency(), freqModulators[3].getBuffer());
		Function func = new Function(FM1,FM2,FM3,FM4) {
			@Override
			public float calculate() {
				return x[0] * freqModulators[0].getIntensity() + x[1] * freqModulators[1].getIntensity() + 
						x[2] * freqModulators[2].getIntensity() + x[3] * freqModulators[3].getIntensity() + baseFreq;
			}
		};
		return null;
	}
	

}
