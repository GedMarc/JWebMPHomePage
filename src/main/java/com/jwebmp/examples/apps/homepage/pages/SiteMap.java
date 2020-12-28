package com.jwebmp.examples.apps.homepage.pages;

import com.jwebmp.examples.apps.homepage.components.SwapScreen;
import com.jwebmp.examples.apps.homepage.pages.helloworld.GetStartedPage;
import com.jwebmp.examples.apps.homepage.pages.helloworld.MavenBasicsPage;
import com.jwebmp.examples.apps.homepage.pages.welcome.WelcomePage;

/**
 * An easy mapping to a page, also encapsulates the Class.forName for the class required ;)
 */
public enum SiteMap
{
	Welcome(WelcomePage.class),
	GetStarted(GetStartedPage.class),
	MavenSetup(MavenBasicsPage.class),
	
	
	;
	private Class<? extends SwapScreen<?>> screenClass;
	
	
	SiteMap(Class<? extends SwapScreen<?>> screenClass)
	{
		this.screenClass = screenClass;
	}
}
