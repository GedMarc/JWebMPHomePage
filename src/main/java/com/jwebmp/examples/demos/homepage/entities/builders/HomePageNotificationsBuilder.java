package com.jwebmp.examples.demos.homepage.entities.builders;

import com.google.inject.Key;
import com.jwebmp.entityassist.querybuilder.QueryBuilderCore;
import com.jwebmp.examples.demos.homepage.db.HomePageDB;
import com.jwebmp.examples.demos.homepage.entities.HomePageNotifications;
import com.jwebmp.guiceinjection.GuiceContext;

import javax.persistence.EntityManager;

public class HomePageNotificationsBuilder
		extends QueryBuilderCore<HomePageNotificationsBuilder, HomePageNotifications, Integer>
{
	@Override
	public EntityManager getEntityManager()
	{
		return GuiceContext.getInstance(Key.get(EntityManager.class, HomePageDB.class));
	}

	@Override
	protected void onCreate(HomePageNotifications entity)
	{

	}

	@Override
	protected boolean isIdGenerated()
	{
		return true;
	}
}
