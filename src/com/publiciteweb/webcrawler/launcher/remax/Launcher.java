package com.publiciteweb.webcrawler.launcher.remax;

import com.publiciteweb.webcrawler.Engine;
import com.publiciteweb.webcrawler.Stewart;

public class Launcher
{

	public static void main( String[] args )
	{

		Engine engine = new Engine( "https://www.remax-quebec.com/fr/recherche/residentielle/resultats.rmx#listing" );
		Stewart hyperlinkToScrapStewart = new Stewart( "https://www.remax-quebec.com", "/fr/maison-a-vendre" );
		hyperlinkToScrapStewart.setToScrap( true );
		engine.addStewarts( hyperlinkToScrapStewart );
		engine.addStewarts( new Stewart( "https://www.remax-quebec.com", "/fr/recherche/residentielle/resultats.rmx" ) );
		engine.search();
	}
}
