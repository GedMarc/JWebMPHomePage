package com.jwebmp.examples.demos.homepage.display.demos.display.metro;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.metrojs.JQMetroPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.google.sourceprettify.SourceCodeLanguages.*;

public class MetroJSDemoScreen
		extends DemoScreen
{
	public MetroJSDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.metrojs", JQMetroPageConfigurator.class,
		                          "JQ Metro", true, true));
		add(buildGoToSource(JQMetroPageConfigurator.class, MetroJSDemoScreen.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		add(more);
		addSourceToContainer(MetroJSDemoScreen.class, "codesnippet.txt", Java, more);
	}
}
