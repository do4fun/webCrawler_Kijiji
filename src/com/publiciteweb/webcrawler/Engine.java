package com.publiciteweb.webcrawler;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Engine {
	
	HashSet<String> pages = new HashSet<String>();
	String intialHref;
	
	public Engine(){}

	public Engine(String initalHref)
	{
		this.intialHref = initalHref;
	}
	
	public void search(){
		search(intialHref);
	}

	public void search( String href )
	{
		try 
		{
			Document document = Jsoup.connect(href).get();
			HashSet<String> hyperlinks = getHyperlinks(document);
			pages.addAll(hyperlinks);
			for(String hyperlink : pages)
			{
				pages.remove(hyperlink);
				search(hyperlink);
			}
		}
		catch (IOException e)
		{
			crawlerLog.exception(e);
		}
	}

	public HashSet<String> getHyperlinks(Document document)
	{
		Elements hrefElements = document.select("a[href]");
		HashSet<String> hyperlinks = new HashSet<String>();
		if( hrefElements != null )
		{
			for(Element hrefElement : hrefElements )
			{
				if(!hrefElement.attr("abs:href").isEmpty())
				{
					hyperlinks.add(hrefElement.attr("abs:href"));
				}
			}
		}
		return hyperlinks;
	}

	public static void main(String[] args) 
	{
		Engine engine = new Engine();
		engine.search("https://www.kijiji.ca/b-autos-camions/quebec/new__used/c174l9001a49");
		
		
	}
}
