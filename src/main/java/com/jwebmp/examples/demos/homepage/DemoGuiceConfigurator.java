package com.jwebmp.examples.demos.homepage;

import com.guicedee.guicedinjection.GuiceConfig;

public class DemoGuiceConfigurator
		implements com.guicedee.guicedinjection.interfaces.IGuiceConfigurator
{

	public DemoGuiceConfigurator()
	{
	}

	@Override
	public GuiceConfig configure(GuiceConfig config)
	{
		return config
				.setClasspathScanning(true)
				.setMethodInfo(true)
				.setFieldInfo(true)
				.setAnnotationScanning(true)
				;
	}
}
