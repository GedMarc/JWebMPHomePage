package com.jwebmp.examples.demos.homepage.display.demos.angular.animatedchange;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.examples.demos.homepage.components.general.OptionsBrowser;
import com.jwebmp.examples.demos.homepage.display.demos.angular.animate.AngularAnimateDemoScreen;
import com.jwebmp.plugins.angularanimatedchange.AngularAnimatedChangePageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;

public class AngularAnimateChangeDemoScreen
		extends DemoScreen
{
	public AngularAnimateChangeDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angularanimatedchange", AngularAnimatedChangePageConfigurator.class,
		                          "Angular Animated change", true, true));
		add(buildGoToSource(AngularAnimateDemoScreen.class, DefaultPackageAPI.class, OptionsBrowser.class));

		Div sourceDiv = new Div<>().addClass(Col_12, Row);

		sourceDiv.add(new Div("This directive is automatically included when added to the classpath"));

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		add(more);
	}

}
