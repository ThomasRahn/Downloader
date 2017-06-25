package managers;

public class DisplayManager {

	/*
	 * @param double progess_percentage 
	 * 
	 * This function is to display a progress bar for downloading an object.
	 */
	public static void display_progress_bar(double progress_percentage) {
		final int width = 50; // progress bar width in chars
		
		System.out.print("\r[");
		
		int i = 0;
		for (; i <= (int)(progress_percentage / 2); i++) {
			System.out.print(".");
		}
		
		for (; i < width; i++) {
			System.out.print(" ");
		}
		
		System.out.print("]");
	}
}
