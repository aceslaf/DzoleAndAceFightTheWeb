package beans;

import java.util.HashSet;
import java.util.LinkedList;

import pages.Category;

public class PictureBean {

	private HashSet<String> tags = new HashSet<>();
	private HashSet<Category> categories  = new HashSet<>();
	private String pictureLink;
	private String price;
	private String description;
	private String originalDestinationLink;
	
	public PictureBean () {
		super();
		tags = new HashSet<>();
		categories  = new HashSet<>();
	}
	
	public PictureBean(String pictureLink, LinkedList<String> tags) {
		this.setPictureLink(pictureLink);
		tags.stream().forEach(t->this.tags.add(t));
	}
	
	public String getOriginalDestinationLink() {
		return originalDestinationLink;
	}
	public HashSet<Category> getCategories()
	{
		return this.categories;
	}
	public String getPictureLink() {
		return pictureLink;
	}
	public HashSet<String> getTags() {
		return tags;
	}
	public String getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public PictureBean addTag(String tag)
	{
		tags.add(tag);
		return this;
	}
	
	public PictureBean setOriginalDestinationLink(String originalDestinationLink) {
		this.originalDestinationLink = originalDestinationLink;
		return this;
	}

	public PictureBean setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public PictureBean addCategory(Category asd) {
		this.categories.add(asd);
		return this;
	}
	
	public PictureBean setPrice(String price) {
		this.price = price;
		return this;
	}
	
	public PictureBean setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
		return this;
	}
	
}
