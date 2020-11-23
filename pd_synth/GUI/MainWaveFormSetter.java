package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import net.beadsproject.beads.data.Buffer;

public class MainWaveFormSetter {
	JComboBox box;
	String options[] = {"SINE", "SAW", "TRIANGLE", "SQUARE"};
	Buffer selectedBuffer;
	Interface iface;
	public MainWaveFormSetter(Interface iface) {
		this.iface = iface;
		box = new JComboBox(options);
		box.addActionListener(al);
		selectedBuffer = Buffer.SINE;
	}
	ActionListener al = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String outputString = null;
			if(box.getSelectedItem().equals("SINE")) {
				selectedBuffer = Buffer.SINE;
				outputString = "SINE";
			}else if(box.getSelectedItem().equals("SAW")) {
				selectedBuffer = Buffer.SAW;
				outputString = "SAW";
			}else if(box.getSelectedItem().equals("TRIANGLE")) {
				selectedBuffer = Buffer.TRIANGLE;
				outputString = "Triangle";
			}else if(box.getSelectedItem().equals("SQUARE")) {
				selectedBuffer = Buffer.SQUARE;
				outputString = "Square";
			}
			iface.setLogMessage("Selected waveform of main soundwave: " + outputString);
		}
	};
	public JComboBox getSelector() {
		return box;
	}
	public Buffer getSelectedBuffer() {
		return selectedBuffer;
	}
}
