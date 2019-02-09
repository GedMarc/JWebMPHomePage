package com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4.switcher;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.bs4.toggle.BSSwitch4PageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class Bootstrap4SwitchDemoScreen
		extends DemoScreen
{
	public Bootstrap4SwitchDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.bootstrap.switch4", BSSwitch4PageConfigurator.class,
		                          "Bootstrap Switch 4", true, true));
		add(buildGoToSource(BSSwitch4PageConfigurator.class, Bootstrap4SwitchDemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);


		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
