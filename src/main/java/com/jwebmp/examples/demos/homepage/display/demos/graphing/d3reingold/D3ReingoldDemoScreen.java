package com.jwebmp.examples.demos.homepage.display.demos.graphing.d3reingold;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.d3.radialreingoldtilfordtree.D3ReingoldTilfordTreePageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class D3ReingoldDemoScreen
		extends DemoScreen
{
	public D3ReingoldDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.d3.radialreingoldtilfordtree", D3ReingoldTilfordTreePageConfigurator.class,
		                          "D3 ReingoldTelFord Tree", true, true));
		add(buildGoToSource(D3ReingoldTilfordTreePageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
