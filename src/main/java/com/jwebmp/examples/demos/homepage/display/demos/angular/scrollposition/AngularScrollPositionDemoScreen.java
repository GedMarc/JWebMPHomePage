package com.jwebmp.examples.demos.homepage.display.demos.angular.scrollposition;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.angularscrollposition.AngularScrollPositionPageConfigurator;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class AngularScrollPositionDemoScreen
		extends DemoScreen
{
	public AngularScrollPositionDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angularscrollposition", AngularScrollPositionPageConfigurator.class,
		                          "Angular Scroll Position", true, true));
		add(buildGoToSource(AngularScrollPositionPageConfigurator.class, AngularScrollPositionDemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);


		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
