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
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.beadsproject.beads.data.Buffer;

public class FMElement extends Component{
	private JSlider intensity[];
	private JSlider frequency[];
	private Buffer bufferValues[];
	private float freqs[];
	private float intensities[];
	private JPanel  FMPanel;
	private JComboBox[] buffers;
	private Interface iface;
	public FMElement(Interface iface) {
		this.iface = iface;
		intensity = new JSlider[4];
		frequency = new JSlider[4];
		buffers = new JComboBox[4];
		bufferValues = new Buffer[4];
		freqs = new float[4];
		intensities = new float[4];
		for(int i = 0; i < 4; i++) {
			freqs[0] = 0;
		}
		for(int i = 0; i < 4; i++) {
			intensities[0] = 0;
		}
		FMPanel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		FMPanel.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		int padCounter = 0;
		ChangeListener cl = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				String name = source.getName();
				for(int i = 0; i < 4; i++) {
					String num = String.valueOf(i);
					String searchString =  num + "intensity"; 
					int nrOfModulator = i + 1;
					if(name.equals(searchString)) {
						intensities[i] = (float)source.getValue();
						iface.setLogMessage("Frequency modulator number " + nrOfModulator + " intensity set to " + (float)source.getValue()  );
					}
				}
				for(int i = 0; i < 4; i++) {
					String searchString = String.valueOf(i) + "frequency"; 
					int nrOfModulator = i + 1;
					if(name.equals(searchString)) {
						freqs[i] = (float)source.getValue();
						iface.setLogMessage("Frequency modulator number " + nrOfModulator + " frequency set to " + (float)source.getValue() + " hertz");
					}
				}

				
			}
		};
		
		String bufferOptions[] = {"SINE", "SAW", "TRIANGLE", "SQUARE"};
		for(int i = 0; i < 4; i++) {
			bufferValues[i] = Buffer.SINE;
		}
		for(int i = 0; i < 4; i++) {
			buffers[i] = new JComboBox(bufferOptions);
			buffers[i].addActionListener(bufferSelectorListener);
			intensity[i] = new JSlider(0, 500);
			frequency[i] = new JSlider(0, 50);
			intensity[i].setValue(0);
			frequency[i].setValue(0);
			intensity[i].setName(String.valueOf(i)+"intensity");
			frequency[i].setName(String.valueOf(i)+"frequency");
			intensity[i].setMajorTickSpacing(100);
			intensity[i].setMinorTickSpacing(10);
			intensity[i].setPaintTicks(true);
			intensity[i].setPaintLabels(true);
			intensity[i].setOrientation(JSlider.VERTICAL);
			intensity[i].addChangeListener(cl);
			frequency[i].setMajorTickSpacing(10);
			frequency[i].setMinorTickSpacing(5);
			frequency[i].setPaintTicks(true);
			frequency[i].setPaintLabels(true);
			frequency[i].setOrientation(JSlider.VERTICAL);
			frequency[i].addChangeListener(cl);
			JTextField text1 = new JTextField("Frequency " + (i+1));
			JTextField text2 = new JTextField("Intensity " + (i+1));
			text1.setEditable(false);
			text2.setEditable(false);
			text1.setSelectedTextColor(Color.ORANGE);
			text2.setSelectedTextColor(Color.BLUE);
			text1.setHorizontalAlignment(JTextField.CENTER);
			text2.setHorizontalAlignment(JTextField.CENTER);
			
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
			
			c.gridx = 2;
			c.gridy = padCounter + 1;
			c.weighty = 0;
			c.ipady = 5;
			FMPanel.add(buffers[i],c);
			
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
	public float[] getIntensites() {
		System.out.println("osztalybansd" +intensities[0]);

		return intensities;
	}
	public float[] getFrequencies() {
		return freqs;
	}
	public Buffer[] getBuffers() {
		return bufferValues;
	}
	ActionListener bufferSelectorListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < 4; i++) {
				if(buffers[i].getSelectedItem().equals("SINE"))
					bufferValues[i] = Buffer.SINE;
				else if(buffers[i].getSelectedItem().equals("SAW"))
					bufferValues[i] = Buffer.SAW;
				else if(buffers[i].getSelectedItem().equals("TRIANGLE"))
					bufferValues[i] = Buffer.TRIANGLE;
				else if(buffers[i].getSelectedItem().equals("SQUARE"))
					bufferValues[i] = Buffer.SQUARE;
			}
			
		}
	};
}
