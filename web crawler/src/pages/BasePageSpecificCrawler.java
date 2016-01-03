package pages;

import Managers.IDbManager;
import Managers.IValidationManager;

public class BasePageSpecificCrawler {
	private IDbManager dbManager;
	private IValidationManager validationManager;

	public BasePageSpecificCrawler(IDbManager dbManager, IValidationManager validationManager) {
		super();
		this.dbManager = dbManager;
		this.validationManager = validationManager;
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
