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
        			System.out.println("gain:" + dataHolder.gain);
        			System.out.println("decay:" + dataHolder.decay);
        			dataHolder.setInterface(iface);
        			dataHolder.setData();
        			iface.repaint();
        			in.close();
        			} catch(IOException ex) {
        				iface.setLogMessage(ex.getMessage());
        				System.out.println(ex.getMessage());
        			} catch(ClassNotFoundException ex) {
        				iface.setLogMessage(ex.getMessage());
        				System.out.println(ex.getMessage());

        			} catch(Exception ex) {
        				iface.setLogMessage(ex.getMessage());
        				System.out.println(ex.getMessage());
        			}
            } else {
                iface.setLogMessage("Unnable to load file");
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
        			System.out.println("siker");

        			out.close();
        		} catch(IOException ex) { 
        			iface.setLogMessage(ex.getMessage());
    				System.out.println(ex.getMessage() + "ezz");

        			return;
        		} catch(Exception e1) {
        			iface.setLogMessage(e1.getMessage());
    				System.out.println(e1.getMessage() + "attt");

        			return;
        		}
                iface.setLogMessage("Saving: " + file.getName() );
            } else {
            	iface.setLogMessage("Save command cancelled by user." );
            }
        }
		
	}
	
}
