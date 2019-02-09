package com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4.bootswatch;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.bs4.bootswatch.Bootswatch4PageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class BootswatchDemoScreen
		extends DemoScreen
{
	public BootswatchDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.bootswatch4", Bootswatch4PageConfigurator.class,
		                          "Bootswatch4", true, true));
		add(buildGoToSource(Bootswatch4PageConfigurator.class, BootswatchDemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);


		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
