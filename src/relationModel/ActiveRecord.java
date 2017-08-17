package relationModel;

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
	
	public String getTableName() {
		return this.table_name;
	}
	
	public List<ActiveField> getFields() {
		return this.fields;
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
	
	public void create_structure(){
		
	}
	
	public void save(){
		
	}

	
	
	
}
