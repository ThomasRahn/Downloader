package storage;

import java.sql.DriverManager;

public class SQLite extends Database{
	private static Database sql_lite = null;
	
	
	private SQLite() {
		try{
			// load the sqlite-JDBC driver using the current class loader
		    Class.forName("org.sqlite.JDBC");
			
			// create a database connection
		     connection = DriverManager.getConnection("jdbc:sqlite:downloader.db");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Database getInstance(){
		if(sql_lite == null){
			sql_lite = new SQLite();
		}
		
		return sql_lite;
	}
	
	
}
