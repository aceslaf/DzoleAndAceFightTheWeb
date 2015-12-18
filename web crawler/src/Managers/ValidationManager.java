package Managers;

public class ValidationManager {

	public static ValidationManager validationManager = null;
	
	protected ValidationManager() {
		
	}
	
	public static ValidationManager getInstance() {
		if(validationManager == null) {
			return new ValidationManager();
		}
		
		return validationManager;
	}
	
	public boolean isLink(String line) {
		return line.indexOf("href") != -1 && line.indexOf("http") != -1 && line.indexOf("shockedchicken") != -1 && line.indexOf("wp-") == -1;
	}
	
	public boolean containsKeywords(String url, String... keywords) {
		for(String keyword : keywords) {
			if(url.indexOf(keyword) == -1) {
				return false;
			}
		}
		return true;
	}
	
	public boolean doesntContainKeywords(String url, String...keywords) {
		for(String keyword : keywords) {
			if(url.indexOf(keyword) != -1) {
				return false;
			}
		}
		return true;
	}
}
