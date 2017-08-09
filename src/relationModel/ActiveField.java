package relationModel;

public class ActiveField {
	private String name;
	private Object obj;
	private String options;
	
	public ActiveField(String name, Object obj, String options){
		this.name = name;
		this.obj = obj;
		this.options = options;
	}
	
	public void setObject(Object obj){
		this.obj = obj;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Object getObject(){
		return this.obj;
	}
	
	public String getOptions(){
		return this.options;
	}
	
	public String getTypeString(){
		if(this.obj instanceof Double){
			return "DOUBLE";
		}else if (this.obj instanceof Integer){
			return "INT";
		}else if (this.obj instanceof String){
			return "VARCHAR";
		}else if (this.obj instanceof Boolean){
			return "BOOLEAN";
		} else {
			return "TEXT";
		}
		
		
	}
}
