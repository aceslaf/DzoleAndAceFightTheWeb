package pages;

import java.util.List;

import Managers.IDbManager;
import Managers.IValidationManager;
import beans.PictureBean;

public class WaikikiMk extends BasePageSpecificCrawler implements ICrawler {

	public WaikikiMk(IDbManager dbManager, IValidationManager validationManager) {
		super(dbManager, validationManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PictureBean> Crawl() {
		// TODO Auto-generated method stub
		return null;
	}

}
