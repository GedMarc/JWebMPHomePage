package com.jwebmp.examples.demos.homepage.display.demos.display.weblogappender;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.weblogappender.WebLogAppenderPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class WebLogAppenderDemoScreen
		extends DemoScreen
{
	public WebLogAppenderDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.weblogappender", WebLogAppenderPageConfigurator.class,
		                          "Web Log Appender", true, true));
		add(buildGoToSource(WebLogAppenderPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
