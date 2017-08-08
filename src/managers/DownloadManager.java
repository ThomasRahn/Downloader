package managers;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

import file.Video;

public class DownloadManager {

	public static Boolean Download(Video vid){
		
		boolean error = false;

		try {
			URL item_url = new  URL(vid.getUrl());
			BufferedInputStream in = null;
			FileOutputStream fout = null;
			    try {
			        in = new BufferedInputStream(item_url.openStream());
			        fout = new FileOutputStream(vid.getFilePath());
			        double sum_count = 0.0;
			        
			        final byte data[] = new byte[1024];
			        int count;
			        while ((count = in.read(data, 0, 1024)) != -1) {
			            fout.write(data, 0, count);
			            sum_count += count;
			            if (vid.getSize() > 0) {
			            	double percentage = (sum_count / vid.getSize() * 100.0);
			            	
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
			System.out.println(vid.getName() + " has finished downloading");
		}
		
		if(!error)
			vid.set_downloaded();
		
		return !error;
	}
}
