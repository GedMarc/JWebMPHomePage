package com.jwebmp.examples.demos.homepage.db;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.jwebmp.guicedpersistence.services.IDBStartup;

public class HomePageDBStartupPostStartup
		implements IDBStartup
{
	public HomePageDBStartupPostStartup()
	{
	}

	@Inject
	public HomePageDBStartupPostStartup(@HomePageDB PersistService persistService)
	{
		persistService.start();
	}
}
