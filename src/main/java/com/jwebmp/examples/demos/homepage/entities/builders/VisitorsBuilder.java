package com.jwebmp.examples.demos.homepage.entities.builders;

import com.google.inject.Key;
import com.entityassist.querybuilder.QueryBuilderCore;
import com.jwebmp.examples.demos.homepage.db.HomePageDB;
import com.jwebmp.examples.demos.homepage.entities.Visitors;
import com.jwebmp.examples.demos.homepage.entities.Visitors_;
import com.guicedee.guicedinjection.GuiceContext;

import javax.persistence.EntityManager;

import static com.entityassist.enumerations.Operand.*;

public class VisitorsBuilder
		extends QueryBuilderCore<VisitorsBuilder, Visitors, Long>
{
	public VisitorsBuilder findByGuid(String guid)
	{
		where(Visitors_.localStorageKey, Equals, guid);
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
