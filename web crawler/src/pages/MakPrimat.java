package pages;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Managers.ConnectionManager;
import Managers.IValidationManager;
import Managers.ValidationManager;
import beans.PictureBean;

public class MakPrimat implements ICrawler{

	private ConnectionManager connectionManager = ConnectionManager.getInstance();

	private IValidationManager validationManager = ValidationManager.getInstance();

	private String page = "http://makprimat.com.mk/store/";
	private ArrayList<PictureBean> pictures;
	private ArrayList<String> linkToCrawl;

	public MakPrimat() {
		pictures = new ArrayList<PictureBean>();
		linkToCrawl = new ArrayList<>();

		getLinks();
	}

	private void getLinks() {
		Document doc = connectionManager.connect(page);
		if (doc == null) {
			return;
		}
		Element menu = doc.getElementById("menu-menu1-1");
		Elements links = menu.select("a[href]");

		for (Element link : links) {
			String linkUrl = link.attr("href");
			if (validationManager.containsKeywords(linkUrl, "makprimat")
					&& validationManager.doesntContainKeywords(linkUrl, "about", "#", "contact", "\\/store\\/store\\/")) {
				System.out.println(linkUrl);
			}
		}
	}

	@Override
	public List<PictureBean> Crawl() {
		throw new NullPointerException();
	}

}
