package com.jwebmp.examples.demos.homepage.display.demos.jqui.spectrumcolourpicker;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.spectrum.colourpicker.JQSpectrumColourPicker;
import com.jwebmp.plugins.spectrum.colourpicker.JQSpectrumPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class SpectrumDemoScreen
		extends DemoScreen
{
	public SpectrumDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.spectrum.colourpicker", JQSpectrumColourPicker.class,
		                          "Spectrum", true, true));
		add(buildGoToSource(JQSpectrumPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
