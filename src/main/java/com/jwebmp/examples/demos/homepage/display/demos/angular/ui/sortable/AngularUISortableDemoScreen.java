package com.jwebmp.examples.demos.homepage.display.demos.angular.ui.sortable;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.angularuisortable.AngularUISortablePageConfigurator;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class AngularUISortableDemoScreen
		extends DemoScreen
{
	public AngularUISortableDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angularuisortable", AngularUISortablePageConfigurator.class,
		                          "Angular UI Sortable", true, true));
		add(buildGoToSource(AngularUISortablePageConfigurator.class, AngularUISortableDemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);


		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
