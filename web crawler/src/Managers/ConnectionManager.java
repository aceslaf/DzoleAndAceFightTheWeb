package Managers;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ConnectionManager {

	public static ConnectionManager connectionManager = null;
	
	protected ConnectionManager() {
		
	}
	
	public static ConnectionManager getInstance() {
		if(connectionManager == null) {
			return new ConnectionManager();
		}
		
		return connectionManager;
	}
	
	
	public Document connect(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).timeout(0).get();
		} catch (NullPointerException e) {
			System.out.println("connect  = " + url);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("connect  = " + url);
			e.printStackTrace();
		} 
		return doc;
	}
}
