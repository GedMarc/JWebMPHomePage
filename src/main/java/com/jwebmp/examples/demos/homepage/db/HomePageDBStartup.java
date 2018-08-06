package com.jwebmp.examples.demos.homepage.db;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.jwebmp.guicedinjection.interfaces.IGuicePostStartup;

public class HomePageDBStartup
		implements IGuicePostStartup
{
	public HomePageDBStartup()
	{
	}

	@Inject
	public HomePageDBStartup(@HomePageDB PersistService persistService)
	{
		persistService.start();
	}

	@Override
	public void postLoad()
	{
		//should be built
	}
}
