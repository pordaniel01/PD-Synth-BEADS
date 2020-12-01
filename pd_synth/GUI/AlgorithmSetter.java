package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import SynthSystem.Algorithms.Algorithm;
import SynthSystem.Algorithms.DefaultParallel;
import SynthSystem.Algorithms.DefaultSerial;

public class AlgorithmSetter {
	public JComboBox selector;
	Algorithm selectedAlgorithm;
	private String[] algorithmNames;
	private Interface iface;
	private int numberOfAlgorithms = 2;
	
	public AlgorithmSetter(Interface iface) {
		this.iface = iface;
		algorithmNames = new String[numberOfAlgorithms];
		for(int i = 0;  i < numberOfAlgorithms; i++)
			algorithmNames[i] = "Algorithm " + String.valueOf(i + 1);
		selector = new JComboBox(algorithmNames);
		selectedAlgorithm = new DefaultSerial();
		selector.addActionListener(al);
	}
	public Algorithm getSelectedAlgorithm() {
		return selectedAlgorithm;
	}
	public void setSelectedAlgoritm(Algorithm alg) {
		selectedAlgorithm = alg;
		System.out.print(alg.getAlgorithName());
		if(alg.getAlgorithName().equals("Default Parallel")) {
			selector.setSelectedItem("Algorithm 2");
		} else if(alg.getAlgorithName().equals("Default Serial"))
			selector.setSelectedItem("Algorithm 1");
		selector.repaint();
	}
	public JComboBox getComponent() {
		return selector;
	}
	ActionListener al = new ActionListener() {		
		@Override
		public void actionPerformed(ActionEvent e) {
			int selected = selector.getSelectedIndex();
			switch(selected) {
				case 0: selectedAlgorithm = new DefaultSerial(); break;
				case 1: selectedAlgorithm = new DefaultParallel(); break;				
			}
			iface.setLogMessage("Selected algorithm: " + selector.getSelectedItem().toString());
		}
	};
}
