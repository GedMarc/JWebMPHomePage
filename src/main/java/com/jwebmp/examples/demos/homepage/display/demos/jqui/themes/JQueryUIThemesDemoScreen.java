package com.jwebmp.examples.demos.homepage.display.demos.jqui.themes;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.jqui.themes.JQUIThemesPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class JQueryUIThemesDemoScreen
		extends DemoScreen
{
	public JQueryUIThemesDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.jqui.themes", JQUIThemesPageConfigurator.class,
		                          "JQuery UI Themes", true, true));
		add(buildGoToSource(JQUIThemesPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
