package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComponent;

import SynthSystem.Keys;
import SynthSystem.SynthEngine;
import net.beadsproject.beads.core.AudioContext;

public class Piano extends JComponent implements MouseListener{
	Interface interf;
	private ArrayList<Keys> pressedKeys;
	private int numberOfKeys = 15;
	private int numberOfBlackKeys = 10;
	private int keyHeight = 300;
	private int keyWidth = 65;
	private int blackKeyWidth = 24;
	private int blackKeyHeight = 200;
	private Point blackKeysPositions[] = new Point[10];
	private Point keyPositions[] = new Point[15];
	private SynthEngine engine;
	public Piano(Interface interf) {
		this.interf = interf;
		pressedKeys = new ArrayList<Keys>(24);
	}
	public void setSynthEngine(SynthEngine se) {
		engine = se;
	}
	public void paint(Graphics g) {
		setBackground(Color.white);
		int shiftX = 60;
		int shiftY = 150;
		for(int i = 0; i < numberOfKeys; i++) {
			int pos = i * keyWidth;
			g.drawLine(pos + shiftX, shiftY, pos + shiftX, keyHeight + shiftY);
			keyPositions[i] = new Point(pos + shiftX,shiftY);

		}
		g.drawLine(shiftX, shiftY, numberOfKeys * keyWidth , shiftY);
		g.drawLine(shiftX, shiftY + keyHeight,numberOfKeys * keyWidth , shiftY + keyHeight);
		int blackKey = 0;
		for(int i = 1; i < numberOfKeys - 1 ; i++) {
			if(!(i % 7 == 3 || i % 7 == 0)) {
				int pos = i * keyWidth ;
				g.fillRect(pos + shiftX - blackKeyWidth/2, shiftY, blackKeyWidth, blackKeyHeight);
				blackKeysPositions[blackKey] = new Point(pos + shiftX - blackKeyWidth/2,shiftY);
				blackKey++;
			}
		}
	}
	private boolean pointInsideOfRectangle(Point p, int x1, int y1, int width, int height) {
		return p.x > x1 && p.x < (x1 + width) && p.y > y1 && p.y < (y1 + height);
	}
	private int whichBlackKeyHasBeenPressed(MouseEvent e) {
		Point mousePosition = e.getPoint();
		for(int i = 0; i < numberOfBlackKeys; i++) {
			if(pointInsideOfRectangle(mousePosition, blackKeysPositions[i].x, blackKeysPositions[i].y + 25, 
													  blackKeyWidth  ,  blackKeyHeight ) )
				return i;
		}
		return -1;
	}
	private int whichWhiteKeyHasBeenPressed(MouseEvent e) {
		Point mousePosition = e.getPoint();	
		for(int i = 0; i < numberOfKeys; i++) {
			if(pointInsideOfRectangle(mousePosition, keyPositions[i].x , keyPositions[i].y + 25, 
													  keyWidth ,  keyHeight) )
				return i;
				
		}
		return -1;
	}
	private Keys createKeyValueOutOfWhiteKey(int key) {
		switch(key) {
			case 0: return Keys.C;
			case 1: return Keys.D;
			case 2: return Keys.E;
			case 3: return Keys.F;
			case 4: return Keys.G;
			case 5: return Keys.A;
			case 6: return Keys.H;
			case 7: return Keys.C1;
			case 8: return Keys.D1;
			case 9: return Keys.E1;
			case 10: return Keys.F1;
			case 11: return Keys.G1;
			case 12: return Keys.A1;
			case 13: return Keys.H1;
			default: return null;
		}
	}
	private Keys createKeyValueOutOfBlackKey(int key) {
		switch(key) {
			case 0: return Keys.CS;
			case 1: return Keys.DS;
			case 2: return Keys.FS;
			case 3: return Keys.GS;
			case 4: return Keys.AS;
			case 5: return Keys.C1S;
			case 6: return Keys.D1S;
			case 7: return Keys.F1S;
			case 8: return Keys.G1S;
			case 9: return Keys.A1S;
			default: return null;
		}
	}
	public ArrayList<Keys> getPressedKeys() {
		return pressedKeys;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}
	@Override
	public synchronized void mousePressed(MouseEvent e) {
		Keys note = null;
		int pressedBlackKey = -1;
		int pressedWhiteKey = -1;
		pressedBlackKey = whichBlackKeyHasBeenPressed(e);
		if(pressedBlackKey < 0) {
			pressedWhiteKey = whichWhiteKeyHasBeenPressed(e);
			if(pressedWhiteKey < 0 ) {
				interf.setLogMessage("");
			}else {
				note = createKeyValueOutOfWhiteKey(pressedWhiteKey);
				interf.setLogMessage(note.toString());
				pressedKeys.add(note);
			}
		}else {
			note = createKeyValueOutOfBlackKey(pressedBlackKey);
			interf.setLogMessage(note.toString());
			pressedKeys.add(note);
		}
		if(pressedWhiteKey < 0 && pressedBlackKey < 0) {
			interf.setLogMessage("");
		}
		System.out.println("wakiae");
		engine.setAudioContext(interf.selectorList.selector.getAudioContext());
		engine.getPressedKeys();	
		engine.startPlaying();
		
		System.out.println("GEHFE");
		//if(engine.getState() == Thread.State.WAITING) {
			//engine.notify();
		//	System.out.println("wakie");
		//}
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		engine.stopPlaying();
		pressedKeys.clear();	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
