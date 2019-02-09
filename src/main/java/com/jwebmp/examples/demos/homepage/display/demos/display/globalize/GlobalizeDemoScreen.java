package com.jwebmp.examples.demos.homepage.display.demos.display.globalize;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.globalize.cultures.GlobalizePageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class GlobalizeDemoScreen
		extends DemoScreen
{
	public GlobalizeDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.globalize.cultures", GlobalizePageConfigurator.class,
		                          "Globalize", true, true));
		add(buildGoToSource(GlobalizePageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);


		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
