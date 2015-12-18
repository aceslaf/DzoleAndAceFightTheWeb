package pages;

import java.io.IOException;
import java.util.LinkedList;

import Managers.LinksManager;

public class GeneralCrawler {

	private LinksManager linksManager = LinksManager.getInstance();

	private String page;
	private LinkedList<String> linksToCrawl = new LinkedList<String>();
	private LinkedList<String> crawledLinks = new LinkedList<String>();

	public GeneralCrawler(String page) {
		this.page = page;
		linksManager.crawlLinks(page, linksToCrawl, crawledLinks);
	}

}
