package com.jwebmp.examples.demos.homepage.display.demos.display.modernizr;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.modernizr.ModernizrPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class ModernizrDemoScreen
		extends DemoScreen
{
	public ModernizrDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.modernizr", ModernizrPageConfigurator.class,
		                          "Modernizr", true, true));
		add(buildGoToSource(ModernizrPageConfigurator.class, ModernizrDemoScreen.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
