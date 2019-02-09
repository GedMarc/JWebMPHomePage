package com.jwebmp.examples.demos.homepage.display.demos.trees.jstree;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.jstree.JSTreePageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class JSTreeDemoScreen
		extends DemoScreen
{

	public JSTreeDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.jstree", JSTreePageConfigurator.class,
		                          "JS Tree", true, true));
		add(buildGoToSource(JSTreeDemoScreen.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
