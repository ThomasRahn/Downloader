package file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Video implements Downloadable {
	
	private String download_location = System.getProperty("user.dir") + "\\downloads\\";
	private boolean is_downloaded;
	
	private String url;
	private String file_name;
	private File file;
	
	private long size; 
	
	public Video(String url, String file_name){
		this.url = url;
		this.file_name = file_name;
		
		file = new File(download_location + file_name);
		
		is_downloaded = file.exists();
	}

	public void setSize(long size){
		this.size = size;
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
				            	update_progress((sum_count / size * 100.0));
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
			} 
		}
	}
	
	
	private void update_progress(double progress_percentage) {
	    final int width = 50; // progress bar width in chars

	    System.out.print("\r[");
	    int i = 0;
	    for (; i <= (int)(progress_percentage*width); i++) {
	      System.out.print(".");
	    }
	    for (; i < width; i++) {
	      System.out.print(" ");
	    }
	    System.out.print("]");
	  }
}
