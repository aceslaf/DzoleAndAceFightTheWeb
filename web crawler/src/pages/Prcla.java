package pages;

import java.util.List;

import Managers.IDbManager;
import Managers.IValidationManager;
import beans.PictureBean;

public class Prcla extends BasePageSpecificCrawler implements ICrawler{

	public Prcla(IDbManager dbManager, IValidationManager validationManager) {
		super(dbManager, validationManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PictureBean> Crawl() {
		// TODO Auto-generated method stub
		return null;
	}

}
