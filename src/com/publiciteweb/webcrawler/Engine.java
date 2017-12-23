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

public class Engine
{

	private HashSet<Element> hrefElements = new HashSet<Element>();
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

	public void search( String hrefDocument )
	{
		try
		{
			Document document = Jsoup.connect( hrefDocument ).get();
			if ( document != null )
			{
				// extract all href element of a document
				hrefElements.addAll( document.select( "a[href]" ) );
				if ( !hrefElements.isEmpty() )
				{
					for ( Element hrefElement : hrefElements )
					{
						// Extract url from of a href element
						String hyperlink = hrefElement.attr( "abs:href" );
						if ( hyperlink != null )
						{
							hrefElements.remove( hrefElement );
							for ( Stewart stewart : stewarts )
							{
								// If url is a valid
								if ( stewart.validateURL( hyperlink ) )
								{
									// Save document's body If the url is valid
									// to scraps
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
		}catch( IOException e )
		{
			e.printStackTrace();
		}
	}

	private boolean isValidHyperlink( String hyperlink )
	{
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
