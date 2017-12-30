package com.publiciteweb.webcrawler.launcher.sutton;

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
		urls.add( "https://www.suttonquebec.com/fr/proprietes-a-vendre.html?regionCode=11&choixTypeRecherche=1" );
		urls.add( "https://www.suttonquebec.com/fr/proprietes-a-vendre.html?regionCode=12&choixTypeRecherche=1" );
		urls.add( "https://www.suttonquebec.com/fr/proprietes-a-vendre.html?regionCode=13&choixTypeRecherche=1" );
		urls.add( "https://www.suttonquebec.com/fr/proprietes-a-vendre.html?regionCode=14&choixTypeRecherche=1" );
		urls.add( "https://www.suttonquebec.com/fr/proprietes-a-vendre.html?regionCode=15&choixTypeRecherche=1" );
		urls.add( "https://www.suttonquebec.com/fr/proprietes-a-vendre.html?regionCode=16&choixTypeRecherche=1" );

		ExecutorService execute = Executors.newFixedThreadPool( 50 );
		for ( String url : urls )
		{
			Engine engine = new Engine( url );
			engine.setTableName( "sutton" );
			Engine.setExcludeHrefs( excludeHyperlinks );
			Stewart hyperlinkToScrapStewart = new Stewart( "https://www.suttonquebec.com/fr", "inscription/maison" );
			hyperlinkToScrapStewart.setToScrap( true );
			engine.addStewarts( hyperlinkToScrapStewart );
			engine.addStewarts( new Stewart( "https://www.suttonquebec.com/fr", "page" ) );
			EngineRunnable engineRunnable = new EngineRunnable( engine );
			execute.submit( engineRunnable );
		}

		execute.shutdown();
	}

}
