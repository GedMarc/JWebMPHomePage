package com.jwebmp.examples.demos.homepage;

import com.jwebmp.guicedinjection.GuiceConfig;

public class GuiceConfigurator
		implements com.jwebmp.guicedinjection.interfaces.GuiceConfigurator
{

	@Override
	public GuiceConfig configure(GuiceConfig config)
	{
		return config.setWhiteList(true);
	}
}
