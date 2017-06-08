package main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import file.Downloadable;
import json.JSONManager;
import network.DropApi;

public class Launcher {

	public static void main(String[] args) {
		
		try {
			DropApi drop = new DropApi(new URL("https://api.thedrop.pw/v1/files/2B72y69LKsYVP7YhAYpigHoq"));
			
			String json_str = drop.getJSONFromApi();
			
			JSONObject json = JSONManager.toJSON(json_str);
			
			List<Downloadable> vids = JSONManager.getVideos(json.getJSONArray("data"));
			
			
			vids.get(0).download();
			
			
		} catch (MalformedURLException e) {
			System.out.println("Unable to access api");
		}
	}

}
