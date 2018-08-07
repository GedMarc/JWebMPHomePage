package com.jwebmp.examples.demos.homepage.db;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.jwebmp.guicedinjection.interfaces.IGuicePostStartup;
import com.jwebmp.logger.LogFactory;

import javax.sql.DataSource;

public class HomePageDBStartup
		implements IGuicePostStartup
{
	public HomePageDBStartup()
	{
	}

	@Inject
	public HomePageDBStartup(@HomePageDB PersistService persistService, @HomePageDB DataSource dataSource)
	{
		persistService.start();
		LogFactory.getLog("DB").info("DB Startup");
	}

	@Override
	public void postLoad()
	{
		//should be built
	}
}
