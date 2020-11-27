package com.jwebmp.examples.demos.homepage.entities.builders;

import com.google.inject.Key;
import com.entityassist.querybuilder.QueryBuilderCore;
import com.jwebmp.examples.demos.homepage.db.HomePageDB;
import com.jwebmp.examples.demos.homepage.entities.Visits;
import com.guicedee.guicedinjection.GuiceContext;

import jakarta.persistence.EntityManager;

public class VisitsBuilder
		extends QueryBuilderCore<VisitsBuilder, Visits, Long>
{
	@Override
	public EntityManager getEntityManager()
	{
		return GuiceContext.get(Key.get(EntityManager.class, HomePageDB.class));
	}

	@Override
	public boolean isIdGenerated()
	{
		return true;
	}
}
