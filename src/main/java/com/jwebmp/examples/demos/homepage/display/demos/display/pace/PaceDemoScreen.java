package com.jwebmp.examples.demos.homepage.display.demos.display.pace;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.pace.PaceLoaderPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class PaceDemoScreen
		extends DemoScreen
{
	public PaceDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.pace", PaceLoaderPageConfigurator.class,
		                          "Pace", true, true));
		add(buildGoToSource(PaceLoaderPageConfigurator.class, PaceDemoScreen.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		add(more);
	}
}
