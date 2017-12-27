package com.publiciteweb.webcrawler.kijiji;

import com.publiciteweb.webcrawler.Engine;
import com.publiciteweb.webcrawler.Stewart;

public class Launcher
{

	public static void main( String[] args )
	{
		Engine engine = new Engine( "https://www.kijiji.ca/b-autos-camions/quebec/new__used/c174l9001a49" );
		Stewart hyperlinkToScrapStewart = new Stewart( "https://www.kijiji.ca", "/v-cars-trucks" );
		hyperlinkToScrapStewart.setToScrap( true );
		engine.addStewarts( hyperlinkToScrapStewart );
		engine.addStewarts( new Stewart( "https://www.kijiji.ca", "/b-autos-camions" ) );
		engine.search();
	}
}
