package main;

import org.json.*;

public class Launcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject("{ \"test\" : 123 }");
		
		int test = json.getInt("test");
		System.out.println(test);
	}

}
