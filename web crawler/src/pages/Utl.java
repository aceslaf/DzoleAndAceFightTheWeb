package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.function.*;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Utl {

	final static Logger logger = Logger.getLogger(Utl.class); 
	public static List<Element> ToList(Elements elems)
	{
		ArrayList<Element> asList = new ArrayList<>();
		for (Element element : elems) {
			asList.add(element);
		}
		return asList;
	}
	
	public static Stream<Element> ToStream(Elements elems)
	{
		return ToList(elems).stream();
	}
	
	public static <InputType,ReturnType> ReturnType tryExec(Function<InputType, ReturnType> func,InputType arg ){
		try{
			return func.apply(arg);
		}catch (Exception e){
			//TODO log
			e.printStackTrace();
			logger.error(String.format("error with safe lambda execution "), e);
		}
		return null;
	}
	public static <InputType> void tryExec(Consumer<InputType> func,InputType arg ){
		try{
			 func.accept(arg);
		}catch (Exception e){
			//TODO log
			e.printStackTrace();
			logger.error(String.format("error with safe lambda execution "), e);
		}
	}
}
