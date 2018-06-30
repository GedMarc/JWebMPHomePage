package com.jwebmp.examples.demos.homepage.db;

import com.jwebmp.guicedinjection.db.ConnectionBaseInfo;
import com.jwebmp.guicedinjection.db.connectionbasebuilders.HibernateDefaultConnectionBaseBuilder;
import com.oracle.jaxb21.Persistence;

import java.lang.annotation.Annotation;
import java.util.Properties;

public class HomePageDBModule
		extends HibernateDefaultConnectionBaseBuilder
{

	@Override
	protected ConnectionBaseInfo getConnectionBaseInfo(Persistence.PersistenceUnit unit, Properties filteredProperties)
	{
		ConnectionBaseInfo cbi = super.getConnectionBaseInfo(unit, filteredProperties);

		cbi.setAllowLocalTransactions(true);
		cbi.setShareTransactionConnections(true);
		cbi.setEnableJdbc4ConnectionTest(true);
		cbi.setTestQuery("select 1");

		return cbi;
	}

	@Override
	protected String getJndiMapping()
	{
		return "jdbc/homepage";
	}

	@Override
	protected String getPersistenceUnitName()
	{
		return "AppPU";
	}

	@Override
	protected Class<? extends Annotation> getBindingAnnotation()
	{
		return HomePageDB.class;
	}
}
