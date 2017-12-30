package com.publiciteweb.webcrawler.launcher.propriodirect;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.publiciteweb.webcrawler.Engine;
import com.publiciteweb.webcrawler.Stewart;
import com.publiciteweb.webcrawler.multithreads.EngineRunnable;

public class MultiLauncher
{
	private static HashSet<String> excludeHyperlinks = new HashSet<String>();

	public static void main( String[] args )
	{
		/*
		 * 
		 * javax.net.ssl.SSLHandshakeException:
		 * sun.security.validator.ValidatorException: PKIX path building failed:
		 * sun.security.provider.certpath.SunCertPathBuilderException: unable to
		 * find valid certification path to requested target
		 * 
		 */
		Set<String> urls = new HashSet<String>();
		urls.add( "https://propriodirect.com/recherche/" );
		urls.add( "https://propriodirect.com/recherche/?page=5&qty_results=30" );
		urls.add( "https://propriodirect.com/recherche/?page=10&qty_results=30" );
		urls.add( "https://propriodirect.com/recherche/?page=15&qty_results=30" );
		urls.add( "https://propriodirect.com/recherche/?page=20&qty_results=30" );
		urls.add( "https://propriodirect.com/recherche/?page=25&qty_results=30" );
		urls.add( "https://propriodirect.com/recherche/?page=30&qty_results=30" );
		urls.add( "https://propriodirect.com/recherche/?page=35&qty_results=30" );

		ExecutorService execute = Executors.newFixedThreadPool( 50 );
		for ( String url : urls )
		{
			Engine engine = new Engine( url );
			engine.setTableName( "propriodirect" );
			Engine.setExcludeHrefs( excludeHyperlinks );
			Stewart hyperlinkToScrapStewart = new Stewart( "https://propriodirect.com", "maison" );
			hyperlinkToScrapStewart.setToScrap( true );
			engine.addStewarts( hyperlinkToScrapStewart );
			engine.addStewarts( new Stewart( "https://propriodirect.com/", "recherche" ) );
			EngineRunnable engineRunnable = new EngineRunnable( engine );
			execute.submit( engineRunnable );
		}

		execute.shutdown();
	}

}
