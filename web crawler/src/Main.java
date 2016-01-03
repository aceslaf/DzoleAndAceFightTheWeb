import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Managers.ConnectionManager;
import Managers.IDbManager;
import Managers.IValidationManager;
import Managers.LinksManager;
import Managers.PicturesManager;
import Managers.ValidationManager;
import beans.PictureBean;
import pages.ICrawler;
import pages.KaliopeMk;
import pages.MakPrimat;
import pages.Prcla;
import pages.WaikikiMk;

public class Main {
	
	private static ConnectionManager connectionManager = ConnectionManager.getInstance();
	
	private static LinksManager linksManager = LinksManager.getInstance();
	
	private static PicturesManager picturesManager = PicturesManager.getInstance();
	
	private static IValidationManager validationManager = ValidationManager.getInstance();
	
	

	public static void main(String[] args) {
		System.setProperty("http.agent", "");
		// Mock validation and dblayer ignore this.
		final Map<String,PictureBean> links= new HashMap<>();
		IDbManager dbManager = new IDbManager() {
			
			@Override
			public void AddPost(PictureBean post) {
				links.put(post.getPictureLink(), post);
			}
		};
		IValidationManager validation = new IValidationManager() {
			
			@Override
			public boolean isLink(String line) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean doesntContainKeywords(String url, String... keywords) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean doesPictureExist(String url) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean doesLinkExist(String url) {
				// TODO Auto-generated method stub
				return links.containsKey(url);
			}
			
			@Override
			public boolean containsKeywords(String url, String... keywords) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		
		//REal code starts here
		List<ICrawler> crawlers = new ArrayList<>();
		crawlers.add(new MakPrimat());
		crawlers.add(new WaikikiMk(dbManager,validation));
		crawlers.add(new KaliopeMk(dbManager,validation));
		crawlers.add(new Prcla(dbManager,validation));
		
		for (ICrawler iCrawler : crawlers) {
			iCrawler.Crawl();
		}
		
		//MakPrimat makPrimatPage = new MakPrimat();
	}
}