package com.publiciteweb.webcrawler.kijiji;

import com.publiciteweb.webcrawler.Engine;

public class Launcher {

	public static void main(String[] args) 
	{
		Engine engine = new Engine();
		engine.search("https://www.kijiji.ca/b-autos-camions/quebec/new__used/c174l9001a49");
	}

}
