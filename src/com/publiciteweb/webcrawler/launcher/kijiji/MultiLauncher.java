package com.publiciteweb.webcrawler.launcher.kijiji;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.publiciteweb.webcrawler.Engine;
import com.publiciteweb.webcrawler.Stewart;
import com.publiciteweb.webcrawler.multithreads.EngineRunnable;

public class MultiLauncher
{
	public static void main( String[] args )
	{
		Set<Thread> threads = new HashSet<Thread>();
		Set<EngineRunnable> engineRunnables = new HashSet<EngineRunnable>();
		Set<String> urls = new HashSet<String>();

		urls.add( "https://www.kijiji.ca/b-autos-camions/quebec/new__used/c174l9001a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/abitibi-temiscamingue/new__used/c174l1700059a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/baie-comeau/new__used/c174l1700251a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/chaudiere-appalaches/new__used/c174l1700062a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/chibougamau-nord-du-quebec/new__used/c174l1700284a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/gaspesie/new__used/c174l1700066a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/granby/new__used/c174l1700253a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/laval-rive-nord/new__used/c174l1700278a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/longueuil-rive-sud/new__used/c174l1700279a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/ouest-de-lile-qc/new__used/c174l1700280a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/ville-de-montreal/new__used/c174l1700281a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/lanaudiere/new__used/c174l1700283a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/laurentides/new__used/c174l1700282a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/mauricie/new__used/c174l1700147a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/rimouski-bas-st-laurent/new__used/c174l1700250a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/saguenay-lac-saint-jean/new__used/c174l1700178a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/saint-hyacinthe/new__used/c174l1700151a49" );
		urls.add( "https://www.kijiji.ca/b-autos-camions/saint-jean-sur-richelieu/new__used/c174l1700252a49" );

		ExecutorService execute = Executors.newFixedThreadPool( 50 );
		for ( String url : urls )
		{
			Engine engine = new Engine( url );
			Stewart hyperlinkToScrapStewart = new Stewart( "https://www.kijiji.ca", "/v-autos-camions" );
			hyperlinkToScrapStewart.setToScrap( true );
			engine.addStewarts( hyperlinkToScrapStewart );
			Stewart hyperlinkToScrapStewart2 = new Stewart( "https://www.kijiji.ca", "/v-cars-trucks" );
			hyperlinkToScrapStewart.setToScrap( true );
			engine.addStewarts( hyperlinkToScrapStewart );
			engine.addStewarts( hyperlinkToScrapStewart2 );
			engine.addStewarts( new Stewart( "https://www.kijiji.ca", "/b-cars-trucks" ) );
			engine.addStewarts( new Stewart( "https://www.kijiji.ca", "/b-autos-camions" ) );
			EngineRunnable engineRunnable = new EngineRunnable( engine );
			execute.submit( engineRunnable );
		}

		execute.shutdown();
	}

}
