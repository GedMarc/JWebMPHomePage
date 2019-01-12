package com.jwebmp.examples.demos.homepage.display.caching;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;

public class HazelcastDisplayPart
		extends DisplayPart<HazelcastDisplayPart>
{
	public HazelcastDisplayPart()
	{
		this(false);
	}

	public HazelcastDisplayPart(boolean scrolly)
	{
		BSCardBody<?> body = getDefaultBody();
		Div all = new Div();
		DefaultSlimScroll scroll = null;
		if (scrolly)
		{
			scroll = new DefaultSlimScroll();
		}

		Div dualExecution = new Div();

		dualExecution.add(new H3<>("Hazelcast Client API"));
		dualExecution.add("Hazelcast is a caching provider that you can use for free for up to two nodes." +
		                  "<br/><br/>This module adds the necessary persistence properties keeping your persistence.xml file clean and neat, " +
		                  "<br/>while still adhering to programmatic approach of the framework." +
		                  "<br/><br/>" +
		                  "You can use the <code>EhCacheEntityManagerProperties</code> class to configure the region name." +
		                  "<br/>");

		dualExecution.add("To use this module simply include it in your class path.");

		dualExecution.add("P.S. Start the Hazelcast server with JCache on the classpath. Include the <code>cache-api-1.1.0.jar</code> in the startup script as per here :)" +
		                  "<br/>Hazelcast does a lot, This one is to enable the JCache API required for Hibernate 5.3 and up.");

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
