package file;

import java.io.File;
import managers.DownloadManager;
import relationModel.ActiveRecord;

/*
 * This class is for videos and their information
 */
public class Video implements Downloadable {
	
	private ActiveRecord record;
	
	private String download_location = System.getProperty("user.dir") + "\\downloads\\";
	private boolean is_downloaded;
	private int id;
	private String url;
	private String file_name;
	private File file;
	
	private long size; 
	
	public Video(int id, String url, String file_name){
		this.id = id;
		this.url = url;
		this.file_name = file_name;
		
		file = new File(download_location + file_name);
		
		is_downloaded = file.exists();
		
		//register active record
		record = new ActiveRecord("video");
		record.registerField("id", this.id, "PRIMARY KEY");
		record.registerField("url", this.url);
		record.registerField("downloaded", this.is_downloaded);
		record.registerField("path", this.file.getPath());
		
	}

	public void setSize(long size){
		this.size = size;
	}
	
	public long getSize(){
		return this.size;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public String getFilePath(){
		return this.file.getAbsolutePath();
	}
	
	public int getId(){
		return this.id;
	}
	
	/*
	 * This method will store the video object into the video table.
	 */
	@Override
	public void store() {
		record.save();
	}
	
	/*
	 * @param none
	 * @return none
	 * 
	 * This method is used to download the file from the url and store is in the default location
	 * 
	 */
	@Override
	public void download() {
		DownloadManager.Download(this);
	}
	
	public void create_structure(){
		record.create_structure();
	}
	
	public void save(){
		record.save();
	}

	@Override
	public void set_downloaded() {
		record.updateField("downloaded", true);
		
		record.save();
	}

	@Override
	public String getName() {
		return file_name;
	}
	
	
}
