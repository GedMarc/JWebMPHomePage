package com.jwebmp.examples.demos.homepage.display.demos.jqui.themesnestable;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.jqui.themesnested.JQUINestableThemesPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class JQueryUIThemesNestableDemoScreen
		extends DemoScreen
{

	public JQueryUIThemesNestableDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.jqui.themesnested", JQUINestableThemesPageConfigurator.class,
		                          "jQuery UI Themes Nestable", true, true));
		add(buildGoToSource(JQUINestableThemesPageConfigurator.class, DefaultPackageAPI.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
