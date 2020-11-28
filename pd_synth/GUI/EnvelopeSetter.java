package GUI;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EnvelopeSetter {
	private JSlider startEnvelope;
	private JSlider endEnvelope;
	private int startDelay;
	private int endDelay;
	private Interface iface;
	public EnvelopeSetter(Interface iface) {
		this.iface = iface;
		startDelay = 1000;
		endDelay = 1000;
		startEnvelope = new JSlider(1,2001);
		endEnvelope =  new JSlider(1,2001);
		startEnvelope.setMajorTickSpacing(999);
		startEnvelope.setMinorTickSpacing(501);
		endEnvelope.setMajorTickSpacing(999);
		endEnvelope.setMinorTickSpacing(501);
		endEnvelope.setPaintTicks(true);
		endEnvelope.setPaintLabels(true);
		startEnvelope.setPaintTicks(true);
		startEnvelope.setPaintLabels(true);
		startEnvelope.setName("start");
		endEnvelope.setName("end");
		ChangeListener cl = new ChangeListener() {		
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if(source.getName().equals("start")) {
					iface.setLogMessage("Start Delay set to" + String.valueOf(source.getValue()) + " ms");
					startDelay = source.getValue();
				}
				else if (source.getName().equals("end")) {
					iface.setLogMessage("End Delay set to" + String.valueOf(source.getValue()) + " ms");
					endDelay = source.getValue();
				}
			}
		};
		startEnvelope.addChangeListener(cl);
		endEnvelope.addChangeListener(cl);
	}
	public JSlider getEndEnvelopeSlider() {
		return endEnvelope;
	}
	public JSlider getStartEnvelopeSlider() {
		return startEnvelope;
	}
	public int getStartDelay() {
		return startDelay;
	}
	public int getEndDelay() {
		return endDelay;
	}
	public void setStartDelay(int attack) {
		System.out.println("reseting");
		startDelay = attack;
		startEnvelope.setValue(attack);
		startEnvelope.repaint();
	}
	public void setEndDelay(int decay) {
		endDelay = decay;
		endEnvelope.setValue(decay);
		endEnvelope.repaint();
	}
}
