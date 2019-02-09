package com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4.nyaselect;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.bs4.nyaselect.NyaSelectPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class Bootstrap4NyaSelectDemoScreen
		extends DemoScreen
{
	public Bootstrap4NyaSelectDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angularnyabootstrapselector4", NyaSelectPageConfigurator.class,
		                          "BS Nya Select4", true, true));
		add(buildGoToSource(NyaSelectPageConfigurator.class, DefaultPackageAPI.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
