package com.jwebmp.examples.demos.homepage.db.dao;

import com.google.inject.Singleton;
import com.jwebmp.examples.demos.homepage.entities.Visits;

@Singleton
public class VisitsService
{

	public long countVisits()
	{
		return new Visits().builder()
		                   .inActiveRange()
		                   .getCount();
	}
}
