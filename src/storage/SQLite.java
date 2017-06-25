package storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite {
	private static SQLite sql_lite = null;
	
	Connection connection = null;
	
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
	
	public static SQLite getInstance(){
		if(sql_lite == null){
			sql_lite = new SQLite();
		}
		
		return sql_lite;
	}
	
	public void create_structure(){
		
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			
			statement.executeQuery("CREATE TABLE IF NOT EXISTS video (id INTEGER, path STRING, downloaded BOOLEAN);");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
