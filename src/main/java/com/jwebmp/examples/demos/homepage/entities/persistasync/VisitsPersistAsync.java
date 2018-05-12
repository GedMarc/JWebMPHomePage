package com.jwebmp.examples.demos.homepage.entities.persistasync;


import com.google.inject.persist.Transactional;
import za.co.mmagon.guiceinjection.GuiceContext;
import com.jwebmp.examples.demos.homepage.entities.Visits;

import javax.persistence.EntityManager;

public class VisitsPersistAsync implements Runnable
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
	@Transactional
	public void run()
	{
		visits.builder().setRunDetached(true).persist(visits);
		System.out.println("visits run");
	}
}
