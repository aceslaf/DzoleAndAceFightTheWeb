package pages;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import Managers.ConnectionManager;
import Managers.IDbManager;
import Managers.IValidationManager;
import Managers.ValidationManager;
import beans.PictureBean;

public class MakPrimat extends BasePageSpecificCrawler {

	

	private String page = "http://makprimat.com.mk/store/";
	private ArrayList<PictureBean> pictures=new ArrayList<>();
	private ArrayList<String> linkToCrawl=new ArrayList<>();

	

	public MakPrimat(ConnectionManager connectionManager,IDbManager dbManager, IValidationManager validation) {
		super(dbManager, validation);
		List<CategoryPage> subPages = getCategoryPages();
		subPages.add(new CategoryPage("http://makprimat.com.mk/store/men/trousers/", 
								 Arrays.asList(Category.Man,Category.Throusers)));
		subPages.add(new CategoryPage("http://makprimat.com.mk/store/men/accessories/belts-2/", 
				 Arrays.asList(Category.Man,Category.Accesories,Category.Belts)));
		subPages.add(new CategoryPage("http://makprimat.com.mk/store/raincoats/", 
				 Arrays.asList(Category.Man,Category.Raincoats)));
		subPages.add(new CategoryPage("http://makprimat.com.mk/store/men/blazers/", 
				 Arrays.asList(Category.Man,Category.Blazers)));
		subPages.add(new CategoryPage("http://makprimat.com.mk/store/men/suits/", 
				 Arrays.asList(Category.Man,Category.Suits)));
		subPages.add(new CategoryPage("http://makprimat.com.mk/store/men/shirts/", 
				 Arrays.asList(Category.Man,Category.Shirts)));
		subPages.add(new CategoryPage("http://makprimat.com.mk/store/men/knitwear/", 
				 Arrays.asList(Category.Man,Category.Knitwear)));
		subPages.add(new CategoryPage("http://makprimat.com.mk/store/men/accessories/scarves-2/", 
				 Arrays.asList(Category.Man,Category.Accesories,Category.Scarves)));
		subPages.add(new CategoryPage("http://makprimat.com.mk/store/men/accessories/ties-and-bow-ties/", 
				 Arrays.asList(Category.Man,Category.Accesories,Category.Ties)));
		subPages.add(new CategoryPage("http://makprimat.com.mk/store/men/accessories/wallets/", 
				 Arrays.asList(Category.Man,Category.Accesories,Category.Wallets)));
		
	}

	private  String parcePrice(Element elem)
	{
		Elements priceElems = elem.select(".naslov p");
		
		if(priceElems.size()<=0){
			throw new NullPointerException("picture has no price to be found");
		} 
		String price ="0";
		for (Element priceElem : priceElems) {
			if(priceElem.html().trim().length()>0){
				price = priceElem.html();
			}
		}
		return price;
	}
	
	private  Stream<PictureBean> parseItem(Element elem)
	{
		//Find price and description
		String price = parcePrice(elem);
		String description = elem.select(".naslov h3").get(0).html();
		
		// Each product has multiple pictures with a new color. I decided to let them exist like separate posts.
		// This creates a separate picture bean with the same price and description like the main one.
		ArrayList<PictureBean> result = new ArrayList<>();
		Elements pics=elem.select("img");
		return Utl.ToStream(pics)
					.map(img->{
					   return (String)img.attr("src");	
					})
					.distinct() //Most of the imgs are displayed twice once as a thumbnail and once when selected
					.map(imgLink->{
						return new PictureBean()
						  .setPrice(price)
						  .setDescription(description)
						  .setPictureLink(imgLink);
					});
	}
	
	@Override
	public Stream<PictureBean> crawlSinglePage(CategoryPage subPage) {
		Document doc = subPage.downloadDocument();
		Elements buyableItems = doc.select(".celo");
		
		//Page is not in the expected format
		if(buyableItems==null || buyableItems.size()<=0){
			throw new NullPointerException("Page does not have any items, or format was changed");
		}
		
		
		return 	Utl.ToStream(buyableItems)
				   .flatMap(item->{
							  return Utl.tryExec(this::parseItem,item);   
						   })
				   		   .map(picBean->{
								//add common data
							    subPage.Categories.forEach(c->picBean.addCategory(c));
							    subPage.Tags.forEach(t->picBean.addTag(t));
							    return picBean.setOriginalDestinationLink(subPage.Url);
						   });	
		}

	private void getLinks() {
		Document doc = getConnectionManager().connect(page);
		if (doc == null) {
			return;
		}
		Element menu = doc.getElementById("menu-menu1-1");
		Elements links = menu.select("a[href]");

		for (Element link : links) {
			String linkUrl = link.attr("href");
			if (getValidationManager().containsKeywords(linkUrl, "makprimat")
					&& getValidationManager().doesntContainKeywords(linkUrl, "about", "#", "contact", "\\/store\\/store\\/")) {
				System.out.println(linkUrl);
			}
		}
	}

	


}
