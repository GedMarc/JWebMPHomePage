package com.jwebmp.examples.demos.homepage.entities.persistasync;

import com.jwebmp.examples.demos.homepage.db.HomePageDB;
import com.jwebmp.examples.demos.homepage.entities.Visits;
import com.jwebmp.guicedpersistence.db.annotations.Transactional;

public class VisitsPersistAsync
		implements Runnable
{
	private Visits visits;

	public VisitsPersistAsync()
	{
	}

	public VisitsPersistAsync(Visits visits)
	{
		this.visits = visits;
	}

	@Override
	@Transactional(entityManagerAnnotation = HomePageDB.class)
	public void run()
	{
		visits.builder()
		      .setRunDetached(true)
		      .persist(visits);
		System.out.println("visits run");
	}

	public Visits getVisits()
	{
		return visits;
	}

	public void setVisits(Visits visits)
	{
		this.visits = visits;
	}
}
