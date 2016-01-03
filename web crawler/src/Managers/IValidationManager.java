package Managers;

public interface IValidationManager {

	boolean isLink(String line);

	boolean containsKeywords(String url, String... keywords);

	boolean doesntContainKeywords(String url, String... keywords);

	boolean doesLinkExist(String url);

	boolean doesPictureExist(String url);

}