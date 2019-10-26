package com.jwebmp.examples.demos.homepage.display.servlets;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;

import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class RestDisplayPart
		extends DisplayPart<RestDisplayPart>
{
	public RestDisplayPart()
	{
		this(false);
	}

	public RestDisplayPart(boolean scrolly)
	{
		BSCardBody<?> body = getDefaultBody();
		Div all = new Div();
		DefaultSlimScroll scroll = null;
		if (scrolly)
		{
			scroll = new DefaultSlimScroll();
		}
		Div dualExecution = new Div();

		dualExecution.add(new H3<>("Rest Anywhere"));
		dualExecution.add("Rest Services are provided using JBoss <code>Rest-Easy</code> and allow registration by simply binding the rest service." +
		                  "<br/> The service can run standalone with an <code>undertow</code> module for distributed servicing, " +
		                  "<br/>and can be packaged with your app for the cleanest development experience yet.");

		dualExecution.add("First create your binding module");
		addSourceToContainer(RestDisplayPart.class, "rest_binding.txt", Java, dualExecution);

		dualExecution.add("Then register your new module via SPI to IGuiceModule");
		dualExecution.add(addServiceTree("com.guicedee.guicedinjection.interfaces.IGuiceModule"));
		dualExecution.add("Or even better in JPMS just use in your module-info.java file <code>provides IGuiceModule with RestTestBinding</code>");


		dualExecution.add("Time to add some rest services");
		addSourceToContainer(RestDisplayPart.class, "rest_services.txt", Java, dualExecution);

		dualExecution.add("Your services are now available, you can change the rest application path using <code>RestEasyModule.setPath()</code>" +
		                  "<br/>This particular rest service will be located at <code>/rest/hello/world</code>");

		dualExecution.add("The test case for this particular service looks like this : ");
		addSourceToContainer(RestDisplayPart.class, "rest_test_case.txt", Java, dualExecution);

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
