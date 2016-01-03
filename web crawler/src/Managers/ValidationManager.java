package Managers;

public class ValidationManager implements IValidationManager {

	public static IValidationManager validationManager = null;

	protected ValidationManager() {

	}

	public static IValidationManager getInstance() {
		if (validationManager == null) {
			return new ValidationManager();
		}

		return validationManager;
	}

	/* (non-Javadoc)
	 * @see Managers.IValidationManager#isLink(java.lang.String)
	 */
	@Override
	public boolean isLink(String line) {
		return line.indexOf("href") != -1 && line.indexOf("http") != -1 && line.indexOf("shockedchicken") != -1
				&& line.indexOf("wp-") == -1;
	}

	/* (non-Javadoc)
	 * @see Managers.IValidationManager#containsKeywords(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean containsKeywords(String url, String... keywords) {
		for (String keyword : keywords) {
			if (url.indexOf(keyword) == -1) {
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see Managers.IValidationManager#doesntContainKeywords(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean doesntContainKeywords(String url, String... keywords) {
		for (String keyword : keywords) {
			if (url.indexOf(keyword) != -1) {
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see Managers.IValidationManager#doesLinkExist(java.lang.String)
	 */
	@Override
	public boolean doesLinkExist(String url) {

		throw new NullPointerException();
	}

	/* (non-Javadoc)
	 * @see Managers.IValidationManager#doesPictureExist(java.lang.String)
	 */
	@Override
	public boolean doesPictureExist(String url) {
		throw new NullPointerException();
	}

}
