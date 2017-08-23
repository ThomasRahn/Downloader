package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import file.Downloadable;
import file.Video;
import filters.Filter;

public class FilterTest {

	@Test
	public void testCanCreateFilter() {
		Filter filter = new Filter(new ArrayList<String>());
		
		assertTrue(filter instanceof Filter);
	}
	@Test
	public void testCanAddFilter() {
		Filter filter = new Filter(new ArrayList<String>());
		filter.addFilter("test");
		
		assertTrue(filter.getFilters().size() == 1);
	}
	
	@Test
	public void testIsValidFilter() {
		Filter filter = new Filter(new ArrayList<String>());
		filter.addFilter("test");
		
		Downloadable dl = new Video(1, "", "Video.Test.File");
		assertTrue(filter.validFilter(dl));
	}
	
	@Test
	public void testIsInvalidValidFilter() {
		Filter filter = new Filter(new ArrayList<String>());
		filter.addFilter("test");
		
		Downloadable dl = new Video(1, "", "Video.File.Invalid");
		assertTrue(!filter.validFilter(dl));
	}

}
