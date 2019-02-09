package com.jwebmp.examples.demos.homepage.display.demos.angular.ionslider;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.examples.demos.homepage.components.general.OptionsBrowser;
import com.jwebmp.plugins.angularionslider.AngularIonSliderPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;
import static com.jwebmp.plugins.bootstrap4.options.BSContainerOptions.*;

public class AngularIonSliderDemoScreen
		extends DemoScreen
{
	public AngularIonSliderDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angularionslider", AngularIonSliderPageConfigurator.class,
		                          "Angular Ion Slider", true, true));
		add(buildGoToSource(AngularIonSliderPageConfigurator.class, DefaultPackageAPI.class, OptionsBrowser.class));

		Div sourceDiv = new Div<>().addClass(Col_12, Row);


		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		add(more);

		more.add(getCodeBlockJava(AngularIonSliderDemoScreen.class, "example.txt"));
	}
}
