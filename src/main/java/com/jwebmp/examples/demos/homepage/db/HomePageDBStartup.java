package com.jwebmp.examples.demos.homepage.db;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.jwebmp.guicedpersistence.services.IDBStartup;
import com.jwebmp.logger.LogFactory;

public class HomePageDBStartup
		implements IDBStartup
{
	public HomePageDBStartup()
	{
	}

	@Inject
	public HomePageDBStartup(@HomePageDB PersistService persistService)
	{
		persistService.start();
		LogFactory.getLog("HomePage DB Startup")
		          .info("HomePage DB Startup : Started");
	}
}
