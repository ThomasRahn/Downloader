package main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import file.Downloadable;
import filters.Filter;
import managers.JSONManager;
import network.DropApi;
import storage.Database;
import storage.SQLite;

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
			
			
			Database db = SQLite.getInstance();
			
			for(Downloadable d : vids){
				d.create_structure(db.connection);
				
				d.store(db.connection);
				if(filter.validFilter(d)){
					System.out.println("DOWNLOADED: " + d.getName());
					//d.download();
				} else {
					System.out.println("NOT DOWNLOADED: " + d.getName());
				}
			}
			
//			File[] roots = File.listRoots();
//			for(int i = 0; i < roots.length ; i++){
//			    System.out.println("Root["+i+"]:" + roots[i]);
//			    Long bytes = roots[i].getFreeSpace();
//			    
//			    //convert to GBs
//			    bytes = bytes / 1024 / 1024 / 1024;
//			    System.out.println(bytes);
//			}
			    
			    
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to access api" + e.getMessage());
		}
	}

}
