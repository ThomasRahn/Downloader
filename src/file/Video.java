package file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import managers.DisplayManager;
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
		record = new ActiveRecord();
		record.registerField("id", this.id);
		record.registerField("url", this.url);
		record.registerField("downloaded", this.is_downloaded);
		record.registerField("pah", this.file.getPath());
		
	}

	public void setSize(long size){
		this.size = size;
	}
	
	public int getId(){
		return this.id;
	}
	
	/*
	 * This method will store the video object into the video table.
	 */
	@Override
	public void store(Connection connection) {
		
		
		//insert or ignore into (in the case of duplication)
		try (PreparedStatement ps = connection.prepareStatement("INSERT OR IGNORE INTO video (id, path, downloaded, url) VALUES (?,?,?,?)")){
			ps.setInt(1, this.id);
			ps.setString(2, this.file.getPath());
			ps.setBoolean(3, this.is_downloaded);
			ps.setString(4, this.url);
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		if(!is_downloaded){
			boolean error = false;

			try {
				URL item_url = new  URL(url);
				BufferedInputStream in = null;
				FileOutputStream fout = null;
				    try {
				        in = new BufferedInputStream(item_url.openStream());
				        fout = new FileOutputStream(file.getAbsolutePath());
				        double sum_count = 0.0;
				        
				        final byte data[] = new byte[1024];
				        int count;
				        while ((count = in.read(data, 0, 1024)) != -1) {
				            fout.write(data, 0, count);
				            sum_count += count;
				            if (size > 0) {
				            	double percentage = (sum_count / size * 100.0);
				            	
				            	DisplayManager.display_progress_bar(percentage);
				            }
				        }
				    } finally {
				        if (in != null) {
				            in.close();
				        }
				        if (fout != null) {
				            fout.close();
				        }
				    }
				
			} catch(Exception e ){
				//log exception
				System.out.println(e.getMessage());
				error = true;
				
			} finally {
				System.out.println(this.file_name + " has finished downloading");
			}
			
			if(!error)
				this.set_downloaded();
		}
	}
	
	public void create_structure(Connection connection){
		
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.
			
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS video (id PRIMARY KEY, path STRING, downloaded BOOLEAN, url STRING)");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
}
