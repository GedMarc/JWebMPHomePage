package com.jwebmp.examples.demos.homepage.entities.builders;

import com.google.inject.Key;
import com.jwebmp.entityassist.querybuilder.QueryBuilderCore;
import com.jwebmp.examples.demos.homepage.db.HomePageDB;
import com.jwebmp.examples.demos.homepage.entities.SubscriberVisitors;
import com.jwebmp.examples.demos.homepage.entities.SubscriberVisitors_;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.guicedinjection.GuiceContext;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

public class SubscriberVisitorsBuilder
		extends QueryBuilderCore<SubscriberVisitorsBuilder, SubscriberVisitors, Long>
{
	public SubscriberVisitorsBuilder findBySubscriberAndVisitorID(Subscribers subscriber, Visitors visitor)
	{
		Predicate equalsPredicate = getCriteriaBuilder().equal(getRoot().get(SubscriberVisitors_.subscriberID), subscriber);
		getFilters().add(equalsPredicate);
		Predicate visitorEquals = getCriteriaBuilder().equal(getRoot().get(SubscriberVisitors_.visitorID), visitor);
		getFilters().add(visitorEquals);
		return this;
	}

	public SubscriberVisitorsBuilder findByVisitorID(Visitors visitor)
	{
		Predicate visitorEquals = getCriteriaBuilder().equal(getRoot().get(SubscriberVisitors_.visitorID), visitor);
		getFilters().add(visitorEquals);
		return this;
	}

	@Override
	public EntityManager getEntityManager()
	{
		return GuiceContext.getInstance(Key.get(EntityManager.class, HomePageDB.class));
	}

	@Override
	protected void onCreate(SubscriberVisitors entity)
	{

	}

	@Override
	protected boolean isIdGenerated()
	{
		return true;
	}
}
