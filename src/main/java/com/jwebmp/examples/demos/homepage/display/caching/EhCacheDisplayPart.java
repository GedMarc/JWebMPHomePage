package com.jwebmp.examples.demos.homepage.display.caching;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.examples.demos.homepage.components.DefaultSlimScroll;
import com.jwebmp.examples.demos.homepage.components.display.DisplayPart;
import com.jwebmp.plugins.bootstrap4.cards.parts.BSCardBody;

public class EhCacheDisplayPart
		extends DisplayPart<EhCacheDisplayPart>
{
	public EhCacheDisplayPart()
	{
		this(false);
	}

	public EhCacheDisplayPart(boolean scrolly)
	{
		BSCardBody<?> body = getDefaultBody();
		Div all = new Div();
		DefaultSlimScroll scroll = null;
		if (scrolly)
		{
			scroll = new DefaultSlimScroll();
		}

		Div dualExecution = new Div();

		dualExecution.add(new H3<>("Hibernate EH Cache Module"));
		dualExecution.add("EH Cache is used quite widely during development and the such, and Hibernate as a neat little addon to utlize it for the 2nd Level Cache." +
		                  "<br/> Including this modules adds all the required persistence unit properties for configuration, and allows you to set the region name to work with." +
		                  "<br/>");

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
