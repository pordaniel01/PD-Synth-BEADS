package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import SynthSystem.SynthEngine;
import net.beadsproject.beads.core.AudioContext;

public class Interface {
	private JFrame mainframe;
	public JPanel mainPanel;
	public JPanel fmPanel;
	public FMElement fmelement;
	private Logger logger;
	public Piano piano;
	double versionNumber;
	public AuidioIOSelectorElement selectorList;
	public SynthEngine engine;
	public VolumeSetter volSetter;
	public EnvelopeSetter envSetter;
	public MainWaveFormSetter waveSetter;
	public AlgorithmSetter algSetter;
	public FileManager fileManager;
	public Interface(double versionNumber) {
		this.versionNumber = versionNumber;
		mainframe = new JFrame("PD-SYNTH   " + versionNumber);
		logger = new Logger();		
		mainPanel = new JPanel();
		BorderLayout layout = new BorderLayout();
		selectorList = new AuidioIOSelectorElement(this);
		volSetter = new VolumeSetter(this);
		piano = new Piano(this);
		fmPanel = new JPanel();
		waveSetter = new MainWaveFormSetter(this);
		fmPanel.setLayout(new BoxLayout(fmPanel,BoxLayout.Y_AXIS));
		fmelement = new FMElement(this);
		fmPanel.add(fmelement);
		piano.setSynthEngine(engine);
		mainframe.setSize(1300, 700);	
		//https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainframe.setLocation(dim.width/2-mainframe.getSize().width/2, dim.height/2-mainframe.getSize().height/2);
		mainPanel.setLayout(layout);
		JPanel north = new JPanel();
		GridBagLayout northLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		JTextField volumeText = new JTextField("Gain");
		volumeText.setEditable(false);
		JTextField waveText = new JTextField("Main waveform: ");
		waveText.setEditable(false);
		volumeText.setEditable(false);
		north.setLayout(northLayout);
		c.gridy = 0;
		north.add(volSetter.getComponent(),c);
		c.gridx = 2;
		north.add(selectorList.getJComboBox(),c);
		c.gridx = 1;
		north.add(volumeText,c);
		c.gridx = 3;
		north.add(waveText,c);
		c.gridx = 4;
		north.add(waveSetter.getSelector(),c);
		envSetter = new EnvelopeSetter(this);
		c.gridy = 1;
		c.gridx = 0;
		north.add(envSetter.getStartEnvelopeSlider(),c);
		JTextField envText1 = new JTextField("Attack");
		envText1.setEditable(false);
		JTextField envText2 = new JTextField("Sustain");
		envText2.setEditable(false);
		c.gridx = 1;
		north.add(envText1,c);
		JTextField algSetterText = new JTextField("Select Algorith");
		algSetterText.setEditable(false);
		c.gridx = 3;
		north.add(algSetterText,c);
		algSetter = new AlgorithmSetter(this);
		c.gridx = 4;
		north.add(algSetter.getComponent(),c);
		
		c.gridy = 2;
		c.gridx = 0;
		north.add(envSetter.getEndEnvelopeSlider(),c);
		c.gridx = 1;
		north.add(envText2,c);
		
		
		fileManager = new FileManager(this);
		c.gridx = 3;
		north.add(fileManager.getSaveButton(),c);
		c.gridx = 4;
		north.add(fileManager.getLoadButton(),c);
		
		mainPanel.add(piano,BorderLayout.CENTER);
		mainPanel.add(north,BorderLayout.NORTH);
		mainframe.add(fmelement.getFMPanel(),BorderLayout.WEST);
		mainPanel.add(logger.getElement(),BorderLayout.SOUTH);
		mainPanel.addMouseListener(piano);
		mainPanel.addKeyListener(piano);
		mainPanel.setFocusable(true);
		mainframe.add(mainPanel);
		mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainframe.setVisible(true);
	}
	public void setLogMessage(String s) {
		logger.setMessage(s);
	}
	public void repaintPiano() {
		piano.repaint();
	}

	public void setSynthEngine(SynthEngine engine) {
		this.engine = engine;
		piano.setSynthEngine(engine);
	}
	public void repaint() {
		mainframe.repaint();
	}
}
