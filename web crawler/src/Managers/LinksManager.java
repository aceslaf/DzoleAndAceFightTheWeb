package Managers;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.nodes.Document;

public class LinksManager {
	
	private static LinksManager linksManager = null;
	
	protected LinksManager() {
		
	}
	
	public static LinksManager getInstance() {
		if(linksManager == null)  {
			return new LinksManager();
		}
		
		return linksManager;
	}
	
	
	private ConnectionManager connectionManager = ConnectionManager.getInstance();
	
	private IValidationManager validationManager = ValidationManager.getInstance();
	
	public void crawlLinks(String link, LinkedList<String> linksToCrawl, LinkedList<String> crawledLinks) {
		crawledLinks.add(link);
		System.out.println(link);
		Document doc= connectionManager.connect(link);
		
		if(doc != null) {
			String lines[] = doc.toString().split("\\r?\\n");
			for (String line : lines) {
				if (validationManager.isLink(line)) {
					try {
						String lineLink = getLink(line);
						if (!linksToCrawl.contains(lineLink) && !crawledLinks.contains(lineLink)) {
							System.out.println(lineLink);
							linksToCrawl.push(lineLink);
						}
					} catch (IndexOutOfBoundsException e) {
						e.printStackTrace();
					}
				}
			}
			
		} else {
			System.out.println("crawlLinksFail : " + link);
		}
		
		if (linksToCrawl.size() > 0) {
			String nextLink = linksToCrawl.pop();
			crawlLinks(nextLink, linksToCrawl, crawledLinks);
		} else {
			return;
		}
	}
	
	public String getLink(String line) {
		String link = "";
		try{
			link = line.substring(line.indexOf("http"), line.indexOf("\"", line.indexOf("http")));
		} catch(StringIndexOutOfBoundsException e) {
			System.out.println("GJOKO  : " + line);
		}
		
		return link;
	}
}
