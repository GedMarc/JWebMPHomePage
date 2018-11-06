package com.jwebmp.examples.demos.homepage;

import com.jwebmp.guicedinjection.GuiceConfig;

public class DemoGuiceConfigurator
		implements com.jwebmp.guicedinjection.interfaces.IGuiceConfigurator
{

	public DemoGuiceConfigurator()
	{
	}

	@Override
	public GuiceConfig configure(GuiceConfig config)
	{
		return config//  .setVerbose(true)
		             .setExcludeParentModules(true)
				;
	}
}
