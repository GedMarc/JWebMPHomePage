package com.jwebmp.examples.demos.homepage.display.demos.angular.animate;

import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.examples.demos.homepage.display.ComingSoon;
import za.co.mmagon.guiceinjection.GuiceContext;

public class AngularAnimateDemoScreen
		extends PluginDemoScreen
{
	public AngularAnimateDemoScreen()
	{
		super("Bootstrap4", "Web Frameworks", "Bootstrap", "Version 4");
		addComponentTile("Accordion", "Displays collapsible content panels for presenting information in a limited amount of space.", GuiceContext.get(ComingSoon.class));
	}
}
