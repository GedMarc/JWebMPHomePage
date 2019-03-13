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

import static com.jwebmp.entityassist.enumerations.Operand.*;

public class SubscriberVisitorsBuilder
		extends QueryBuilderCore<SubscriberVisitorsBuilder, SubscriberVisitors, Long>
{
	public SubscriberVisitorsBuilder findBySubscriberAndVisitorID(Subscribers subscriber, Visitors visitor)
	{
		where(SubscriberVisitors_.subscriberID, Equals, subscriber);
		findByVisitorID(visitor);
		return this;
	}

	public SubscriberVisitorsBuilder findByVisitorID(Visitors visitor)
	{
		where(SubscriberVisitors_.visitorID, Equals, visitor);
		return this;
	}

	@Override
	public EntityManager getEntityManager()
	{
		return GuiceContext.get(Key.get(EntityManager.class, HomePageDB.class));
	}

	@Override
	protected boolean isIdGenerated()
	{
		return true;
	}
}
