package com.jwebmp.examples.demos.homepage.db;

import com.guicedee.guicedpersistence.btm.BTMConnectionBaseInfo;
import com.guicedee.guicedpersistence.db.DatabaseModule;
import com.guicedee.guicedpersistence.db.ConnectionBaseInfo;
import com.oracle.jaxb21.PersistenceUnit;

import jakarta.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.util.Properties;

public class HomePageDBModule
		extends DatabaseModule<HomePageDBModule>
{

	@NotNull
	@Override
	protected String getPersistenceUnitName()
	{
		return "AppPU";
	}

	@Override
	@NotNull
	protected ConnectionBaseInfo getConnectionBaseInfo(PersistenceUnit unit, Properties filteredProperties)
	{
		ConnectionBaseInfo cbi = new BTMConnectionBaseInfo(false);
		cbi.setAllowLocalTransactions(true);
		cbi.setShareTransactionConnections(true);
		cbi.setEnableJdbc4ConnectionTest(true);
		cbi.setTestQuery("select 1");
		return cbi;
	}

	@NotNull
	@Override
	protected String getJndiMapping()
	{
		return "jdbc/homepage";
	}

	@NotNull
	@Override
	protected Class<? extends Annotation> getBindingAnnotation()
	{
		return HomePageDB.class;
	}
}
