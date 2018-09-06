package com.jwebmp.examples.demos.homepage.display.demos.angular.autofocus;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;

public class AngularAutoFocusDemoScreen
		extends PluginDemoScreen
{
	public AngularAutoFocusDemoScreen()
	{
		super("Angular Auto Focus", "Angular", "Utilities", "Auto Focus");

		addComponentTile("Angular Auto Focus",
		                 "An assistant directive for mdDialogs and the likes..");

		getAdditionalsRight().add(new Div("This directive is automatically included when added to the classpath, or a dependency of the library is added."));
	}
}
