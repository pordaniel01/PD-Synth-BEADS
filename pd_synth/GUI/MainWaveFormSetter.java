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
	String bufferString;
	public MainWaveFormSetter(Interface iface) {
		this.iface = iface;
		box = new JComboBox(options);
		box.addActionListener(al);
		selectedBuffer = Buffer.SINE;
	}
	ActionListener al = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			bufferString = null;
			if(box.getSelectedItem().equals("SINE")) {
				selectedBuffer = Buffer.SINE;
				bufferString = "SINE";
			}else if(box.getSelectedItem().equals("SAW")) {
				selectedBuffer = Buffer.SAW;
				bufferString = "SAW";
			}else if(box.getSelectedItem().equals("TRIANGLE")) {
				selectedBuffer = Buffer.TRIANGLE;
				bufferString = "TRIANGLE";
			}else if(box.getSelectedItem().equals("SQUARE")) {
				selectedBuffer = Buffer.SQUARE;
				bufferString = "SQUARE";
			}
			iface.setLogMessage("Selected waveform of main soundwave: " + bufferString);
		}
	};
	public JComboBox getSelector() {
		return box;
	}
	public Buffer getSelectedBuffer() {
		return selectedBuffer;
	}
	public String getBufferString() {
		return bufferString;
	}
	public void setBuffer(Buffer buffer, String bufferName) {
		this.selectedBuffer = buffer;
		this.bufferString = bufferName;
		System.out.println(bufferName);
		box.setSelectedItem(bufferName);
		box.repaint();
	}
}
