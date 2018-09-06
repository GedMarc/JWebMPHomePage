package com.jwebmp.examples.demos.homepage.display.demos.angular.autoexpand;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;

public class AngularAutoExpandDemoScreen
		extends PluginDemoScreen
{
	public AngularAutoExpandDemoScreen()
	{
		super("Angular Auto Expand", "Angular", "Utilities", "Auto Expand");

		addComponentTile("Angular Auto Expand Directive",
		                 "Automatically expands text boxes as you type into them to avoid scroll bars.");

		getAdditionalsRight().add(new Div("This directive is automatically included when added to the classpath"));
	}
}
