package com.jwebmp.examples.demos.homepage.display.demos.forms.textangular;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.textangular.TextAngularPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class TextAngularDemoScreen
		extends DemoScreen
{
	public TextAngularDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.textangular", TextAngularPageConfigurator.class,
		                          "Text Angular", true, true));
		add(buildGoToSource(TextAngularPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
