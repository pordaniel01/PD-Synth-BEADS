package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
	public Interface(double versionNumber) {
		this.versionNumber = versionNumber;
		mainframe = new JFrame("PD-SYNTH   " + versionNumber);
		logger = new Logger();		
		mainPanel = new JPanel();
		BorderLayout layout = new BorderLayout();
		selectorList = new AuidioIOSelectorElement(this);
		piano = new Piano(this);
		fmPanel = new JPanel();
		fmPanel.setLayout(new BoxLayout(fmPanel,BoxLayout.Y_AXIS));
		fmelement = new FMElement();
		fmPanel.add(fmelement);
		piano.setSynthEngine(engine);
		mainframe.setSize(1300, 700);	
		//https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainframe.setLocation(dim.width/2-mainframe.getSize().width/2, dim.height/2-mainframe.getSize().height/2);
		mainPanel.setLayout(layout);
		mainPanel.add(selectorList.getJComboBox(),BorderLayout.NORTH);
		mainPanel.add(piano,BorderLayout.CENTER);
		//mainPanel.add(new FMElement().getFrequencySlider(),BorderLayout.WEST);
		//mainPanel.add(new FMElement().getIntensitySlider(),BorderLayout.WEST);

		mainframe.add(fmelement.getFMPanel(),BorderLayout.WEST);
		//west.add(new FMElement().getFMPanel());
		//west.add(new FMElement().getFMPanel());
		//west.add(new FMElement().getFMPanel());
		//mainframe.add(west,BorderLayout.WEST);
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
	public void addElement() {
		
	}
	public void setSynthEngine(SynthEngine engine) {
		this.engine = engine;
		piano.setSynthEngine(engine);
	}
}
