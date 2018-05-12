package com.jwebmp.examples.demos.homepage.entities.builders;

import com.google.inject.Key;
import za.co.mmagon.entityassist.querybuilder.QueryBuilderCore;
import za.co.mmagon.guiceinjection.GuiceContext;
import com.jwebmp.examples.demos.homepage.db.HomePageDB;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.examples.demos.homepage.entities.Visitors_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

public class VisitorsBuilder extends QueryBuilderCore<VisitorsBuilder, Visitors, Long>
{
	public VisitorsBuilder findByGuid(String guid)
	{
		Predicate equalsPredicate = getCriteriaBuilder().equal(getRoot().get(Visitors_.localStorageKey), guid);
		getFilters().add(equalsPredicate);
		return this;
	}

	@Override
	public EntityManager getEntityManager()
	{
		return GuiceContext.getInstance(Key.get(EntityManager.class, HomePageDB.class));
	}

	@Override
	protected void onCreate(Visitors entity)
	{

	}

	@Override
	protected boolean isIdGenerated()
	{
		return true;
	}
}
