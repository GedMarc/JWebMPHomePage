package com.jwebmp.examples.demos.homepage.display.demos.icons.glyphicons;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.glyphicons.GlyphiconsPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class GlyphiconsDemoScreen
		extends DemoScreen
{

	public GlyphiconsDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.glyphicons", GlyphiconsPageConfigurator.class,
		                          "Glyphicons", true, true));
		add(buildGoToSource(GlyphiconsPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
