package GUI;

import javax.swing.JTextField;

public class Logger {
	private String log;
	private JTextField logger;
	public Logger() {
		log = "Logs appear here";
		logger = new JTextField(log);
		logger.setEditable(false);
	}
	public void setMessage(String s) {
		logger.setText(s);
	}
	public JTextField getElement() {
		return logger;
	}
	public String getLog() {
		return log;
	}
}
