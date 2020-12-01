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
	private double versionNumber;
	private JFrame mainframe;
	private BorderLayout layout;
	public JPanel mainPanel; 
	public JPanel fmPanel;
	public FMElement fmelement;
	private Logger logger;
	public Piano piano;
	public AuidioIOSelectorElement selectorList;
	public SynthEngine engine;
	public VolumeSetter volSetter;
	public EnvelopeSetter envSetter;
	public MainWaveFormSetter waveSetter;
	public AlgorithmSetter algSetter;
	public FileManager fileManager;
	public Interface(double versionNumber) {
		this.versionNumber = versionNumber;
		//Init
		createComponents();
		fmPanel.add(fmelement);
		piano.setSynthEngine(engine);
		mainframe.setSize(1300, 700);	
		//https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
		//HOW TO SET FRAME IN THE MIDDLE
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainframe.setLocation(dim.width/2-mainframe.getSize().width/2, dim.height/2-mainframe.getSize().height/2);
		mainPanel.setLayout(layout);
		
		//NORTH INIT
		JPanel north = new JPanel();  //NORTH PANEL
		GridBagLayout northLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		north.setLayout(northLayout);
		
		JTextField volumeText = new JTextField("Gain");
		volumeText.setEditable(false);
		JTextField waveText = new JTextField("Main waveform: ");
		waveText.setEditable(false);
		
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
	private void createComponents(){
		mainframe = new JFrame("PD-SYNTH   " + versionNumber);
		logger = new Logger();		
		mainPanel = new JPanel();
		layout = new BorderLayout();
		selectorList = new AuidioIOSelectorElement(this);
		volSetter = new VolumeSetter(this);
		piano = new Piano(this);
		fmPanel = new JPanel();
		waveSetter = new MainWaveFormSetter(this);
		fmPanel.setLayout(new BoxLayout(fmPanel,BoxLayout.Y_AXIS));
		fmelement = new FMElement(this);
	}
	public void setLogMessage(String s) {
		logger.setMessage(s);
	}
	public void repaintPiano() {
		piano.repaint();
	}
	
	
	//Getters and setter for testing
	public void setSynthEngine(SynthEngine engine) {
		this.engine = engine;
		piano.setSynthEngine(engine);
	}
	public void repaint() {
		mainframe.repaint();
	}
	public JFrame getMainframe() {
		return mainframe;
	}
	public void setMainframe(JFrame mainframe) {
		this.mainframe = mainframe;
	}
	public JPanel getMainPanel() {
		return mainPanel;
	}
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	public JPanel getFmPanel() {
		return fmPanel;
	}
	public void setFmPanel(JPanel fmPanel) {
		this.fmPanel = fmPanel;
	}
	public FMElement getFmelement() {
		return fmelement;
	}
	public void setFmelement(FMElement fmelement) {
		this.fmelement = fmelement;
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public Piano getPiano() {
		return piano;
	}
	public void setPiano(Piano piano) {
		this.piano = piano;
	}
	public double getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(double versionNumber) {
		this.versionNumber = versionNumber;
	}
	public AuidioIOSelectorElement getSelectorList() {
		return selectorList;
	}
	public void setSelectorList(AuidioIOSelectorElement selectorList) {
		this.selectorList = selectorList;
	}
	public SynthEngine getEngine() {
		return engine;
	}
	public void setEngine(SynthEngine engine) {
		this.engine = engine;
	}
	public VolumeSetter getVolSetter() {
		return volSetter;
	}
	public void setVolSetter(VolumeSetter volSetter) {
		this.volSetter = volSetter;
	}
	public EnvelopeSetter getEnvSetter() {
		return envSetter;
	}
	public void setEnvSetter(EnvelopeSetter envSetter) {
		this.envSetter = envSetter;
	}
	public MainWaveFormSetter getWaveSetter() {
		return waveSetter;
	}
	public void setWaveSetter(MainWaveFormSetter waveSetter) {
		this.waveSetter = waveSetter;
	}
	public AlgorithmSetter getAlgSetter() {
		return algSetter;
	}
	public void setAlgSetter(AlgorithmSetter algSetter) {
		this.algSetter = algSetter;
	}
	public FileManager getFileManager() {
		return fileManager;
	}
	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}
}
