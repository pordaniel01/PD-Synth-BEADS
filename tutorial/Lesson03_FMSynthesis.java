import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.core.io.JavaSoundAudioIO;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Function;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.WavePlayer;

public class Lesson03_FMSynthesis {

	public static void main(String[] args) {
		AudioContext ac;

		  ac = new AudioContext();
		  /*
		   * In the last example, we used an Envelope to
		   * control the frequency of a WavePlayer.
		   * 
		   * In this example, we'll use another WavePlayer.
		   * This is called FM synthesis.
		   * 
		   * Here's the modulating WavePlayer. It has a low 
		   * frequency.
		   */
		  JavaSoundAudioIO aoi = new JavaSoundAudioIO();
			JavaSoundAudioIO.printMixerInfo();
			aoi.selectMixer(5);
			ac = new AudioContext(aoi);
			
			
		  WavePlayer freqModulator = new WavePlayer(ac, 10, Buffer.SINE);
		  WavePlayer freqMod2 = new WavePlayer(ac, 50, Buffer.SINE);
		  Function function = new Function(freqModulator,freqMod2) {
			  public float calculate() {
			      return x[0] * 200.0f + 440.0f + x[1] * 100.0f /*+ 700.0f*/;
			    }
		  };
		  WavePlayer wp = new WavePlayer(ac, function, Buffer.SINE);
		  Function function2 = new Function(wp) {
			    public float calculate() {
				      return x[0] * 100.0f ;
				    }
		  };
		  /*
		   * The next line might look outrageous if you're not
		   * experienced in Java. Basically we're defining a 
		   * Function on the fly which takes the freqModulator
		   * and maps it to a sensible range. Since the input
		   * to the function is a sine wave (freqModulator), the
		   * output will be a sine wave that goes from 500 to 700,
		   * 50 times a second.
		   */
		  /*Function function = new Function(freqModulator) {
		    public float calculate() {
		      return x[0] * 440.0f + 500.0f;
		    }
		  };
		 
		  /*
		   * Here's the WavePlayer that will actually play.
		   * Now we plug in the function. Compare this to the previous
		   * example, where we plugged in an envelope.
		   */
		  
		  WavePlayer wp2 = new WavePlayer(ac, function2, Buffer.SINE);
		  Function function3 = new Function(wp2) {
			    public float calculate() {
				      return x[0] * 1000.0f+ 440.0f;
				    }
		  };
		  WavePlayer wp3 = new WavePlayer(ac, function2, Buffer.SINE);
		  /**/
		   /* Connect it all together as before.
		   */
		  Gain g = new Gain(ac, 1, 0.1f);
		  g.addInput(wp3);
		  ac.out.addInput(g);
		  ac.start();
		
	}
}
