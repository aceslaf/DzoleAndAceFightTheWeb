package beans;

import java.util.LinkedList;
import java.util.Locale.Category;

public class PictureBean {

	private String pictureLink;
	private LinkedList<String> tags;
	private Category category;
	
	public PictureBean(String pictureLink, LinkedList<String> tags) {
		this.setPictureLink(pictureLink);
		this.setTags(tags);
	}
	

	public Category GetCategory()
	{
		return this.category;
	}
	
	public String getPictureLink() {
		return pictureLink;
	}

	private void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public LinkedList<String> getTags() {
		return tags;
	}

	private void setTags(LinkedList<String> tags) {
		this.tags = tags;
	}
	
	
}
