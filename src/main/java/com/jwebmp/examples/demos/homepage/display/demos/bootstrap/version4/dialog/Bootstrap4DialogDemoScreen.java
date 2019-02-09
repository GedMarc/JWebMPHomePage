package com.jwebmp.examples.demos.homepage.display.demos.bootstrap.version4.dialog;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.bs4.dialog.BSDialogPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class Bootstrap4DialogDemoScreen
		extends DemoScreen
{
	public Bootstrap4DialogDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.bootstrap.dialog", BSDialogPageConfigurator.class,
		                          "Bootstrap Dialog 4", true, true));
		add(buildGoToSource(BSDialogPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
