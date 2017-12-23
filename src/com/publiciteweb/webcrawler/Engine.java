package com.publiciteweb.webcrawler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Engine
{
	private Set<Element> hrefElements = Collections.synchronizedSet( new HashSet<Element>() );
	private HashSet<String> intialHrefs = new HashSet<String>();
	private HashSet<String> excludeHrefs = new HashSet<String>();
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

	public void search( String hrefDocument )
	{
		try
		{
			Document document = Jsoup.connect( hrefDocument ).get();
			if ( document != null )
			{
				// extract all href element of a document
				synchronized( hrefElements )
				{
					hrefElements.addAll( document.select( "a[href]" ) );
					if ( !hrefElements.isEmpty() )
					{
						for ( Element hrefElement : hrefElements )
						{
							// Extract url from of a href element
							String hyperlink = hrefElement.attr( "abs:href" );
							if ( hyperlink != null && !excludeHrefs.contains( hyperlink ) )
							{
								hrefElements.remove( hrefElement );
								for ( Stewart stewart : stewarts )
								{
									// If url is a valid
									if ( stewart.validateURL( hyperlink ) )
									{
										// Save document's body If the url is
										// valid to scraps
										if ( stewart.isToScrap() )
										{
											save( hyperlink, document.body().toString() );
										}
									}
								}
								search( hyperlink );
							}
						}
					}
				}
			}
		}catch( IOException e )
		{
			e.printStackTrace();
		}
	}

	public void addStewarts( Stewart stewart )
	{
		stewarts.add( stewart );
	}

	private synchronized void save( String href, String html )
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
