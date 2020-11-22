package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class FMElement extends Component{
	private JSlider intensity[];
	private JSlider frequency[];
	private JPanel  FMPanel;
	public FMElement() {
		intensity = new JSlider[4];
		frequency = new JSlider[4];
		FMPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		FMPanel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		int padCounter = 0;
		for(int i = 0; i < 4; i++) {
			intensity[i] = new JSlider(0, 500);
			frequency[i] = new JSlider(0, 200);
			intensity[i].setMajorTickSpacing(100);
			intensity[i].setMinorTickSpacing(10);
			intensity[i].setPaintTicks(true);
			intensity[i].setPaintLabels(true);
			intensity[i].setOrientation(JSlider.VERTICAL);
			frequency[i].setMajorTickSpacing(100);
			frequency[i].setMinorTickSpacing(10);
			frequency[i].setPaintTicks(true);
			frequency[i].setPaintLabels(true);
			frequency[i].setOrientation(JSlider.VERTICAL);
			JTextField text1 = new JTextField("Intensity " + (i+1));
			JTextField text2 = new JTextField("Frequency " + (i+1));
			text1.setEditable(false);
			text2.setEditable(false);
			text1.setSelectedTextColor(Color.ORANGE);
			text2.setSelectedTextColor(Color.BLUE);
			text1.setHorizontalAlignment(JTextField.CENTER);
			text2.setHorizontalAlignment(JTextField.CENTER);

			//text1.setSize(WIDTH, 20);
			//text2.setSize(WIDTH, 20);

			//FMPanel.add(frequency[i]);
			//FMPanel.add(intensity[i]);
			//FMPanel.add(text1);
			//FMPanel.add(text2);
			
			c.gridx = 0;
			c.gridy = padCounter;
			c.ipady = 50;
			c.fill = GridBagConstraints.VERTICAL;
			c.weighty = 1.0;
			FMPanel.add(frequency[i],c);
			
			c.gridx = 1;
			c.gridy = padCounter;
			c.ipady = 40;
			c.fill = GridBagConstraints.VERTICAL;
			c.weighty = 1.0;
			FMPanel.add(intensity[i],c);
			
			c.weighty = 0;
			c.weightx = 0;
			c.ipadx = 30;
			c.gridx = 0;
			c.gridy = padCounter+1;
			c.ipady = 5;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weighty = 0;
			FMPanel.add(text1,c);
			
			c.gridx = 1;
			c.gridy = padCounter+1;
			c.ipady = 5;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weighty = 0;
			FMPanel.add(text2,c);
			padCounter += 2;
		}	
	}
	public JSlider[] getIntensitySlider() {
		return intensity;
	}
	public JSlider[] getFrequencySlider() {
		return  frequency;
	}
	public JPanel getFMPanel() {
		return FMPanel;
	}
	ActionListener sliderAL = new  ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
	};
}
