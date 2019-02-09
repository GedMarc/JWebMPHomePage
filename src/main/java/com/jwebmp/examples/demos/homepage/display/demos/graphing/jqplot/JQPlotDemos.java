package com.jwebmp.examples.demos.homepage.display.demos.graphing.jqplot;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.jqplot.JQPlotPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class JQPlotDemos
		extends DemoScreen
{
	public JQPlotDemos()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.jqplot", JQPlotPageConfigurator.class,
		                          "JQ Plot", true, true));
		add(buildGoToSource(JQPlotPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}

}
