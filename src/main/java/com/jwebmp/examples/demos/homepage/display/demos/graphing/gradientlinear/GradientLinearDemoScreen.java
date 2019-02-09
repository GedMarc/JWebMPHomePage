package com.jwebmp.examples.demos.homepage.display.demos.graphing.gradientlinear;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.jqgradientlinear.JQGradientPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class GradientLinearDemoScreen
		extends DemoScreen
{
	public GradientLinearDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.jqgradientlinear", JQGradientPageConfigurator.class,
		                          "JQ GradientLiner", true, true));
		add(buildGoToSource(JQGradientPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
