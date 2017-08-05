package relationModel;

public class ActiveField {
	private String name;
	private Object obj;
	
	public ActiveField(String name, Object obj){
		this.name = name;
		this.obj = obj;
	}
	
	public void setObject(Object obj){
		this.obj = obj;
	}
	
	public String getName(){
		return this.name;
	}
}
