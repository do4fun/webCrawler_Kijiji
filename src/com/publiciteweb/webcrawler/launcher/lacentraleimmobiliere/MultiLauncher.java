package com.publiciteweb.webcrawler.launcher.lacentraleimmobiliere;

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
		urls.add( "http://www.lacentraleimmobiliere.ca/proprietes.php" );
		urls.add( "http://www.lacentraleimmobiliere.ca/proprietes.php?page=5" );

		ExecutorService execute = Executors.newFixedThreadPool( 50 );
		for ( String url : urls )
		{
			Engine engine = new Engine( url );
			engine.setTableName( "lacentraleimmobiliere" );
			Engine.setExcludeHrefs( excludeHyperlinks );
			Stewart hyperlinkToScrapStewart = new Stewart( "http://www.lacentraleimmobiliere.ca/propriete", "maison" );
			hyperlinkToScrapStewart.setToScrap( true );
			engine.addStewarts( hyperlinkToScrapStewart );
			engine.addStewarts( new Stewart( "http://www.lacentraleimmobiliere.ca/proprietes.php?", "page" ) );
			EngineRunnable engineRunnable = new EngineRunnable( engine );
			execute.submit( engineRunnable );
		}

		execute.shutdown();
	}

}
