package com.jwebmp.examples.demos.homepage.display.demos.jqui.layout;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.jqlayout.JQLayoutPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class JQLayoutDemoScreen
		extends DemoScreen
{
	public JQLayoutDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.jqlayout", JQLayoutPageConfigurator.class,
		                          "JQ Layout", true, true));
		add(buildGoToSource(JQLayoutPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
