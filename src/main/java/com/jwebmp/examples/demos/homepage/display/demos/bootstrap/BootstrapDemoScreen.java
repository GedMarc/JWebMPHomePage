package com.jwebmp.examples.demos.homepage.display.demos.bootstrap;

import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.examples.demos.homepage.display.ComingSoon;
import za.co.mmagon.guiceinjection.GuiceContext;

public class BootstrapDemoScreen
		extends PluginDemoScreen
{
	public BootstrapDemoScreen()
	{
		super("Bootstrap4", "Web Frameworks", "Bootstrap", "Version 4");
		addComponentTile("Accordion", "Displays collapsible content panels for presenting information in a limited amount of space.", GuiceContext.get(ComingSoon.class));
	}
}
