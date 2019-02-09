package com.jwebmp.examples.demos.homepage.display.demos.jqxwidgets;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.core.base.html.H3;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.jqxwidgets.JQXWidgetsPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class JQXWidgetsDemoScreen
		extends DemoScreen
{
	public JQXWidgetsDemoScreen()
	{
		add(new H3<>("JQX Widgets requires a license for commercial projects"));
		add(new DefaultPackageAPI("com.jwebmp.plugins.jqxwidgets", JQXWidgetsPageConfigurator.class,
		                          "JQX Widgets", true, true));
		add(buildGoToSource(JQXWidgetsDemoScreen.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
