package com.jwebmp.examples.demos.homepage.display.demos.forms.quickforms;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.bs4.quickforms.BSQuickFormsPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class QuickFormsDemoScreen
		extends DemoScreen
{
	public QuickFormsDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.quickforms", BSQuickFormsPageConfigurator.class,
		                          "Quick Forms", true, true));
		add(buildGoToSource(BSQuickFormsPageConfigurator.class, DefaultPackageAPI.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
