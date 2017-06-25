package managers;

import java.util.ArrayList;
import java.util.List;

import org.json.*;
import file.Downloadable;
import file.Video;

public class JSONManager {
	
	
	/*
	 * @param String json_str
	 * 
	 * Parse the String parameter into a JSONObject 
	 */
	public static JSONObject toJSON(String json_str){
		JSONObject json = new JSONObject(json_str);
		
		return json;
	}
	
	
	/*
	 * @param JSONArray
	 * @return List<Downloadable>
	 * 
	 * This method will iterate through the JSONArray and create a Downloadable object and return the list.
	 */
	public static List<Downloadable> getVideos(JSONArray arr){
		List<Downloadable> downloads = new ArrayList<Downloadable>(arr.length());
		
		for(int i = 0; i < arr.length(); i ++){
			//In the mean time, create all objects as "videos"
			JSONObject obj = (JSONObject) arr.get(i);
			Video v = new Video(obj.getInt("id"), obj.getString("link"), obj.getString("filename"));
			
			v.setSize(obj.getLong("size"));
			
			downloads.add(v);
		}
		
		return downloads;
	}
}
