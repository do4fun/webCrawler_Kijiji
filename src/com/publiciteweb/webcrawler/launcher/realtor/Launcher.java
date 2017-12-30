package com.publiciteweb.webcrawler.launcher.realtor;

import com.publiciteweb.webcrawler.Engine;
import com.publiciteweb.webcrawler.Stewart;

public class Launcher
{
	public static void main( String[] args )
	{
		Engine engine = new Engine(
				"https://www.realtor.ca/R%C3%A9sidentiel/map.aspx#CultureId=2&ApplicationId=1&RecordsPerPage=9&MaximumResults=9&PropertySearchTypeId=1&TransactionTypeId=2&StoreyRange=0-0&BedRange=0-0&BathRange=0-0&LongitudeMin=-80.12274932861322&LongitudeMax=-59.468452453613224&LatitudeMin=44.79719138994582&LatitudeMax=55.431940311609885&SortOrder=A&SortBy=1&viewState=m&Longitude=-69.1504974365234&Latitude=47.8184051513672&ZoomLevel=6&PropertyTypeGroupID=1" );
		Stewart hyperlinkToScrapStewart = new Stewart( "https://www.kijiji.ca", "/v-cars-trucks" );
		hyperlinkToScrapStewart.setToScrap( true );
		engine.addStewarts( hyperlinkToScrapStewart );
		engine.addStewarts( new Stewart( "https://www.kijiji.ca", "/b-autos-camions" ) );
		engine.search();
	}

}
