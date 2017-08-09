package relationModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ActiveRecord {
	
	private List<ActiveField> fields = null;
	private String table_name;  
	
	
	
	public ActiveRecord(String table_name) {
		this.fields = new ArrayList<ActiveField>();
		this.table_name = table_name;
	}
	
	public void registerField(String name, Object obj){
		this.registerField(name, obj, "");
	}
	
	public void registerField(String name, Object obj, String options){
		fields.add(new ActiveField(name, obj, options));
	}
	
	public void updateField(String name, Object obj){
		for(ActiveField f : fields){
			if(f.getName().equals(name)){
				f.setObject(obj);
			}
		}
	}
	
	
	public void createStructure(Connection connection){
		String sql_query = "CREATE TABLE IF NOT EXISTS " + table_name + " (";
		String separator = "";
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			
			for(ActiveField f : fields){
				sql_query += separator + f.getName() + " " + f.getTypeString() + " " + f.getOptions();
				separator = ",";
			}
			
			sql_query += " )";
			
			statement.executeUpdate(sql_query);
		} catch(SQLException sqle){
			sqle.getMessage();
		}
	}
	
	public void save(Connection connection){
		try{
			String sql_query = "INSERT OR REPLACE INTO " + table_name + " ( ";
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
