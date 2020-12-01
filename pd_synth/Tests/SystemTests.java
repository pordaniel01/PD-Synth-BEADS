package Tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import GUI.AuidioIOSelectorElement;
import GUI.Interface;
import GUI.Logger;
import GUI.Piano;
import SynthSystem.AudioIOSelector;
import SynthSystem.DataOperator;
import SynthSystem.Keys;
import SynthSystem.Notes;
import SynthSystem.SynthEngine;
import SynthSystem.Algorithms.Algorithm;
import SynthSystem.Algorithms.DefaultSerial;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.WavePlayer;

public class SystemTests {
	Interface iface;
	DataOperator operator;
	Buffer[] fmBuffers;
	float freqs[] = {0.0f,0.0f,0.0f,0.0f};
	float intensities[] = {0.0f,0.0f,0.0f,0.0f};
	
	Notes notes = new Notes();
	float expectedFreqOfC = 261.625f;
	
	Piano piano;
		
	AudioIOSelector selector;
	
	SynthEngine engine;
	@Before
	public void testDataOperatorInit() {
		iface = new Interface(1.0);
		operator = new DataOperator(iface);
		operator.getData();  //This is the tested method actually
		fmBuffers = new Buffer[4];
		for(int i = 0; i < 4; i++)
			fmBuffers[i] = Buffer.SINE;
	}
	@Test //DataOperator
	public void checkData() {
		assertEquals(1000,operator.getAttack());
		assertEquals(1000, operator.getDecay());
		assertArrayEquals( fmBuffers,operator.getFmBuffers());
		assertTrue(Arrays.equals(freqs, operator.getFreqs()));
		assertTrue(Arrays.equals(intensities, operator.getIntensities()));

	}
	@Test 
	public void testNotes() {
		assertEquals(expectedFreqOfC, notes.getFrequencyFromPressedKey(Keys.C), 0.01);
	}
	
	@Test
	public void testInterfaceAndComponents() {
		assertEquals("Message", "Logs appear here",iface.getLogger().getLog() );
		assertEquals(1000,iface.getEnvSetter().getEndDelay());
		assertEquals(1000,iface.getEnvSetter().getStartDelay());
		assertEquals(new DefaultSerial().getClass(),iface.getAlgSetter().getSelectedAlgorithm().getClass());
		assertTrue(Arrays.equals(freqs, iface.getFmelement().getFrequencies()));
		assertTrue(Arrays.equals( intensities,iface.getFmelement().getIntensites()));
		assertEquals(15,iface.getVolSetter().getVolume());
		assertEquals(Buffer.SINE,iface.getWaveSetter().getSelectedBuffer());

	}
	
	@Before
	public void preparePiano() {
		piano = new Piano(iface);
	}
	@Test
	public void testPianoMethods() {
		assertTrue(piano.pointInsideOfRectangle(new Point(2,2), 0, 0, 10, 10));
		assertEquals(Keys.C,piano.createKeyValueOutOfWhiteKey(0));
		assertEquals(Keys.E,piano.createKeyValueOutOfWhiteKey(2));

	}
	@Before
	public void prepAudioIOSel() {
		selector = new AudioIOSelector();
		selector.selectMixer(0);
	}
	@Test
	public void testAudioIOSEL() {
		assertNotNull(selector.getAudioOutputs());
		assertEquals(new AudioContext().getClass(),selector.getAudioContext().getClass());
	}
	@Before
	public void setUpEngine() {
		engine = new SynthEngine(iface);
	}
	@Test
	public void testSynthEngine() {
		assertEquals(new Gain(engine.getAc(), 0).getClass(),engine.getG().getClass());
		assertNotNull(engine.getWhichKeysArePressed());
		assertNotNull(engine.getNotes());
	}
}
