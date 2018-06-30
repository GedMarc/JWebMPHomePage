package com.jwebmp.examples.demos.homepage;

import com.jwebmp.guiceinjection.GuiceConfig;

public class GuiceConfigurator
		implements com.jwebmp.guiceinjection.interfaces.GuiceConfigurator
{

	@Override
	public GuiceConfig configure(GuiceConfig config)
	{
		return config.setWhiteList(true);
	}
}
