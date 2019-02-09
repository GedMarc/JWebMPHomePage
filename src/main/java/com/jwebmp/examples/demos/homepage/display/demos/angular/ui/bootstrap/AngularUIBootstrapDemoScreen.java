package com.jwebmp.examples.demos.homepage.display.demos.angular.ui.bootstrap;

import com.jwebmp.core.base.html.Div;
import com.jwebmp.examples.demos.homepage.components.DefaultReadMore;
import com.jwebmp.examples.demos.homepage.components.DemoScreen;
import com.jwebmp.examples.demos.homepage.components.display.DefaultPackageAPI;
import com.jwebmp.plugins.angularuibootstrap.AngularUIBootstrapPageConfigurator;
import com.jwebmp.plugins.bootstrap4.options.BSContainerOptions;

import static com.jwebmp.plugins.bootstrap4.options.BSColumnOptions.*;

public class AngularUIBootstrapDemoScreen
		extends DemoScreen
{
	public AngularUIBootstrapDemoScreen()
	{
		add(new DefaultPackageAPI("com.jwebmp.plugins.angularuibootstrap", AngularUIBootstrapPageConfigurator.class,
		                          "Angular UI Bootstrap", true, true));
		add(buildGoToSource(AngularUIBootstrapPageConfigurator.class, AngularUIBootstrapDemoScreen.class));

		Div sourceDiv = new Div<>().addClass(Col_12, BSContainerOptions.Row);

		DefaultReadMore more = new DefaultReadMore(sourceDiv, "View More Info");
		//	add(more);
	}
}
