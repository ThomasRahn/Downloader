package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class DropApi {
	private URL url;
	
	public DropApi(URL url){
		this.url = url;
	}
	
	/*
	 * This function will perform the curl request and get the JSON from the API;
	 * 
	 * @param none
	 * @return String The api response or the error message
	 */
	public String getJSONFromApi(){
		//perform the curl to get the JSON from API
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
		    for (String line; (line = reader.readLine()) != null;) {
		        sb.append(line);
		    }
		}catch(IOException ioe){
			return ioe.getMessage();
		}
		
		return sb.toString();
	}
}
