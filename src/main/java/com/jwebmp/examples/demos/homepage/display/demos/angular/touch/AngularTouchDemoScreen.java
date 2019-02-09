package com.jwebmp.examples.demos.homepage.display.demos.angular.touch;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.examples.demos.homepage.components.general.OptionsBrowser;
import com.jwebmp.plugins.angulartouch.AngularTouchPageConfigurator;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class AngularTouchDemoScreen
		extends DemoScreen
{
	public AngularTouchDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angulartouch", AngularTouchPageConfigurator.class,
		                          "Angular Touch", true, true));
		add(buildGoToSource(AngularTouchPageConfigurator.class, DefaultPackageAPI.class, OptionsBrowser.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
