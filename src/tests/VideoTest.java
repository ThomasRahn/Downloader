package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import file.Video;

public class VideoTest {

	@Test
	public void testCanCreateVideo() {
		Video vid = new Video(1, "http://www.google.ca", "test.html");
		
		assertTrue(vid instanceof Video);
	}
	
}
