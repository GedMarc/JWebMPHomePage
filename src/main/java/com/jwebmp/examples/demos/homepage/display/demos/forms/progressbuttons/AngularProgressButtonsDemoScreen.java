package com.jwebmp.examples.demos.homepage.display.demos.forms.progressbuttons;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.angularprogressbuttonstyles.AngularProgressButtonStylesPageConfigurator;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class AngularProgressButtonsDemoScreen
		extends DemoScreen
{
	public AngularProgressButtonsDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angularprogressbuttonstyles", AngularProgressButtonStylesPageConfigurator.class,
		                          "Angular Progress Button Styles", true, true));
		add(buildGoToSource(AngularProgressButtonStylesPageConfigurator.class, DefaultPackageAPI.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		add(more);
	}
}
