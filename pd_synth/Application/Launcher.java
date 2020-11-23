package Application;

import GUI.AuidioIOSelectorElement;
import SynthSystem.AudioIOSelector;
import SynthSystem.SynthEngine;
import net.beadsproject.beads.core.AudioContext;
import GUI.*;

public class Launcher {
	public static void main(String args[]) {
		//AudioIOSelector selector = new AudioIOSelector();
		//System.out.print(selector.getAudioOutputs()[2]);
		//AudioContext MAINAC = new AudioContext();
		Interface iface = new Interface(0.01);
		SynthEngine engine = new SynthEngine(iface);
		iface.setSynthEngine(engine);
		engine.setAudioContext(iface.selectorList.selector.getAudioContext());
		//engine.start();

	}
}
