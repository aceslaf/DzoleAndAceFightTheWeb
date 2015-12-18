import Managers.ConnectionManager;
import Managers.LinksManager;
import Managers.PicturesManager;
import Managers.ValidationManager;
import pages.MakPrimat;

public class Main {
	
	private static ConnectionManager connectionManager = ConnectionManager.getInstance();
	
	private static LinksManager linksManager = LinksManager.getInstance();
	
	private static PicturesManager picturesManager = PicturesManager.getInstance();
	
	private static ValidationManager validationManager = ValidationManager.getInstance();
	
	

	public static void main(String[] args) {
		System.setProperty("http.agent", "");
		
		MakPrimat makPrimatPage = new MakPrimat();
	}
}