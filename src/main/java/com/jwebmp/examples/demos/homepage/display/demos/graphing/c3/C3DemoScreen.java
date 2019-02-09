package com.jwebmp.examples.demos.homepage.display.demos.graphing.c3;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.c3.C3PageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class C3DemoScreen
		extends DemoScreen
{
	public C3DemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.c3", C3PageConfigurator.class,
		                          "C3", true, true));
		add(buildGoToSource(C3PageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
