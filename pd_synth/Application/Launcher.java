package Application;

import GUI.AuidioIOSelectorElement;
import SynthSystem.AudioIOSelector;
import SynthSystem.SynthEngine;
import net.beadsproject.beads.core.AudioContext;
import GUI.*;

public class Launcher {
	public static void main(String args[]) {
		Interface iface = new Interface(1.0);
		SynthEngine engine = new SynthEngine(iface);
		iface.setSynthEngine(engine);
		engine.setAudioContext(iface.selectorList.selector.getAudioContext());
	}
}
