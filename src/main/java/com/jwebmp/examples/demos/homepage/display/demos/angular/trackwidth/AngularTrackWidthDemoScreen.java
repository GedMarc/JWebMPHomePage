package com.jwebmp.examples.demos.homepage.display.demos.angular.trackwidth;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.examples.demos.homepage.components.general.OptionsBrowser;
import com.jwebmp.plugins.angulartrackwidth.AngularTrackWidthPageConfigurator;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class AngularTrackWidthDemoScreen
		extends DemoScreen
{
	public AngularTrackWidthDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angulartrackwidth", AngularTrackWidthPageConfigurator.class,
		                          "Angular Track Width", true, true));
		add(buildGoToSource(AngularTrackWidthPageConfigurator.class, AngularTrackWidthDemoScreen.class, OptionsBrowser.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
