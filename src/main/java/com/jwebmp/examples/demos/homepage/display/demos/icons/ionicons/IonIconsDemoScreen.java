package com.jwebmp.examples.demos.homepage.display.demos.icons.ionicons;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.ionic.ionicons.IonIconsPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class IonIconsDemoScreen
		extends DemoScreen
{

	public IonIconsDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.ionic.ionicons", IonIconsPageConfigurator.class,
		                          "Ion Icons", true, true));
		add(buildGoToSource(IonIconsPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}

}
