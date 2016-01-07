package pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import Managers.ConnectionManager;
import Managers.IDbManager;
import Managers.IValidationManager;
import beans.PictureBean;

public class BasePageSpecificCrawler implements ICrawler{
	private ConnectionManager connectionManager = ConnectionManager.getInstance();
	private IDbManager dbManager;
	private IValidationManager validationManager;
	private List<CategoryPage> pages = new ArrayList<>();
	

	public BasePageSpecificCrawler(IDbManager dbManager, IValidationManager validationManager) {
		super();
		this.dbManager = dbManager;
		this.validationManager = validationManager;
		this.pages= new ArrayList<>();
	}
	
	public CrawlResult executeCrawlCycle(){
		Iterator<CategoryPage> pageIterator = pages.iterator();
		CrawlResult result = new CrawlResult();
		if(pageIterator.hasNext()){
			crawlSinglePage(pageIterator.next())
							.filter(picBean->{return picBean!=null;})
							.forEach(picBean->result.picBeans.add(picBean));
			result.shouldEnd=false;
		}else{
			result.shouldEnd=true;
		}
		return result;		
	}
	
    protected Stream<PictureBean> crawlSinglePage(CategoryPage subPage)
    {
    	throw new NullPointerException();
    }
	protected ConnectionManager getConnectionManager(){return connectionManager;}
	protected List<CategoryPage> getCategoryPages(){
		return this.pages;
	}
	public IDbManager getDbManager() {
		return dbManager;
	}

	public void setDbManager(IDbManager dbManager) {
		this.dbManager = dbManager;
	}

	public IValidationManager getValidationManager() {
		return validationManager;
	}

	public void setValidationManager(IValidationManager validationManager) {
		this.validationManager = validationManager;
	}
	
	

}
