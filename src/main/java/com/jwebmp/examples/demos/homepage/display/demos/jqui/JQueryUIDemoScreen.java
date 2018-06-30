package com.jwebmp.examples.demos.homepage.display.demos.jqui;

import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;
import com.jwebmp.examples.demos.homepage.display.ComingSoon;
import com.jwebmp.examples.demos.homepage.display.demos.jqui.demos.JQUIDraggableDemoScreen;
import com.jwebmp.guicedinjection.GuiceContext;

public class JQueryUIDemoScreen
		extends PluginDemoScreen
{
	public JQueryUIDemoScreen()
	{
		super("JQuery UI", "Web Frameworks", "jQuery UI");
		addFeatureTile("Draggable", "Allow elements to be moved using the mouse", new JQUIDraggableDemoScreen());
		addComponentTile("Accordion", "Displays collapsible content panels for presenting information in a limited amount of space.", GuiceContext.get(ComingSoon.class));
	}
}
