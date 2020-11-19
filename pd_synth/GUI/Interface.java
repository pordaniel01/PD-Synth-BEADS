package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import SynthSystem.SynthEngine;
import net.beadsproject.beads.core.AudioContext;

public class Interface {
	private JFrame mainframe;
	private JPanel mainPanel;
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
		piano.setSynthEngine(engine);
		mainframe.setSize(1000, 600);	
		//https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainframe.setLocation(dim.width/2-mainframe.getSize().width/2, dim.height/2-mainframe.getSize().height/2);
		mainPanel.setLayout(layout);
		mainPanel.add(selectorList.getJComboBox(),BorderLayout.NORTH);
		mainPanel.add(logger.getElement(),BorderLayout.SOUTH);
		mainPanel.add(piano,BorderLayout.CENTER);
		mainPanel.addMouseListener(piano);
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
