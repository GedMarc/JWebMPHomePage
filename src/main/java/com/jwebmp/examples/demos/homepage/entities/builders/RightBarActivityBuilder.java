package com.jwebmp.examples.demos.homepage.entities.builders;

import com.google.inject.Key;
import com.jwebmp.entityassist.querybuilder.QueryBuilderCore;
import com.jwebmp.examples.demos.homepage.db.HomePageDB;
import com.jwebmp.examples.demos.homepage.entities.RightBarActivity;
import com.jwebmp.guicedinjection.GuiceContext;

import javax.persistence.EntityManager;

public class RightBarActivityBuilder
		extends QueryBuilderCore<RightBarActivityBuilder, RightBarActivity, Integer>
{
	@Override
	public EntityManager getEntityManager()
	{
		return GuiceContext.getInstance(Key.get(EntityManager.class, HomePageDB.class));
	}

	@Override
	protected boolean isIdGenerated()
	{
		return true;
	}
}
