package GUI;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VolumeSetter {
	private JSlider volumeSetter;
	private int volume;
	private Interface iface;
	public VolumeSetter(Interface iface) {
		volumeSetter = new JSlider(0,30);
		this.iface = iface;
		volumeSetter.setMajorTickSpacing(10);
		volumeSetter.setMinorTickSpacing(5);
		volumeSetter.setPaintTicks(true);
		volumeSetter.setPaintLabels(true);
		volume = 15;
		ChangeListener cl = new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				volume = (int)source.getValue();
				iface.setLogMessage("Gain set to: " + String.valueOf(source.getValue()));
			}
			
		};
		volumeSetter.addChangeListener(cl);
	}
	public int getVolume() {
		return volume;
	}
	public JSlider getComponent() {
		return volumeSetter;
	}
}
