package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import SynthSystem.DataOperator;

public class FileManager extends JPanel implements ActionListener{
	private final JFileChooser fc;
	private JButton saveButton;
	private JButton loadButton;
	private Interface iface;
	private DataOperator dataHolder;
	public FileManager(Interface iface){
		this.iface = iface;
		dataHolder = new DataOperator(iface);
		fc = new JFileChooser();
		saveButton = new JButton("Save");
		loadButton = new JButton("Load");
		saveButton.addActionListener(this);
		loadButton.addActionListener(this);
	}
	public JButton getLoadButton() {
		return loadButton;
	}
	public JButton getSaveButton() {
		return saveButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Handle open button action.
        if (e.getSource() == loadButton) {
            int returnVal = fc.showOpenDialog(FileManager.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
        			FileInputStream f = new FileInputStream(file);
        			ObjectInputStream in = new ObjectInputStream(f);
        			dataHolder = (DataOperator)in.readObject();
        			dataHolder.setInterface(iface);
        			dataHolder.setData();
        			iface.repaint();
        			in.close();
        			} catch(IOException ex) {
        				iface.setLogMessage(ex.getMessage());
        			} catch(ClassNotFoundException ex) {
        				iface.setLogMessage(ex.getMessage());

        			} catch(Exception ex) {
        				iface.setLogMessage(ex.getMessage());
        			}
            } else {
                iface.setLogMessage("File loading cancelled by user");
            }
        //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(FileManager.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
        			FileOutputStream f = new FileOutputStream(file);
        			ObjectOutputStream out = new ObjectOutputStream(f);
        			dataHolder.getData();
        			out.writeObject(dataHolder);

        			out.close();
        		} catch(IOException ex) { 
        			iface.setLogMessage(ex.getMessage());
        			return;
        		} catch(Exception e1) {
        			iface.setLogMessage(e1.getMessage());
        			return;
        		}
                iface.setLogMessage("Saving: " + file.getName() );
            } else {
            	iface.setLogMessage("Save command cancelled by user." );
            }
        }
		
	}
	
}
