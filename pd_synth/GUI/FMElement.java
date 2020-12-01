package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	//private JSlider intensity[];
	//private JSlider frequency[];
	private ArrayList<JSlider> intensity;
	private ArrayList<JSlider> frequency;
	private Buffer bufferValues[];
	private float freqs[];
	private float intensities[];
	private JPanel  FMPanel;
	private JComboBox[] buffers;
	private Interface iface;
	private String bufferNames[];
	public FMElement(Interface iface) {
		this.iface = iface;
		intensity = new ArrayList<JSlider>(4);
		frequency = new ArrayList<JSlider>(4);
		buffers = new JComboBox[4];
		bufferNames = new String[4];
		bufferValues = new Buffer[4];
		freqs = new float[4];
		intensities = new float[4];
		for(int i = 0; i < 4; i++) {
			freqs[0] = 0;
		}
		for(int i = 0; i < 4; i++) {
			intensities[0] = 0;
		}
		for(int i = 0; i < 4; i++) {
			bufferNames[i] = "SINE";
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
			JSlider intensitySlider = new JSlider(0, 30);
			JSlider frequencySlider = new JSlider(0, 30);
			intensitySlider.setValue(0);
			frequencySlider.setValue(0);
			intensitySlider.setName(String.valueOf(i)+"intensity");
			frequencySlider.setName(String.valueOf(i)+"frequency");
			intensitySlider.setMajorTickSpacing(10);
			intensitySlider.setMinorTickSpacing(2);
			intensitySlider.setPaintTicks(true);
			intensitySlider.setPaintLabels(true);
			intensitySlider.setOrientation(JSlider.VERTICAL);
			intensitySlider.addChangeListener(cl);
			frequencySlider.setMajorTickSpacing(10);
			frequencySlider.setMinorTickSpacing(2);
			frequencySlider.setPaintTicks(true);
			frequencySlider.setPaintLabels(true);
			frequencySlider.setOrientation(JSlider.VERTICAL);
			frequencySlider.addChangeListener(cl);
			intensity.add(intensitySlider);
			frequency.add(frequencySlider);
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
			FMPanel.add(frequency.get(i),c);
			
			c.gridx = 1;
			c.gridy = padCounter;
			c.ipady = 40;
			c.fill = GridBagConstraints.VERTICAL;
			c.weighty = 1.0;
			FMPanel.add(intensity.get(i),c);
			
			c.gridx = 2;
			c.gridy = padCounter + 1;
			c.weightx = 0;
			c.ipadx = 0;
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
	public ArrayList<JSlider> getIntensitySlider() {
		return intensity;
	}
	public ArrayList<JSlider> getFrequencySlider() {
		return  frequency;
	}
	public JPanel getFMPanel() {
		return FMPanel;
	}
	public float[] getIntensites() {
		return intensities;
	}
	public float[] getFrequencies() {
		return freqs;
	}
	public Buffer[] getBuffers() {
		return bufferValues;
	}
	public String[] getBufferNames() {
		return bufferNames;
	}
	
	public void setBuffers(Buffer[] buffs, String[] bufferNames) {
		bufferValues = buffs;
		for(int i = 0; i < 4; i++) {
			buffers[i].setSelectedItem(bufferNames[i]);
			buffers[i].repaint();
		}
	}
	public void setFrequencies(float[] freqs) {
		this.freqs = freqs;
		for(int i = 0; i < 4; i++) {
			frequency.get(i).setValue((int) freqs[i]);
			frequency.get(i).repaint();
		}
	}
	public void setIntensities(float[] intensities) {
		this.intensities = intensities;
		for(int i = 0; i < 4; i++) {
			intensity.get(i).setValue((int) intensities[i]);
			intensity.get(i).repaint();
		}
	}
	ActionListener bufferSelectorListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < 4; i++) {
				if(buffers[i].getSelectedItem().equals("SINE")) {
					bufferValues[i] = Buffer.SINE;
				}
				else if(buffers[i].getSelectedItem().equals("SAW")) {
					bufferValues[i] = Buffer.SAW;
				}
				else if(buffers[i].getSelectedItem().equals("TRIANGLE")) {
					bufferValues[i] = Buffer.TRIANGLE;
				}
				else if(buffers[i].getSelectedItem().equals("SQUARE")) {
					bufferValues[i] = Buffer.SQUARE;
				}
				bufferNames[i] = (String) buffers[i].getSelectedItem();
			}
			iface.setLogMessage("Mod soundform 1: " + bufferNames[0] 
								+"    Mod soundform 2: "+ bufferNames[1] 
								+"    Mod soundform 3: "+ bufferNames[2]
								+"    Mod soundform 4: "+ bufferNames[3]);
			
		}
	};
}
