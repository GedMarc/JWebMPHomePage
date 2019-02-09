package com.jwebmp.examples.demos.homepage.display.demos.angular.zoomin;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.angularzoomanimation.AngularZoomInAnimationPageConfigurator;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class AngularZoomDemoScreen
		extends DemoScreen
{
	public AngularZoomDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angularzoomanimation", AngularZoomInAnimationPageConfigurator.class,
		                          "Angular Zoom in Animation", true, true));
		add(buildGoToSource(AngularZoomInAnimationPageConfigurator.class, AngularZoomDemoScreen.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
