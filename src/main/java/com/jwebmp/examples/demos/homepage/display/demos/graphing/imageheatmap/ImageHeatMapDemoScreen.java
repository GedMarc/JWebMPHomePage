package com.jwebmp.examples.demos.homepage.display.demos.graphing.imageheatmap;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.imagemap.JQImageMapPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class ImageHeatMapDemoScreen
		extends DemoScreen
{
	public ImageHeatMapDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.imagemap", JQImageMapPageConfigurator.class,
		                          "JQ Image Heat Map", true, true));
		add(buildGoToSource(JQImageMapPageConfigurator.class, DefaultPackageAPI.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
