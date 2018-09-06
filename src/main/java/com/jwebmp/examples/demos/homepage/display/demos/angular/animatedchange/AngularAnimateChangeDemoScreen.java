package com.jwebmp.examples.demos.homepage.display.demos.angular.animatedchange;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;

public class AngularAnimateChangeDemoScreen
		extends PluginDemoScreen
{
	public AngularAnimateChangeDemoScreen()
	{
		super("Angular Animated change", "Angular", "Utilities", "Animated Change Events");

		addComponentTile("AngularAnimated ChangeDirective",
		                 "An assistant directive that monitors old and new values and applies the animation when a value changes");

		getAdditionalsRight().add(new Div("This directive is automatically included when added to the classpath"));
	}
}
