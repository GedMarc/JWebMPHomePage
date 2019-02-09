package com.jwebmp.examples.demos.homepage.display.demos.display.fullcalendar;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.fullcalendar.FullCalendarPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class FullCalendarDemoScreen
		extends DemoScreen
{
	public FullCalendarDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.fullcalendar", FullCalendarPageConfigurator.class,
		                          "Full Calendar", true, true));
		add(buildGoToSource(FullCalendarPageConfigurator.class, FullCalendarDemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//		add(more);
	}
}
