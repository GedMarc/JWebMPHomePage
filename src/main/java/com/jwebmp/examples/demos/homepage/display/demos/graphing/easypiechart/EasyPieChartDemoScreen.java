package com.jwebmp.examples.demos.homepage.display.demos.graphing.easypiechart;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.easypiechart.EasyPieChartPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class EasyPieChartDemoScreen
		extends DemoScreen
{
	public EasyPieChartDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.easypiechart", EasyPieChartPageConfigurator.class,
		                          "Easy Pie Chart", true, true));
		add(buildGoToSource(EasyPieChartPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
