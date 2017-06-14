package file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import managers.DisplayManager;

public class Video implements Downloadable {
	
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
	}

	public void setSize(long size){
		this.size = size;
	}
	
	public int getId(){
		return this.id;
	}
	
	@Override
	public void download() {
		if(!is_downloaded){
			System.out.println(download_location);
			
			try {
				URL item_url = new  URL(url);
				BufferedInputStream in = null;
				FileOutputStream fout = null;
				    try {
				        in = new BufferedInputStream(item_url.openStream());
				        fout = new FileOutputStream(file_name);
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
			} finally {
				System.out.println(this.file_name + " has finished downloading");
			}
		}
	}
}
