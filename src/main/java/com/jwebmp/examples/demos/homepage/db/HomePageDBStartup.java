package com.jwebmp.examples.demos.homepage.db;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;
import com.jwebmp.guiceinjection.db.DBStartupAsync;

import javax.sql.DataSource;

public class HomePageDBStartup extends DBStartupAsync
{
	public HomePageDBStartup()
	{
	}

	@Inject
	public HomePageDBStartup(@HomePageDB PersistService persistService, @HomePageDB DataSource dataSource)
	{
		super(persistService, dataSource);
	}
}
