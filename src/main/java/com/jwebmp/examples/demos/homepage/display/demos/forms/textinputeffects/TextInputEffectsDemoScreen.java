package com.jwebmp.examples.demos.homepage.display.demos.forms.textinputeffects;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.textinputeffects.TextInputEventsPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class TextInputEffectsDemoScreen
		extends DemoScreen
{
	public TextInputEffectsDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.textinputeffects", TextInputEventsPageConfigurator.class,
		                          "Text Input Effects", true, true));
		add(buildGoToSource(TextInputEventsPageConfigurator.class, DefaultPackageAPI.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
