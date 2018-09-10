package com.jwebmp.examples.demos.homepage.display.demos.angular.ionslider;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.general.PluginDemoScreen;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class AngularIonSliderDemoScreen
		extends PluginDemoScreen
{
	public AngularIonSliderDemoScreen()
	{
		super("Angular Ion Slider", "Angular", "Ion Slider");

		addComponentTile("AngularIonSlider", "Client Variable Data Bound Ion Slider. Maps specific properties to data variables.");
		getAdditionalsRight().add(getCodeBlockJava(AngularIonSliderDemoScreen.class, "example.txt"));

		getAdditionals().add(new Div<>().addClass(Col_12)
		                                .setText("The Angular Ion Slider binds to client variables"));
	}
}
