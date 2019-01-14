package com.jwebmp.examples.demos.homepage.display.demos.htmltags;

import com.jwebmp.core.base.html.Abbreviation;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.examples.demos.homepage.components.display.DisplayAPITabView;
import com.jwebmp.examples.demos.homepage.components.general.PackagesBrowser;

public class InputTypesDemoScreen
		extends DemoScreen
{

	public InputTypesDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.core.base.html.inputs", Abbreviation.class, "JWebMP Core", true, false));
		add(buildGoToSource(InputTypesDemoScreen.class, PackagesBrowser.class, DisplayAPITabView.class));
	}
}
