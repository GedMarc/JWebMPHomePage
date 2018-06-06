package com.jwebmp.examples.demos.homepage;

import com.jwebmp.guiceinjection.GuiceConfig;
import com.jwebmp.guiceinjection.interfaces.IGuiceConfigurator;

public class GuiceConfigurator
		implements IGuiceConfigurator
{

	@Override
	public GuiceConfig configure(GuiceConfig config)
	{
		return config.setWhiteList(true);
	}
}
