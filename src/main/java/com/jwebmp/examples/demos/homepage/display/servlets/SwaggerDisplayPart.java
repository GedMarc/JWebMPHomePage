package com.jwebmp.examples.demos.homepage.display.servlets;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.examples.demos.homepage.components.display.PluginModulePart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;

public class SwaggerDisplayPart
		extends DisplayPart<SwaggerDisplayPart>
{
	public SwaggerDisplayPart()
	{
		this(false);
	}

	public SwaggerDisplayPart(boolean scrolly)
	{
		BSCardBody<?> body = getDefaultBody();
		Div all = new Div();
		DefaultSlimScroll scroll = null;
		if (scrolly)
		{
			scroll = new DefaultSlimScroll();
		}
		Div dualExecution = new Div();

		dualExecution.add(new H3<>("Swagger Rest API"));
		dualExecution.add("The Swagger module is a convenience module for implementing a Rest API to your application." +
		                  "<br/>This module runs completely separate to JWebMP and can be distributed anywhere. " +
		                  "<br/>This module only includes OpenAPI and the UI is included in a separate module.");

		dualExecution.add("To use this module simply include it in your class path. " +
		                  "<br/>You can exclude the dependency in your maven production profile for safety reasons.");

		dualExecution.add("To enable the Swagger UI include the below module. Again also disable when not explicitly needed in production.");
		dualExecution.add(new PluginModulePart("Guiced Swagger UI"));

		if (scrolly)
		{
			scroll.add(dualExecution);
			all.add(scroll);
		}
		else
		{
			all.add(dualExecution);
		}
		body.add(all);
		add(body);
	}
}
