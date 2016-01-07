import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import Managers.ConnectionManager;
import Managers.IDbManager;
import Managers.IValidationManager;
import Managers.LinksManager;
import Managers.PicturesManager;
import Managers.ValidationManager;
import beans.PictureBean;
import pages.BasePageSpecificCrawler;
import pages.CrawlResult;
import pages.ICrawler;
import pages.KaliopeMk;
import pages.MakPrimat;
import pages.Prcla;
import pages.WaikikiMk;

public class Main {
	final static Logger logger = Logger.getLogger(Main.class);
	private final static long MINIMUM_MILISEC_BETWEEN_REQUESTS=300;
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

			@Override
			public void AddPosts(List<PictureBean> post) {
				post.stream().forEach(p->links.put(p.getPictureLink(), p));
				post.stream().map(p->{
					return String.format("%s %s \n %s \n", p.getPrice(),p.getDescription(),p.getPictureLink()+p.getOriginalDestinationLink());
				})
				.forEach(s->logger.info(s));
				
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
		crawlers.add(new MakPrimat(connectionManager, dbManager,validation));
//		crawlers.add(new WaikikiMk(dbManager,validation));
//		crawlers.add(new KaliopeMk(dbManager,validation));
//		crawlers.add(new Prcla(dbManager,validation));
//		
		
		while(crawlers.size()>0){
			long startTime=System.nanoTime();
			
			executeCrawlCycle(crawlers, dbManager);
			
			long endTime=System.nanoTime();
			long elapsedTime = endTime-startTime;
			if(elapsedTime<MINIMUM_MILISEC_BETWEEN_REQUESTS){
				try {
					Thread.sleep(MINIMUM_MILISEC_BETWEEN_REQUESTS-elapsedTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					logger.error("sleep failed us", e);
					e.printStackTrace();
				}
			}
		}
		
		//MakPrimat makPrimatPage = new MakPrimat();
	}
	public static void executeCrawlCycle(List<ICrawler> crawlers,IDbManager dbManager){
		ICrawler crawler=null;
		for (int i = crawlers.size()-1; i >=0; i--) {
			try {
				crawler= crawlers.get(i);
				CrawlResult result =crawler.executeCrawlCycle();				
				dbManager.AddPosts(result.picBeans);
				if(result.shouldEnd){
					crawlers.remove(i);
				}
			} catch (Exception e) {
				// TODO: handle exception
				// Also we should probably diferentiate between io exceptions and npts (logic based and checked as in db crashed)
				logger.error(String.format("Error for crawler {%s} : %s",crawler!=null?crawler.getClass().toString():"crawler is null",e));
			}
			
		}
	}
}