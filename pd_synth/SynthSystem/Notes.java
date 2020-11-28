package SynthSystem;

public class Notes {
	float baseNote = 440;  //This is the frequeny of A
	float TWELTHROOTOFTWO = 1.059463094359f;
	public Notes() {
		
	}
	public Notes(float baseNote) {
		this.baseNote = baseNote;
	}
	public float getFrequencyFromPressedKey(Keys key) {
		float Cfrequency = calculateCFromBaseNote();
		int distanceFromC = key.getValue();
		return (float) (Cfrequency * Math.pow(TWELTHROOTOFTWO, distanceFromC));
	}
	private float calculateCFromBaseNote() {
		return (float) (baseNote * Math.pow(TWELTHROOTOFTWO, -9));
	}
}
