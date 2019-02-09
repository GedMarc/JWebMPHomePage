package com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4.datetimepicker;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.bs4.datetimepicker.BS4DateTimePageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class Bootstrap4DateTimePickerDemoScreen
		extends DemoScreen
{
	public Bootstrap4DateTimePickerDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.bs4datetimepicker", BS4DateTimePageConfigurator.class,
		                          "BS4 DateTime picker", true, true));
		add(buildGoToSource(BS4DateTimePageConfigurator.class, Bootstrap4DateTimePickerDemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);


		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
