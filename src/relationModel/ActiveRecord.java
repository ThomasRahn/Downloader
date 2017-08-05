package relationModel;

import java.util.ArrayList;
import java.util.List;

public class ActiveRecord {
	
	List<ActiveField> fields = null;
	
	public ActiveRecord() {
		this.fields = new ArrayList<ActiveField>();
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
	
	
	public void createStructure(){
		
	}
	
	public void save(){
		
	}
	
}
