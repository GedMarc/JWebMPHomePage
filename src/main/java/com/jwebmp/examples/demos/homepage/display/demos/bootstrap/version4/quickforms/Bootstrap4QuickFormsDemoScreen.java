package com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4.quickforms;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.bs4.quickforms.BSQuickFormsPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class Bootstrap4QuickFormsDemoScreen
		extends DemoScreen
{
	public Bootstrap4QuickFormsDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.bsquickforms4", BSQuickFormsPageConfigurator.class,
		                          "BS QuickForms4", true, true));
		add(buildGoToSource(BSQuickFormsPageConfigurator.class, Bootstrap4QuickFormsDemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
