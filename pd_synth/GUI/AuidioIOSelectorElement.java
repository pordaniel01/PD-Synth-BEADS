package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import SynthSystem.AudioIOSelector;
import net.beadsproject.beads.core.AudioContext;

public class AuidioIOSelectorElement  {
	JComboBox jComboBox;
	public AudioIOSelector selector;
	private Interface interf;
	public AuidioIOSelectorElement(Interface interf) {
		this.interf = interf;
		selector = new AudioIOSelector();
		selector.selectMixer(0);
		String outputs[] = selector.getAudioOutputs();
		jComboBox = new JComboBox(outputs);
		jComboBox.addActionListener(comboBoxActionListener);
	}
	public JComboBox getJComboBox() {
		return jComboBox;
	}
	public AudioIOSelector getOutput() {
		return selector;
	}
	ActionListener comboBoxActionListener = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			int selected = jComboBox.getSelectedIndex();
			String selStr = (String)jComboBox.getSelectedItem();
			try {
				selector.selectMixer(selected);
				interf.engine.setAudioContext(selector.getAudioContext());
			} catch(Exception e1) {
				interf.setLogMessage(e1.getLocalizedMessage());
			}
			interf.setLogMessage("Selected audio IO: " + selStr);
			interf.mainPanel.setFocusable(true);
		}
	};	
}
