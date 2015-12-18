package Managers;

public class PicturesManager {
	
	private static PicturesManager picturesManager = null;
	
	protected PicturesManager() {
		
	}
	
	public static PicturesManager getInstance() {
		if(picturesManager == null) {
			return new PicturesManager();
		}
		
		return picturesManager;
	}

}
