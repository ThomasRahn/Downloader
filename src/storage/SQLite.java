package storage;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import relationModel.ActiveField;
import relationModel.ActiveRecord;

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
	
	@Override
	public void createStructure(ActiveRecord record){
		String sql_query = "CREATE TABLE IF NOT EXISTS " + record.getTableName() + " (";
		String separator = "";
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			
			for(ActiveField f : record.getFields()){
				sql_query += separator + f.getName() + " " + f.getTypeString() + " " + f.getOptions();
				separator = ",";
			}
			
			sql_query += " )";
			
			statement.executeUpdate(sql_query);
		} catch(SQLException sqle){
			sqle.getMessage();
		}
	}
	
	@Override
	public void save(ActiveRecord record){
		try{
			List<ActiveField> fields = record.getFields();
			
			String sql_query = "INSERT OR REPLACE INTO " + record.getTableName() + " ( ";
			int counter = 0;
			String separator = "";
			
			for(ActiveField f : fields){
				counter++;
				sql_query += separator + f.getName();
				separator = ",";
			}
			
			sql_query += " ) VALUES ( ";
			
			separator = "";
			for(int i = 0; i < counter; i++){
				sql_query += separator + "?";
				separator = ",";
			}
			
			sql_query += ")";
			
			PreparedStatement statement = connection.prepareStatement(sql_query);
			statement.setQueryTimeout(30);
			
			for(int j = 0; j < fields.size(); j++){
				statement.setObject(j+1, fields.get(j).getObject());
			}
			
			statement.execute();
		}catch(SQLException sqle){
			sqle.getMessage();
		}
	}
}
