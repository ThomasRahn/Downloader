package relationModel;

import java.sql.Connection;
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
		fields.add(new ActiveField(name, obj));
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
				sql_query += separator + f.getName() + " " + f.getTypeString();
				separator = ",";
			}
			
			sql_query += " )";
			
			statement.executeUpdate(sql_query);
		} catch(SQLException sqle){
			sqle.getMessage();
		}
	}
	
	public void save(){
		
	}
	
}
