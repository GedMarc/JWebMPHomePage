package com.jwebmp.examples.demos.homepage.display.demos.display.moment;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.moment.MomentPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class MomentDemoScreen
		extends DemoScreen
{
	public MomentDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.moment", MomentPageConfigurator.class,
		                          "Moment", true, true));
		add(buildGoToSource(MomentPageConfigurator.class, MomentDemoScreen.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
