package com.jwebmp.examples.demos.homepage.display.demos.display.plusastab;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.plusastab.PlusAsTabPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class PlusAsTabDemoScreen
		extends DemoScreen
{
	public PlusAsTabDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.plusastab", PlusAsTabPageConfigurator.class,
		                          "JQuery Plus As Tab", true, true));
		add(buildGoToSource(PlusAsTabPageConfigurator.class, DefaultPackageAPI.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
