package main;

import java.net.URL;
import java.util.List;
import org.json.JSONObject;
import file.Downloadable;
import managers.JSONManager;
import network.DropApi;
import storage.SQLite;

public class Launcher {

	public static void main(String[] args) {
		
		try {
			DropApi drop = new DropApi(new URL("https://api.thedrop.pw/v1/files/2B72y69LKsYVP7YhAYpigHoq"));
			
			String json_str = drop.getJSONFromApi();
			
			JSONObject json = JSONManager.toJSON(json_str);
			
			List<Downloadable> vids = JSONManager.getVideos(json.getJSONArray("data"));
			
			
			for(Downloadable d : vids){
				d.create_structure(SQLite.getInstance().connection);
				
				d.store();
				//d.download();
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to access api" + e.getMessage());
		}
	}

}
