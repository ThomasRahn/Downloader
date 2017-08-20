package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import managers.DisplayManager;

public class DisplayManagerTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
	
	@Test
	public void testDisplayEmptyProgress() {
		DisplayManager.display_progress_bar(0);
		
		String empty = "\r[.                                                 ]";
		assertEquals(empty, outContent.toString());
	}
	
	@Test
	public void testCompletedProgressBar() {
		DisplayManager.display_progress_bar(100);
		
		String full = "\r[...................................................]";
		assertEquals(full, outContent.toString());
	}

}
