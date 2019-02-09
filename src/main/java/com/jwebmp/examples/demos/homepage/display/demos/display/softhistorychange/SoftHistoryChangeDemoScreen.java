package com.jwebmp.examples.demos.homepage.display.demos.display.softhistorychange;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.softhistorychange.SoftHistoryChangePageConfiguration;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class SoftHistoryChangeDemoScreen
		extends DemoScreen
{
	public SoftHistoryChangeDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.softhistorychange", SoftHistoryChangePageConfiguration.class,
		                          "Soft History Change", true, true));
		add(buildGoToSource(SoftHistoryChangePageConfiguration.class, SoftHistoryChangeDemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
