package com.jwebmp.examples.demos.homepage.display.demos.jqui.datetimepicker;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.jqui.datetimepicker.JQUIDateTimePickerPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class JQueryUIDateTimePickerDemoScreen
		extends DemoScreen
{
	public JQueryUIDateTimePickerDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.jqui.datetimepicker", JQUIDateTimePickerPageConfigurator.class,
		                          "JQuery UI DateTime Picker", true, true));
		add(buildGoToSource(JQUIDateTimePickerPageConfigurator.class, DefaultPackageAPI.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
