package com.jwebmp.examples.demos.homepage.display.demos.forms.smartwizard;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;
import com.jwebmp.plugins.smartwizard4.SmartWizardPageConfigurator;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class SmartWizardDemoScreen
		extends DemoScreen
{
	public SmartWizardDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.smartwizard4", SmartWizardPageConfigurator.class,
		                          "Smart Wizard", true, true));
		add(buildGoToSource(SmartWizardPageConfigurator.class, DefaultPackageAPI.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);
		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//add(more);
	}
}
