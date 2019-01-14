package com.jwebmp.examples.demos.homepage.display.demos.htmltags;

import com.google.inject.Singleton;
import com.jwebmp.core.base.html.Abbreviation;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.examples.demos.homepage.components.general.OptionsBrowser;

@Singleton
public class HtmlTagsDemoScreen
		extends DemoScreen
{
	@SuppressWarnings("unchecked")
	public HtmlTagsDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.core.base.html", Abbreviation.class, "JWebMP Core", true, false));
		add(buildGoToSource(HtmlTagsDemoScreen.class, DefaultPackageAPI.class, OptionsBrowser.class));
	}
}
