package com.jwebmp.examples.demos.homepage.display.demos.display.easingeffects;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.easingeffects.JQEasingPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class EasingEffectsDemoScreen
		extends DemoScreen
{
	public EasingEffectsDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.easingeffects", JQEasingPageConfigurator.class,
		                          "JQuery Easing Effects", true, true));
		add(buildGoToSource(JQEasingPageConfigurator.class, EasingEffectsDemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);


		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
