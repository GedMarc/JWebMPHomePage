package com.jwebmp.examples.demos.homepage.display.demos.display.dynamicsourcecodeviewer;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.dynamicsourcecode.DynamicSourceCodePageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class DynamicSourceCodeViewerDemoScreen
		extends DemoScreen
{
	public DynamicSourceCodeViewerDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.dynamicsourcecode", DynamicSourceCodePageConfigurator.class,
		                          "Dynamic Source Code Viewer", true, true));
		add(buildGoToSource(DynamicSourceCodePageConfigurator.class, DynamicSourceCodeViewerDemoScreen.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
