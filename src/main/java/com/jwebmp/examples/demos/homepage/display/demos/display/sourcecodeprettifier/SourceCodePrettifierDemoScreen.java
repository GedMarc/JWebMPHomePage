package com.jwebmp.examples.demos.homepage.display.demos.display.sourcecodeprettifier;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.google.sourceprettify.JQSourceCodePrettifyPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class SourceCodePrettifierDemoScreen
		extends DemoScreen
{
	public SourceCodePrettifierDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.google.sourceprettify", JQSourceCodePrettifyPageConfigurator.class,
		                          "Google SourceCode Prettyify", true, true));
		add(buildGoToSource(JQSourceCodePrettifyPageConfigurator.class, SourceCodePrettifierDemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
