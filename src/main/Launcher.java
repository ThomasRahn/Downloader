package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import file.Downloadable;
import filters.Filter;
import managers.JSONManager;
import network.DropApi;

public class Launcher {

	public static void main(String[] args) {
		
		try {
			DropApi drop = new DropApi(new URL("https://api.thedrop.pw/v1/files/2B72y69LKsYVP7YhAYpigHoq"));
			
			String json_str = drop.getJSONFromApi();
			
			JSONObject json = JSONManager.toJSON(json_str);
			
			List<Downloadable> vids = JSONManager.getVideos(json.getJSONArray("data"));
			
			Filter filter = new Filter(new ArrayList<String>());
			filter.addFilter("whose");
			filter.addFilter("anyway");
			
			
			for(Downloadable d : vids){
				d.create_structure();
				
				d.store();
				if(filter.validFilter(d)){
					System.out.println("DOWNLOADED: " + d.getName());
					//d.download();
				} else {
					System.out.println("NOT DOWNLOADED: " + d.getName());
				}
			}
			    
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to access api" + e.getMessage());
		}
	}

}
