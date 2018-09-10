package com.jwebmp.examples.demos.homepage.display.demos.angular.sanitize;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class AngularSanitizeDemoScreen
		extends PluginDemoScreen
{
	public AngularSanitizeDemoScreen()
	{
		super("Angular Sanitize", "Angular", "Utilities", "Sanitize");

		addComponentTile("Angular Sanitize Module", "Angular Module Service that registers and installs angular sanitize ");

		getAdditionalsRight().add(new Div<>().addClass(Col_12)
		                                     .setText("This module is added automatically when it is in the classpath."));

	}
}
