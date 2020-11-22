package SynthSystem.Algorithms;

import net.beadsproject.beads.data.Buffer;

public class FreqModulator {
	private float frequency;
	private float intensity;
	private Buffer buffer;
	public FreqModulator(float freq, float intensity, Buffer buffer) {
		this.frequency = freq;
		this.intensity = intensity;
		this.buffer = buffer;
	}
	public float getFrequency() {
		return frequency;
	}
	public float getIntensity() {
		return intensity;
	}
	public Buffer getBuffer() {
		return buffer;
	}
}
