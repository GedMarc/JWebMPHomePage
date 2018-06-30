package com.jwebmp.examples.demos.homepage.entities.builders;

import com.google.inject.Key;
import com.jwebmp.entityassist.querybuilder.QueryBuilderCore;
import com.jwebmp.examples.demos.homepage.db.HomePageDB;
import com.jwebmp.examples.demos.homepage.entities.Visits;
import com.jwebmp.guicedinjection.GuiceContext;

import javax.persistence.EntityManager;

public class VisitsBuilder
		extends QueryBuilderCore<VisitsBuilder, Visits, Long>
{
	@Override
	public EntityManager getEntityManager()
	{
		return GuiceContext.getInstance(Key.get(EntityManager.class, HomePageDB.class));
	}

	@Override
	protected void onCreate(Visits entity)
	{

	}

	@Override
	protected boolean isIdGenerated()
	{
		return true;
	}
}
