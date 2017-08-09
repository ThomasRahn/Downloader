package file;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import managers.DownloadManager;
import relationModel.ActiveRecord;
import storage.Database;

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
	public void store(Connection connection) {
		record.save(connection);
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
	
	public void create_structure(Connection connection){
		record.createStructure(connection);
	}
	
	public void save(Connection connection){
		record.save(connection);
	}

	@Override
	public void set_downloaded() {
		Database db = Database.getInstance();
		
		//insert or ignore into (in the case of duplication)
		try (PreparedStatement ps = db.connection.prepareStatement("UPDATE video SET downloaded = 1 WHERE id = ?")){
			ps.setInt(1, this.id);
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		return file_name;
	}
	
	
}
