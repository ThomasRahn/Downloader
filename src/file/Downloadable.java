package file;

/*
 * Downloadable interface for objects that can be downloaded and stored locally
 */
public interface Downloadable {
	
	public String getName();
	
	public void download();
	
	public void store();
	
	public void create_structure();
	
	public void set_downloaded();
}
