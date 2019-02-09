package com.jwebmp.examples.demos.homepage.display.demos.display.verticaltimelinegem;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.verticaltimeline.VerticalTimelinePageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class VerticalTimelineGemDemoScreen
		extends DemoScreen
{
	public VerticalTimelineGemDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.verticaltimelin", VerticalTimelinePageConfigurator.class,
		                          "Vertical TimeLine Gem", true, true));
		add(buildGoToSource(VerticalTimelinePageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);


		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
