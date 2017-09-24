package filters;

import java.util.List;

import file.Downloadable;

public class Filter {
	private List<String> filters; 
	
	public Filter(List<String> filters) {
		this.filters = filters;
	}
	
	public void addFilter(String filter){
		filters.add(filter);
	}
	
	public void removeFilter(String filter){
		for(int i = 0; i < filters.size(); i++){
			if(filters.get(i).equals(filter)){
				filters.remove(i);
				break;
			}
		}
	}
	
	public List<String> getFilters(){
		return filters;
	}
	
	public boolean validFilter(Downloadable file) {
		
		if(filters.size() == 0){
			return true;
		}
		String[] componenets = file.getName().split("\\.");
		
		
		for(String filter : filters){
			for(String comp : componenets){
				
				if(filter.toLowerCase().equals(comp.toLowerCase())){
					return true;
				}
			}
		}
		
		return false;
	}
	
}
