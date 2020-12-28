package com.jwebmp.examples.apps.homepage;

import com.jwebmp.examples.apps.homepage.components.SwapContainer;
import com.jwebmp.plugins.themes.mintontheme.partials.LogoBox;
import com.jwebmp.examples.apps.homepage.events.SwapScreenEvent;
import com.jwebmp.examples.apps.homepage.pages.welcome.WelcomePage;

public class DemoLogoBox extends LogoBox
{
	public DemoLogoBox()
	{
		addLogo("images/logo-sm.png", "images/logo-light.png", "images/logo-sm-dark.png", "images/logo-dark.png");
	}
	
	@Override
	public void init()
	{
		if (!isInitialized())
		{
			addEvent(new SwapScreenEvent(new SwapContainer(new WelcomePage()), this));
		}
		super.init();
	}
}
