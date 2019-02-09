package com.jwebmp.examples.demos.homepage.display.demos.display.radialslidergem;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.radialsvgslider.RadialSVGSliderGemPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class RadialSliderGemDemoScreen
		extends DemoScreen
{
	public RadialSliderGemDemoScreen()
	{

		add(new DefaultPackageAPI("com.jwebmp.plugins.radialsvgslider", RadialSVGSliderGemPageConfigurator.class,
		                          "Radial Slider Gem", true, true));
		add(buildGoToSource(RadialSVGSliderGemPageConfigurator.class, RadialSliderGemDemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
