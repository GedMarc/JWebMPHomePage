package com.jwebmp.examples.demos.homepage;

import com.jwebmp.guicedinjection.GuiceConfig;

public class DemoGuiceConfigurator
		implements com.jwebmp.guicedinjection.interfaces.IGuiceConfigurator
{

	public DemoGuiceConfigurator()
	{
		System.out.println("Constructed");
	}

	@Override
	public GuiceConfig configure(GuiceConfig config)
	{
		return config.setWhiteList(true)
		             .setVerbose(true);
	}
}
