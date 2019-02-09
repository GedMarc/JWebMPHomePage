package com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.BootstrapPageConfigurator;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class Bootstrap4DemoScreen
		extends DemoScreen
{
	public Bootstrap4DemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.bootstrap4", BootstrapPageConfigurator.class,
		                          "Bootstrap4", true, true));
		add(buildGoToSource(BootstrapPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
