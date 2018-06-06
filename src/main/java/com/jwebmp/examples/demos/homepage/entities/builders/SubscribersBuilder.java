package com.jwebmp.examples.demos.homepage.entities.builders;


import com.google.inject.Inject;
import com.google.inject.Key;
import com.jwebmp.entityassist.querybuilder.QueryBuilderCore;
import com.jwebmp.examples.demos.homepage.db.HomePageDB;
import com.jwebmp.examples.demos.homepage.entities.Subscribers;
import com.jwebmp.examples.demos.homepage.entities.Subscribers_;
import com.jwebmp.guiceinjection.GuiceContext;

import javax.persistence.EntityManager;

import static com.jwebmp.entityassist.enumerations.Operand.*;

public class SubscribersBuilder
		extends QueryBuilderCore<SubscribersBuilder, Subscribers, Long>
{

	public SubscribersBuilder findByEmail(String email)
	{
		where(Subscribers_.emailAddress, Equals, email);
		return this;
	}

	public SubscribersBuilder findByConfirmationKey(String confirmationKey)
	{
		where(Subscribers_.confirmationKey, Equals, confirmationKey);
		return this;
	}

	public SubscribersBuilder withUnconfirmedKey()
	{
		where(Subscribers_.confirmed, Equals, false);
		return this;
	}

	public SubscribersBuilder findNewSubscribed()
	{
		where(Subscribers_.unsubscribed, Equals, false);
		return this;
	}

	public SubscribersBuilder findNewsUnsubscribed()
	{
		where(Subscribers_.unsubscribed, Equals, true);
		return this;
	}

	public SubscribersBuilder withPassword(String password)
	{
		where(Subscribers_.password, Equals, password);
		return this;
	}

	@Override
	public EntityManager getEntityManager()
	{
		return GuiceContext.getInstance(Key.get(EntityManager.class, HomePageDB.class));
	}

	@Inject
	protected EntityManager entityManager(@HomePageDB EntityManager entityManager)
	{
		return entityManager;
	}

	@Override
	protected void onCreate(Subscribers entity)
	{

	}

	@Override
	protected boolean isIdGenerated()
	{
		return true;
	}
}
