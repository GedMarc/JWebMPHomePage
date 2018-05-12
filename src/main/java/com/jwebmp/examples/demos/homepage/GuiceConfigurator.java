package com.jwebmp.examples.demos.homepage;

import za.co.mmagon.guiceinjection.GuiceConfig;
import za.co.mmagon.guiceinjection.interfaces.IGuiceConfigurator;

public class GuiceConfigurator
		implements IGuiceConfigurator
{

	@Override
	public GuiceConfig configure(GuiceConfig config)
	{
		return config.setWhiteList(true);
	}
}
