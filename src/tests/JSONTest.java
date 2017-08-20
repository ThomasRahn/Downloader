package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import file.Downloadable;
import file.Video;
import managers.JSONManager;

public class JSONTest {
	

	@Test
	public void testCreateJsonObject() {
		JSONObject json = JSONManager.toJSON("{ \"test\" : 23 }");
		
		assertTrue(json instanceof JSONObject);
	}

	@Test
	public void testGetJSONVideos() {
		String testJson = "{\n" + 
				"	\"data\": [{\n" + 
				"		\"id\": 9306,\n" + 
				"		\"type\": \"video.tv\",\n" + 
				"		\"filename\": \"Halt.and.Catch.Fire.S04E01.720p.HDTV.x264-AVS.mkv\",\n" + 
				"		\"size\": 2385004848,\n" + 
				"		\"modified_at\": \"2017-08-19 23:58:33\",\n" + 
				"		\"link\": \"https:\\/\\/api.thedrop.pw\\/v1\\/download\\/2B72y69LKsYVP7YhAYpigHoq\\/Halt.and.Catch.Fire.S04E01.720p.HDTV.x264-AVS.mkv\"\n" + 
				"	}]\n" + 
				"}";
		
		JSONObject json = JSONManager.toJSON(testJson);
		
		//get objects
		List<Downloadable> vids = JSONManager.getVideos(json.getJSONArray("data"));
		Video vid = (Video) vids.get(0);
		
		//assertions
		assertTrue(vids.size() == 1);
		assertTrue(vid instanceof Video);
		
	}
}
