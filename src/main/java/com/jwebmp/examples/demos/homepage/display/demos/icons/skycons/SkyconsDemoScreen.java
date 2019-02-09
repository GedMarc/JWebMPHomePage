package com.jwebmp.examples.demos.homepage.display.demos.icons.skycons;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.skycons.configurator.SkyconPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class SkyconsDemoScreen
		extends DemoScreen
{

	public SkyconsDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.skycons", SkyconPageConfigurator.class,
		                          "Skycons", true, true));
		add(buildGoToSource(SkyconPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//		add(more);
	}
}
