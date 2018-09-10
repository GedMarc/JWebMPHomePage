package com.jwebmp.examples.demos.homepage.display.demos.angular.ngslimscroll;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class NgSlimScrollDemoScreen
		extends PluginDemoScreen
{
	public NgSlimScrollDemoScreen()
	{
		super("NG Slimscroll", "Angular", "NG Slim Scroll");

		addComponentTile("NgSlimScrollPageConfigurator", "Applies the slim scroll with the default settings. Options are limited to additional CSS Entries");

		getAdditionalsRight().add(getCodeBlockJava(NgSlimScrollDemoScreen.class, "example.txt"));

		getAdditionals().add(new Div<>().addClass(Col_12)
		                                .setText("A simple slim scroller. To override the default styles a css file may be needed."));
	}
}
