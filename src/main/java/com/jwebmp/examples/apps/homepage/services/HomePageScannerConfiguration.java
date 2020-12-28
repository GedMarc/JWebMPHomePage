package com.jwebmp.examples.apps.homepage.services;

import com.guicedee.guicedinjection.GuiceConfig;
import com.guicedee.guicedinjection.interfaces.IGuiceConfigurator;

public class HomePageScannerConfiguration implements IGuiceConfigurator
{
	@Override
	public GuiceConfig<?> configure(GuiceConfig config)
	{
		config.setClasspathScanning(true)
		      .setIncludeModuleAndJars(true)
		      .setAnnotationScanning(true)
		      .setFieldInfo(true)
		      .setMethodInfo(true)
		      .setIgnoreFieldVisibility(true)
		      .setIgnoreMethodVisibility(true);
		
		return config;
	}
}
