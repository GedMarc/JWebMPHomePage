package com.jwebmp.examples.demos.homepage.display.demos.forms.ionrangeslider;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.ionrangeslider.IonRangeSliderPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class IonRangeSliderDemoScreen
		extends DemoScreen
{
	public IonRangeSliderDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.ionrangeslider", IonRangeSliderPageConfigurator.class,
		                          "Ion Range Slider", true, true));
		add(buildGoToSource(IonRangeSliderPageConfigurator.class, DefaultPackageAPI.class));
		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
