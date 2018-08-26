package com.jwebmp.examples.demos.homepage.db;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.jwebmp.guicedinjection.interfaces.IGuicePostStartup;
import com.jwebmp.logger.LogFactory;

public class HomePageDBStartup
		implements IGuicePostStartup
{
	public HomePageDBStartup()
	{
	}

	@Inject
	public HomePageDBStartup(@HomePageDB PersistService persistService)
	{
		//persistService.start();
		LogFactory.getLog("DB")
		          .info("DB Startup");
	}

	@Override
	public void postLoad()
	{
		//should be built
	}
}
