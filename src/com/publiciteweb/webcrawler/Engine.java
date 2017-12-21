package com.publiciteweb.webcrawler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Engine
{

	private HashSet<String> pages = new HashSet<String>();
	private HashSet<String> intialHrefs = new HashSet<String>();
	private HashSet<Stewart> stewarts = new HashSet<Stewart>();
	private Statement stmt = null;

	public Engine()
	{}

	public Engine( String initalHref )
	{
		this.intialHrefs.add( initalHref );
	}

	public Engine( HashSet<String> intialHrefs )
	{
		this.intialHrefs = intialHrefs;
	}

	public void search()
	{
		for ( String intialHref : intialHrefs )
		{
			search( intialHref );
		}
	}

	public void search( String href )
	{
		try
		{
			Document document = Jsoup.connect( href ).get();
			if ( document != null )
			{
				save( href, document.body().toString() );
				HashSet<String> hyperlinks = getValidHyperlinks( document );
				if ( hyperlinks != null )
				{
					pages.addAll( hyperlinks );
					for ( String hyperlink : pages )
					{
						pages.remove( hyperlink );
						search( hyperlink );
					}
				}
			}
		}catch( IOException e )
		{
			e.printStackTrace();
		}
	}

	private HashSet<String> getValidHyperlinks( Document document )
	{
		Elements hrefElements = document.select( "a[href]" );
		HashSet<String> hyperlinks = new HashSet<String>();
		if ( hrefElements != null )
		{
			for ( Element hrefElement : hrefElements )
			{
				String href = hrefElement.attr( "abs:href" );
				if ( !href.isEmpty() && isValidHyperlink( href ) )
				{
					hyperlinks.add( href );
				}
			}
		}
		return hyperlinks;
	}

	private boolean isValidHyperlink( String hyperlink )
	{
		for ( Stewart stewart : stewarts )
			if ( stewart.validateURL( hyperlink ) )
			{
				return true;
			}
		return false;
	}

	public void addStewarts( Stewart stewart )
	{
		stewarts.add( stewart );
	}

	private void save( String href, String html )
	{
		html = html.replaceAll( "\\'", "" );
		String sqlString = "INSERT INTO htmlscrap (hyperlink, html ) VALUES ('" + href + "', '" + html + "' )";
		try
		{
			if ( stmt == null )
			{
				init();
			}
			int updateReturn = stmt.executeUpdate( sqlString );

		}catch( SQLException e )
		{
			e.printStackTrace();
		}
	}

	private void init()
	{
		try
		{
			Connection conn = DriverManager.getConnection( "jdbc:mysql://localhost/webCrawler?user=root&password=admin&useSSL=false&characterEncoding=UTF-8" );
			stmt = conn.createStatement();
		}catch( SQLException e )
		{
			e.printStackTrace();
		}
	}

}
