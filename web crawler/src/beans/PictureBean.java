package beans;

import java.util.LinkedList;

public class PictureBean {

	private String pictureLink;
	private LinkedList<String> tags;
	
	public PictureBean(String pictureLink, LinkedList<String> tags) {
		this.setPictureLink(pictureLink);
		this.setTags(tags);
	}

	private String getPictureLink() {
		return pictureLink;
	}

	private void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	private LinkedList<String> getTags() {
		return tags;
	}

	private void setTags(LinkedList<String> tags) {
		this.tags = tags;
	}
	
	
}
