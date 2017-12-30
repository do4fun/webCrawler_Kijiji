package com.publiciteweb.webcrawler.launcher.remax;

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
		Set<String> urls = new HashSet<String>();
		urls.add( "https://www.remax-quebec.com/fr/recherche/residentielle/resultats.rmx#listing" );
		urls.add( "http://www.remax-quebec.com/fr/recherche/residentielle/index.rmx?offset=100#listing" );
		urls.add( "http://www.remax-quebec.com/fr/recherche/residentielle/index.rmx?offset=200#listing" );
		urls.add( "http://www.remax-quebec.com/fr/recherche/residentielle/index.rmx?offset=300#listing" );
		urls.add( "http://www.remax-quebec.com/fr/recherche/residentielle/index.rmx?offset=400#listing" );

		ExecutorService execute = Executors.newFixedThreadPool( 50 );
		for ( String url : urls )
		{
			Engine engine = new Engine( url );
			engine.setTableName( "remax" );
			Engine.setExcludeHrefs( excludeHyperlinks );
			Stewart hyperlinkToScrapStewart = new Stewart( "http://www.remax-quebec.com/fr", "/fr/maison-a-vendre" );
			hyperlinkToScrapStewart.setToScrap( true );
			engine.addStewarts( hyperlinkToScrapStewart );
			engine.addStewarts( new Stewart( "http://www.remax-quebec.com/fr", "/recherche/residentielle/index.rmx?offset=" ) );
			EngineRunnable engineRunnable = new EngineRunnable( engine );
			execute.submit( engineRunnable );
		}

		execute.shutdown();
	}

}
