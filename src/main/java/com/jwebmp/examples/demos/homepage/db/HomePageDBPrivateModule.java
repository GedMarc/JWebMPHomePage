package com.jwebmp.examples.demos.homepage.db;

import com.google.inject.PrivateModule;
import com.jwebmp.guicedinjection.interfaces.IGuiceModule;

public class HomePageDBPrivateModule
		extends PrivateModule
		implements IGuiceModule<HomePageDBPrivateModule>
{

	@Override
	protected void configure()
	{
		install(new HomePageDBModule());
	}
}
