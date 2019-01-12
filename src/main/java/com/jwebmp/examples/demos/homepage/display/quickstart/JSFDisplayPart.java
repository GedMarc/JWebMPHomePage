package com.jwebmp.examples.demos.homepage.display.quickstart;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;

import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class JSFDisplayPart
		extends DisplayPart<JSFDisplayPart>
{
	public JSFDisplayPart()
	{
		this(true);
	}

	public JSFDisplayPart(boolean scrolly)
	{
		BSCardBody<?> body = getDefaultBody();
		Div all = new Div();
		DefaultSlimScroll scroll = null;
		if (scrolly)
		{
			scroll = new DefaultSlimScroll();
		}
		Div dualExecution = new Div();

		dualExecution.add(new H3<>("Easily begin migration"));
		dualExecution.add("Migrating to JPMS has been made as easy as ever, with a completely non-intrusive system that can run alongside anything.</br>" +
		                  "There is a Guice module for running JSF and any components (PrimeFaces etc...) through Guice.<br/>" +
		                  "JSF Integration for Guiced Injection Framework. Allows for Faces to execute through the Guice Filter<br/>" +
		                  "To configure correctly, add the following depedency<br/>");

		addSourceToContainer(QuickStartScreen.class, "servlet_jsf.txt", XML, dualExecution);

		dualExecution.add("Include the following in faces-config.xml");
		addSourceToContainer(QuickStartScreen.class, "faces_config_jsf.txt", XML, dualExecution);

		dualExecution.add("You will need to remove your context path from web.xml or the configuration will be skipped");
		addSourceToContainer(QuickStartScreen.class, "remove_faces_jsf.txt", XML, dualExecution);

		dualExecution.add("This mapping is performed through the servlet binder<br/>" +
		                  "You can change/add URL mappings by configuring GuicedServletJSFBindings or by mapping your own<br/>");
		addSourceToContainer(QuickStartScreen.class, "jsf_mapping.txt", JS, dualExecution);

		dualExecution.add("This implementation is a merge of http://javaevangelist.blogspot.com/2013/08/jsf-2x-tip-of-day-guice-elresolver.html <br/>" +
		                  "and https://github.com/skuzzle/guice-jsf and uses the automated configurations of guiced-servlets and guiced-injection");
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
