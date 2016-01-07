package pages;

import java.util.HashSet;
import java.util.List;

import org.jsoup.nodes.Document;

import Managers.ConnectionManager;

public class CategoryPage{
	public CategoryPage(String url, List<Category> categories) {
		super();
		Url = url;
		this.Categories = new HashSet<>();
		this.Tags = new HashSet<>();
		categories.stream().forEach(c->Categories.add(c));
	}
	public CategoryPage(String url, List<Category> categories,List<String> tags) {
		this(url,categories);
		tags.forEach(t->this.Tags.add(t));
	}
	public String Url;
	public HashSet<Category> Categories ;
	public HashSet<String> Tags;
	
	public Document downloadDocument(){
		return ConnectionManager.getInstance().connect(Url);
	};
}
